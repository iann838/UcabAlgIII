package edu.ucab.cryptomonitor.views;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.paaksing.jjango.Database.ObjectDoesNotExist;
import com.paaksing.jjango.Validator.ValidationError;

import edu.ucab.cryptomonitor.api.CmcAPI;
import edu.ucab.cryptomonitor.api.CmcAPI.RequestFailed;
import edu.ucab.cryptomonitor.app.Request;
import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.Alert;
import edu.ucab.cryptomonitor.models.Currency;
import edu.ucab.cryptomonitor.models.WarningTone;
import edu.ucab.cryptomonitor.models.Alert.Direction;

public class BitcoinLiveView extends View {

    @Override
    public Response dispatch(Request request) throws IOException {
        Scanner scan = request.getScanner();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        System.out.println(String.format("*** Monitor de Bitcoin | Bitcoin Live (@%s) ***", request.getUser().getUsername()));
        System.out.println("(Presiona enter para salir)");
        System.out.println();
        System.out.println(">> Bitcoin estadisticas en vivo:");
        System.out.println(String.format(
            "%5s  %5s  %15s  %10s  %7s  %7s  %7s  %15s  %15s  %25s",
            "Rank",
            "Etiq",
            "Nombre",
            "Precio",
            "1h %",
            "24h %",
            "7d %",
            "Volume 24h",
            "Market Cap",
            "Actualizacion"
        ));

        try {
            double ommitedUpwardPrice = Double.MIN_VALUE;
            double ommitedDownwardPrice = Double.MAX_VALUE;
            while (true) {                
                List<Alert> alerts = Alert.objects.filter("user", request.getUser().getId());
                CmcAPI.updateLatestCurrencies();
                Currency currency = Currency.objects.first("tag", "BTC");
                System.out.println(String.format(
                    "%5d  %5s  %15s  %10.2f  %7.2f  %7.2f  %7.2f  %15.0f  %15.0f  %25s",
                    currency.getRank(),
                    currency.getTag(),
                    currency.getName(),
                    currency.getPrice(),
                    currency.getPercentChange1h(),
                    currency.getPercentChange24h(),
                    currency.getPercentChange7d(),
                    currency.getVolume24h(),
                    currency.getMarketCap(),
                    df.format(currency.getLastUpdated())
                ));

                Alert worstAlert = null;
                double priceDiff = Double.MAX_VALUE;
                for (Alert alert: alerts) {
                    if (
                        alert.getDirection() == Direction.GTE &&
                        currency.getPrice() >= alert.getPrice() &&
                        alert.getPrice() > ommitedUpwardPrice &&
                        currency.getPrice() - alert.getPrice() < priceDiff
                    ) {
                        worstAlert = alert;
                        priceDiff = currency.getPrice() - alert.getPrice();
                    } else if (
                        alert.getDirection() == Direction.LTE &&
                        currency.getPrice() <= alert.getPrice() &&
                        alert.getPrice() < ommitedDownwardPrice &&
                        alert.getPrice() - currency.getPrice() < priceDiff
                    ) {
                        worstAlert = alert;
                        priceDiff = alert.getPrice() - currency.getPrice();
                    }
                }

                if (worstAlert != null) {
                    String directionSign;
                    if (worstAlert.getDirection() == Direction.GTE) {
                        directionSign = ">=";
                    } else {
                        directionSign = "<=";
                    }
                    System.out.print(String.format(
                        "   !!! Alerta (id: %d) %s%.2f activado por ultima actualizacion de %s con %.2f (Enter para detener) !!!",
                        worstAlert.getId(),
                        directionSign,
                        worstAlert.getPrice(),
                        df.format(currency.getLastUpdated()),
                        currency.getPrice()
                    ));

                    File file = new File(WarningTone.objects.get(worstAlert.getWarningTone()).getFilename());
                    AudioInputStream audioStream;
                    try {
                        audioStream = AudioSystem.getAudioInputStream(file);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioStream);
                        clip.loop(Clip.LOOP_CONTINUOUSLY);
                        scan.nextLine();
                        clip.stop();
                        clip.close();
                        if (worstAlert.getDirection() == Direction.GTE) {
                            ommitedUpwardPrice = worstAlert.getPrice();
                        } else {
                            ommitedDownwardPrice = worstAlert.getPrice();
                        }
                        continue;
                    } catch (UnsupportedAudioFileException e) {
                        System.out.println("Error: Archivo de media no compatible");
                    } catch (LineUnavailableException e) {
                        System.out.println("Error: Archivo de media no reproducible");                        
                    }
                }

                BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
                long startTime = System.currentTimeMillis();
                while ((System.currentTimeMillis() - startTime) < 50 * 1000 && !in.ready()) {}
                
                if (in.ready()) {
                    in.readLine();
                    return new Response(new AlertMenuView(), Status.REDIRECT);
                }
            }
        } catch (ObjectDoesNotExist e) {
            System.out.println();
            System.out.println("Bitcoin no se encuentra registrado");
        } catch (RequestFailed e) {
            System.out.println();
            System.out.println("Conexion hacia API fallida");
        } catch (ValidationError e) {
            System.out.println();
            System.out.println("Error de validacion");
        }

        System.in.read();
        scan.nextLine();
        return new Response(new AlertMenuView(), Status.REDIRECT);
    }

}

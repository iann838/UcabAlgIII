package edu.ucab.cryptomonitor.views;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
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
import edu.ucab.cryptomonitor.models.Alert.Direction;
import edu.ucab.cryptomonitor.models.Currency;
import edu.ucab.cryptomonitor.models.WarningTone;

public class AlertCreateView extends View {

    @Override
    public Response dispatch(Request request) throws IOException {
        Scanner scan = request.getScanner();

        System.out.println(String.format("*** Monitor de Bitcoin | Crear Alerta (@%s) ***", request.getUser().getUsername()));
        try {
            long warningToneId = getWarningToneId(scan);
            try {
                CmcAPI.updateLatestCurrencies();
            } catch (RequestFailed e) {}
            Currency currency = Currency.objects.first("tag", "BTC");
            System.out.print(String.format(">> Precio a alertar (precio BTC actual: %.2f): ", currency.getPrice()));
            double price = scan.nextDouble();
            scan.nextLine();
            if (currency.getPrice() == price) {
                System.out.println();
                System.out.println("El precio a activar tiene que ser mayor o menor que el precio actual");
            } else {
                Direction direction;
                if (currency.getPrice() > price)
                    direction = Alert.Direction.LTE;
                else
                    direction = Alert.Direction.GTE;                
                Alert alert = new Alert(
                    request.getUser().getId(),
                    currency.getId(),
                    price,
                    warningToneId,
                    direction
                );
                alert.save();
                System.out.println();
                System.out.println(String.format("Alerta (id: %d) %s %.2f creado", alert.getId(), currency.getTag(), price));
            }
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println();
            System.out.println("Entrada invalida");
        } catch (ObjectDoesNotExist e) {
            System.out.println();
            System.out.println("Tono no existe");
        } catch (UnsupportedAudioFileException e) {
            System.out.println();
            System.out.println("Archivo de media no compatible");
        } catch (LineUnavailableException e) {
            System.out.println();
            System.out.println("Error en reproduccion del tono");
        } catch (ValidationError e) {
            System.out.println();
            System.out.println("Error en creacion de alerta");
        } finally {
            System.in.read();
            scan.nextLine();
        }
        return new Response(new AlertMenuView(), Status.REDIRECT);
    }

    public static long getWarningToneId (Scanner scan) throws IOException, ObjectDoesNotExist, UnsupportedAudioFileException, LineUnavailableException {
        long warningToneId = -1;
        while (true) {                
            System.out.println(">> Selecciona un tono de alerta:");
            System.out.println(String.format(
                    "%5s  %25s  %35s",
                    "Id",
                    "Nombre",
                    "Archivo"
                    ));
            for (WarningTone tone: WarningTone.objects.all()) {
                System.out.println(String.format(
                        "%5d  %25s  %35s",
                        tone.getId(),
                        tone.getName(),
                        tone.getFilename()
                        ));
            }
            System.out.print(">> Tono de alerta (id): ");
            warningToneId = scan.nextLong();
            scan.nextLine();
            WarningTone warningTone = WarningTone.objects.get(warningToneId);
            System.out.println("Tono seleccionado: " + warningTone.getName());
            System.out.println();
            File file = new File(warningTone.getFilename());
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            String option = "";
            while (!option.equals("M") && !option.equals("C")) {
                System.out.println("R (Reproducir) | D (Detener) | M (Modificar) | C (Confirmar)");
                System.out.print(">> Accion en tono de alerta: ");
                option = scan.nextLine().toUpperCase();
                if (option.equals("R")) {
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } else if (option.equals("D") || option.equals("M") || option.equals("C")) {
                    clip.stop();
                } else {
                    System.out.println("Opcion no valida");
                }
            }
            if (option.equals("C"))
                break;
            System.out.println();
        }
        return warningToneId;
    }
}

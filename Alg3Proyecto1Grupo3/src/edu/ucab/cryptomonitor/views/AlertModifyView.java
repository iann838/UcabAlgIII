package edu.ucab.cryptomonitor.views;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

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
import edu.ucab.cryptomonitor.models.Alert.Direction;

public class AlertModifyView extends View {

    @Override
    public Response dispatch(Request request) throws IOException {
        Scanner scan = request.getScanner();

        System.out.println(String.format("*** Monitor de Bitcoin | Modificar Alerta (@%s) ***", request.getUser().getUsername()));
        
        AlertListView.printAlertList(request.getUser().getId());
        
        System.out.println();
        System.out.print(">> Alerta a modificar (id): ");
        try {
            long alertId = scan.nextLong();
            scan.nextLine();
            System.out.println();
        
            Alert alert = Alert.objects.get(alertId);
            if (alert.getUser() == request.getUser().getId()) {                
                long warningToneId = AlertCreateView.getWarningToneId(scan);
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
                    alert.setWarningTone(warningToneId);
                    alert.setPrice(price);
                    alert.setDirection(direction);
                    alert.save();
                    System.out.println();
                    System.out.println(String.format("Alerta (id: %d) %s %.2f modificado", alert.getId(), currency.getTag(), price));
                }
            } else {
                System.out.println("Permiso rechazado");
            }
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println();
            System.out.println("Entrada invalida");
        } catch (ObjectDoesNotExist e) {
            System.out.println();
            System.out.println("Alerta o tono no existe");
        } catch (UnsupportedAudioFileException e) {
            System.out.println();
            System.out.println("Archivo de media no compatible");
        } catch (LineUnavailableException e) {
            System.out.println();
            System.out.println("Error en reproduccion del tono");
        } catch (ValidationError e) {
            System.out.println();
            System.out.println("Error en creacion de alerta");
        }

        System.in.read();
        scan.nextLine();
        return new Response(new AlertMenuView(), Status.REDIRECT);
    }

}

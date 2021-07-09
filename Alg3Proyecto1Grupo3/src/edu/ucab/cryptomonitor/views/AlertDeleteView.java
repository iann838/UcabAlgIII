package edu.ucab.cryptomonitor.views;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.paaksing.jjango.Database.ObjectDoesNotExist;

import edu.ucab.cryptomonitor.app.Request;
import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.Alert;

public class AlertDeleteView extends View {

    @Override
    public Response dispatch(Request request) throws IOException {
        Scanner scan = request.getScanner();

        System.out.println(String.format("*** Monitor de Bitcoin | Eliminar Alerta (@%s) ***", request.getUser().getUsername()));
        AlertListView.printAlertList(request.getUser().getId());

        System.out.println();
        System.out.print(">> Alerta a eliminar (id): ");
        
        try {
            long alertId = scan.nextLong();
            scan.nextLine();
            System.out.println();
            Alert alert = Alert.objects.get(alertId);
            if (alert.getUser() == request.getUser().getId()) {                
                alert.delete();
                System.out.println(String.format("Alerta (id: %d) %s %.2f eliminado", alert.getId(), "BTC", alert.getPrice()));
            } else {
                System.out.println("Permiso rechazado");              
            }
        } catch (InputMismatchException e) {
            scan.nextLine();
            System.out.println("Entrada invalida");
        } catch (ObjectDoesNotExist e) {
            System.out.println("Alerta no existe");
        }

        System.in.read();
        scan.nextLine();
        return new Response(new AlertMenuView(), Status.REDIRECT);
    }

}

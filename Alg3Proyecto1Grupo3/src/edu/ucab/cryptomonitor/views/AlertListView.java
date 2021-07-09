package edu.ucab.cryptomonitor.views;

import java.io.IOException;
import java.util.Scanner;

import com.paaksing.jjango.Database.ObjectDoesNotExist;

import edu.ucab.cryptomonitor.app.Request;
import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.Alert;
import edu.ucab.cryptomonitor.models.Currency;
import edu.ucab.cryptomonitor.models.Alert.Direction;
import edu.ucab.cryptomonitor.models.WarningTone;

public class AlertListView extends View {

    @Override
    public Response dispatch(Request request) throws IOException {
        Scanner scan = request.getScanner();

        System.out.println(String.format("*** Monitor de Bitcoin | Ver Alertas (@%s) ***", request.getUser().getUsername()));
        printAlertList(request.getUser().getId());

        System.in.read();
        scan.nextLine();
        return new Response(new AlertMenuView(), Status.REDIRECT);
    }
    
    public static void printAlertList(long userId) throws IOException {
        System.out.println(String.format(
            "%5s  %10s  %15s  %25s",
            "Id",
            "Moneda",
            "Precio",
            "Tono"
        ));
        for (Alert alert: Alert.objects.filter("user", userId)) {
            String qualPrice;
            if (alert.getDirection() == Direction.GTE) {
                qualPrice = ">=" + String.format("%.2f", alert.getPrice());
            } else {
                qualPrice = "<=" + String.format("%.2f", alert.getPrice());
            }
            try {
                System.out.println(String.format(
                    "%5d  %10s  %15s  %25s",
                    alert.getId(),
                    Currency.objects.get(alert.getCurrency()).getTag(),
                    qualPrice,
                    WarningTone.objects.get(alert.getWarningTone()).getName()
                ));
            } catch (ObjectDoesNotExist e) {
                // silent
            }
        }
    }

}

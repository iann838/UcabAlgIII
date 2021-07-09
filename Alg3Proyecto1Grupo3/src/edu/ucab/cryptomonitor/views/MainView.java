package edu.ucab.cryptomonitor.views;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.paaksing.jjango.Database.ObjectDoesNotExist;
import com.paaksing.jjango.Validator.ValidationError;

import edu.ucab.cryptomonitor.api.CmcAPI;
import edu.ucab.cryptomonitor.api.CmcAPI.RequestFailed;
import edu.ucab.cryptomonitor.app.Request;
import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.Currency;

public class MainView extends View {

    @Override
    public Response dispatch(Request request) throws IOException {
        if (request.getUser().isAuthenticated()) {            
            return new Response(new HomeView(), Status.REDIRECT);
        }
        Scanner scan = request.getScanner();
        Date minuteAgo = new Date(System.currentTimeMillis() - 300 * 1000);
        List<Currency> currencies = Currency.objects.all();
        if (currencies.isEmpty() || currencies.get(0).getLastUpdated().before(minuteAgo)) {
            try {
                CmcAPI.updateLatestCurrencies();
            } catch (RequestFailed | ValidationError e) {
                System.out.println("Alerta: No se pudo actualizar informacion de cripto monedas");
            }
        }
        while (true) {
            System.out.println("*** Monitor de Bitcoin | Main ***");
            try {
                System.out.println(String.format("Precio actual Bitcoin: %.2f", Currency.objects.first("tag", "BTC").getPrice()));
            } catch (ObjectDoesNotExist e) {
                System.out.println(">> Precio actual Bitcoin: Undefined");
            }
            System.out.println("1. Log in");
            System.out.println("2. Registrar");
            System.out.println("0. Salir");
            
            System.out.println();
            System.out.print(">> Opcion: ");
            String option = scan.nextLine();
            System.out.println();
            
            if (option.equals("1"))
                return new Response(new LoginView(), Status.REDIRECT);
            if (option.equals("2"))
                return new Response(new RegisterView(), Status.REDIRECT);
            if (option.equals("0")) {                
                System.out.println("Bye");
                return new Response(null, Status.GONE);
            }

            System.out.println("Opcion invalida");
            System.in.read();
            scan.nextLine();
        }
    }

}

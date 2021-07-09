package edu.ucab.cryptomonitor.views;

import java.io.IOException;
import java.util.Scanner;

import edu.ucab.cryptomonitor.app.Request;
import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.User;

public class HomeView extends View {

    @Override
    public Response dispatch(Request request) throws IOException {
        while (true) {
            Scanner scan = request.getScanner();

            System.out.println(String.format("*** Monitor de Bitcoin | Home (@%s) ***", request.getUser().getUsername()));
            System.out.println("1. Top 10 criptomonedas");
            System.out.println("2. Entrar a Bitcoin Live");
            System.out.println("3. Manejo de alertas");
            System.out.println("4. Modificar usuario");
            System.out.println("0. Log out");

            System.out.println();
            System.out.print(">> Opcion: ");
            String option = scan.nextLine();
            System.out.println();

            if (option.equals("1"))
                return new Response(new TopCryptoView(), Status.REDIRECT);
            if (option.equals("2"))
                return new Response(new BitcoinLiveView(), Status.REDIRECT);
            if (option.equals("3"))
                return new Response(new AlertMenuView(), Status.REDIRECT);
            if (option.equals("0")) {                
                request.setUser(new User()); // Log out
                return new Response(new MainView(), Status.REDIRECT);
            }

            System.out.println("Opcion invalida");
            System.in.read();
            scan.nextLine();
        }
    }

}

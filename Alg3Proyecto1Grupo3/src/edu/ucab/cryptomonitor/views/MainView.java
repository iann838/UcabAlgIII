package edu.ucab.cryptomonitor.views;

import java.io.IOException;
import java.util.Scanner;

import edu.ucab.cryptomonitor.app.Request;
import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;

public class MainView extends View {

    @Override
    public Response dispatch(Request request) throws IOException {
        if (request.getUser().isAuthenticated()) {            
            return new Response(new HomeView(), Status.REDIRECT);
        }
        while (true) {
            Scanner scan = request.getScanner();
            
            System.out.println("*** Monitor de Bitcoin | Main ***");
            System.out.println("1. Log in");
            System.out.println("2. Registrarse");
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

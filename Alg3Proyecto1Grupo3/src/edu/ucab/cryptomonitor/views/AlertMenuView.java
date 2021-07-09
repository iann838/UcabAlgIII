package edu.ucab.cryptomonitor.views;

import java.io.IOException;
import java.util.Scanner;

import edu.ucab.cryptomonitor.app.Request;
import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;

public class AlertMenuView extends View {

    @Override
    public Response dispatch(Request request) throws IOException {
        while (true) {
            Scanner scan = request.getScanner();

            System.out.println(String.format("*** Monitor de Bitcoin | Alertas (@%s) ***", request.getUser().getUsername()));
            System.out.println("1. Ver alertas");
            System.out.println("2. Crear alerta");
            System.out.println("3. Modificar alerta");
            System.out.println("4. Eliminar alerta");
            System.out.println("0. Regresar a Home");

            System.out.println();
            System.out.print(">> Opcion: ");
            String option = scan.nextLine();
            System.out.println();

            if (option.equals("1"))
                return new Response(new AlertListView(), Status.REDIRECT);
            if (option.equals("2"))
                return new Response(new AlertCreateView(), Status.REDIRECT);
            if (option.equals("3"))
                return new Response(new AlertModifyView(), Status.REDIRECT);
            if (option.equals("4"))
                return new Response(new AlertDeleteView(), Status.REDIRECT);
            if (option.equals("0")) {
                return new Response(new MainView(), Status.REDIRECT);
            }

            System.out.println("Opcion invalida");
            System.in.read();
            scan.nextLine();
        }
    }

}

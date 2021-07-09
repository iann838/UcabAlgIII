package edu.ucab.cryptomonitor.views;

import java.io.IOException;
import java.util.Scanner;

import com.paaksing.jjango.Database.ObjectDoesNotExist;

import edu.ucab.cryptomonitor.app.Request;
import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.User;

public class LoginView extends View {

    @Override
    public Response dispatch(Request request) throws IOException {
        Scanner scan = request.getScanner();

        System.out.println("*** Monitor de Bitcoin | Log in ***");
        System.out.print(">> Email: ");
        String email = scan.nextLine();
        System.out.print(">> Password: ");
        String password = scan.nextLine();
        System.out.println();
        
        if (User.authenticate(email, password)) {
            try {
                User user = User.objects.first("email", email);
                request.setUser(user);
            } catch (ObjectDoesNotExist e) {
                e.printStackTrace();
            }
            return new Response(new MainView(), Status.REDIRECT);
        }

        System.out.println("Usuario o clave invalida");
        System.in.read();
        scan.nextLine();
        return new Response(new MainView(), Status.REDIRECT);        
    }
}

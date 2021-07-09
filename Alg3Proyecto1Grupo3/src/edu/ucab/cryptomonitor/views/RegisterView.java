package edu.ucab.cryptomonitor.views;

import java.io.IOException;
import java.util.Scanner;

import com.paaksing.jjango.Validator.InsecurePassword;
import com.paaksing.jjango.Validator.InvalidEmail;
import com.paaksing.jjango.Validator.UniqueConstraintFailed;
import com.paaksing.jjango.Validator.ValidationError;

import edu.ucab.cryptomonitor.app.Request;
import edu.ucab.cryptomonitor.app.Response;
import edu.ucab.cryptomonitor.app.Status;
import edu.ucab.cryptomonitor.app.View;
import edu.ucab.cryptomonitor.models.User;

public class RegisterView extends View {

    @Override
    public Response dispatch(Request request) throws IOException {

        Scanner scan = request.getScanner();

        System.out.println("*** Monitor de Bitcoin | Registrar ***");
        System.out.print(">> Username: ");
        String username = scan.nextLine();
        System.out.print(">> Email: ");
        String email = scan.nextLine();
        System.out.print(">> Password: ");
        String password = scan.nextLine();
        System.out.println();

        User user = new User(username, email, password);
        try {
            user.save();
            System.out.println(String.format("Usuario %s registrado exitosamente", user.getUsername()));
        } catch (UniqueConstraintFailed e) {
            System.out.println("Ya este email se encuentra registrado");
        } catch (InvalidEmail e) {
            System.out.println("Email no valido");
        } catch (InsecurePassword e) {
            System.out.println("Clave insegura (debe contener min. 8 caracteres Mayus, Minus, especiales y numerales)");
        } catch (ValidationError e) {
            System.out.println(e.getMessage());
        }

        System.in.read();
        scan.nextLine();
        return new Response(new MainView(), Status.REDIRECT);
    }

}

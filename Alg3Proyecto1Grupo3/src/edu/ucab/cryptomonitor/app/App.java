package edu.ucab.cryptomonitor.app;

import java.io.IOException;
import java.util.Scanner;

import edu.ucab.cryptomonitor.models.User;
import edu.ucab.cryptomonitor.views.MainView;

public class App {

    public static void run() throws IOException {

        Request request = new Request(new User(), new Scanner(System.in));
        View currentView = new MainView();
        while (true) {
            Response response = currentView.dispatch(request);
            if (response.getStatus() == Status.REDIRECT)
                currentView = response.getView();
            else if (response.getStatus() == Status.GONE)
                break;
        }

    }

}

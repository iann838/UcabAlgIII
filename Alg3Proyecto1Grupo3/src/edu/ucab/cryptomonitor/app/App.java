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
//            clearConsole();
            Response response = currentView.dispatch(request);
            if (response.getStatus() == Status.REDIRECT)
                currentView = response.getView();
            else if (response.getStatus() == Status.GONE)
                break;
        }

    }
    
    public static void clearConsole() {
        try {
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
              
            if(operatingSystem.contains("Windows")){        
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process ps;
                ps = pb.inheritIO().start();
                ps.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process ps;
                ps = pb.inheritIO().start();
                ps.waitFor();
            } 
        } catch(Exception e) {
            System.out.println(e);
        }
    }

}

package edu.ucab.cryptomonitor.app;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import edu.ucab.cryptomonitor.api.CryptoAPI;
import edu.ucab.cryptomonitor.models.User;
import edu.ucab.cryptomonitor.views.LoginView;

public class App {

    public static void run() throws IOException {

        State state = new State();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(state.isRunning()) {
                    try {
                        CryptoAPI.updateLatest();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        Request request = new Request(new User());
        View currentView = new LoginView();
        while (true) {
            Response response = currentView.dispatch(request);
            if (response.getStatus() == Status.REDIRECT)
                try {
                    currentView = response.getView().getDeclaredConstructor().newInstance();
                } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                        | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                    e.printStackTrace();
                }
            else if (response.getStatus() == Status.GONE) {
                state.setRunning(false);
                break;
            }
        }
    }

}

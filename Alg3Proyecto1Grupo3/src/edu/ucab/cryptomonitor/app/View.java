package edu.ucab.cryptomonitor.app;

import java.io.IOException;

public abstract class View {

    public abstract Response dispatch(Request request) throws IOException;

}

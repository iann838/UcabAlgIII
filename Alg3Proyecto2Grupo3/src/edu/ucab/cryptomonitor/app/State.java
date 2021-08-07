package edu.ucab.cryptomonitor.app;

public class State {

    private boolean isRunning;
    
    public State() {
        this.isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

}

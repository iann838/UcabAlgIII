package edu.ucab.cryptomonitor.app;

public enum Status {

    REDIRECT(301),
    SUCCESS(200),
    GONE(410)
    ;

    private final int statusCode;

    private Status(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}

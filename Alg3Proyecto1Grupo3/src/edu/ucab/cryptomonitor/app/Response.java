package edu.ucab.cryptomonitor.app;

public class Response {

    private Status status;
    private View view;
    private Object data;
    
    public Response(View view, Status status) {
        super();
        this.view = view;
        this.status = status;
        this.data = null;
    }

    public Response(Object data, Status status) {
        super();
        this.data = data;
        this.status = status;
        this.view = null;
    }

    public Status getStatus() {
        return status;
    }

    public View getView() {
        return view;
    }

    public Object getData() {
        return data;
    }

}

package edu.ucab.cryptomonitor.app;

public class Response {

    private Status status;
    private Class<View> view;
    private Object data;
    
    @SuppressWarnings("unchecked")
    public <T extends View> Response(Class<T> view, Status status) {
        super();
        this.view = (Class<View>) view;
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

    public Class<View> getView() {
        return view;
    }

    public Object getData() {
        return data;
    }

}

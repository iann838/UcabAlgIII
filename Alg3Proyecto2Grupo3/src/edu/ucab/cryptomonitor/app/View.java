package edu.ucab.cryptomonitor.app;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public abstract class View extends JFrame {

    protected Request request;
    protected Response response;
    protected boolean isClosed = false;

    public <T extends View> Response dispatch(Request request) {
        try {
            this.isClosed = false;
            this.request = request;
            this.response = new Response(null, Status.GONE);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.postInit();
            this.setVisible(true);
            this.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    isClosed = true;
                }
            });
            while (!this.isClosed) {
                TimeUnit.MILLISECONDS.sleep(100);
            }
            return this.response;
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(null, Status.GONE);
        }
    }
    
    public void postInit() {
        
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}

package mx.com.audioweb.divisas_project;

import java.io.Serializable;

/**
 * Created by Juan Acosta on 9/1/2014.
 */

public class Contacto implements Serializable {


    private String alert_id;
    private String usr_id;
    private String currency_from;
    private String currency_to;
    private String minimum;
    private String maximum;
    private String notification;
    private String date;

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getAlert_id() {
        return alert_id;
    }
    public void setAlert_id(String alert_id) {
        this.alert_id = alert_id;
    }

    public String getUsr_id() {
        return usr_id;
    }
    public void setUsr_id(String usr_id) {
        this.usr_id = usr_id;
    }

    public String getCurrency_from() {
        return currency_from;
    }
    public void setCurrency_from(String currency_from) {
        this.currency_from = currency_from;
    }

    public String getCurrency_to() {
        return currency_to;
    }
    public void setCurrency_to(String currency_to) {
        this.currency_to = currency_to;
    }

    public String getMinimum() {
        return minimum;
    }
    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getMaximum() {
        return maximum;
    }
    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public String getNotification() {
        return notification;
    }
    public void setNotification(String notification) {
        this.notification = notification;
    }
}



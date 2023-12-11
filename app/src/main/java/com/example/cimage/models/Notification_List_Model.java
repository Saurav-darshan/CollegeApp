package com.example.cimage.models;

public class Notification_List_Model {

    private String notif_type, notif_date, notif_title, notif_message;

    public Notification_List_Model(String notif_type, String notif_date, String notif_title, String notif_message) {
        this.notif_type = notif_type;
        this.notif_date = notif_date;
        this.notif_title = notif_title;
        this.notif_message = notif_message;
    }


    public String getNotif_type() {
        return notif_type;
    }

    public void setNotif_type(String notif_type) {
        this.notif_type = notif_type;
    }

    public String getNotif_date() {
        return notif_date;
    }

    public void setNotif_date(String notif_date) {
        this.notif_date = notif_date;
    }

    public String getNotif_title() {
        return notif_title;
    }

    public void setNotif_title(String notif_title) {
        this.notif_title = notif_title;
    }

    public String getNotif_message() {
        return notif_message;
    }

    public void setNotif_message(String notif_message) {
        this.notif_message = notif_message;
    }
}

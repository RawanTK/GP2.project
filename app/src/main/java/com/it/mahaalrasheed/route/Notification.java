package com.it.mahaalrasheed.route;

import io.realm.RealmObject;

/**
 * Created by RawanTurki on 26-Jan-16.
 */
public class Notification extends RealmObject{

    private String Content;
    private int ID;

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}


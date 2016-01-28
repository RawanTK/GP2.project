package com.it.mahaalrasheed.route;

import io.realm.RealmObject;

/**
 * Created by RawanTurki on 26-Jan-16.
 */
public class Notification extends RealmObject{

    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}


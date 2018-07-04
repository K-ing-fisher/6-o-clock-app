package com.example.leejinyeong.a6oclock.mail;

import io.realm.Realm;
import io.realm.RealmObject;

public class MailDB extends RealmObject {
    private String title;
    private String text;

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

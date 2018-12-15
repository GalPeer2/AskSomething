package com.peer.gal.asksomething;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gal on 12/15/2018.
 */

public class User {
    String name,password;
    ArrayList<String> myEmailAddresses;
    ArrayList<Question> myHistoryQuestions;





    public User()
    {

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<String> getMyEmailAddresses() {
        return myEmailAddresses;
    }

    public void setMyEmailAddresses(ArrayList<String> myEmailAddresses) {
        this.myEmailAddresses = myEmailAddresses;
    }

    public ArrayList<Question> getMyHistoryQuestions() {
        return myHistoryQuestions;
    }

    public void setMyHistoryQuestions(ArrayList<Question> myHistoryQuestions) {
        this.myHistoryQuestions = myHistoryQuestions;
    }
}

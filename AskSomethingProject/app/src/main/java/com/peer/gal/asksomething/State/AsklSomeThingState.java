package com.peer.gal.asksomething.State;

import com.peer.gal.asksomething.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Gal on 12/15/2018.
 */

public class AsklSomeThingState {

    public  AsklSomeThingState()
    {

    }

    private Map<String,User> dictionary=new HashMap<String, User>();
    String userName;

    public Map<String, User> getDictionary() {
        return dictionary;
    }

    public void setDictionary(Map<String, User> dictionary) {
        this.dictionary = dictionary;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}

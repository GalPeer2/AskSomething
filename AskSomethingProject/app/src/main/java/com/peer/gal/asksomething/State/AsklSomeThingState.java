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

    private List <User> userList;
    User thisUser;

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public User getThisUser() {
        return thisUser;
    }

    public void setThisUser(User thisUser) {
        this.thisUser = thisUser;
    }
}

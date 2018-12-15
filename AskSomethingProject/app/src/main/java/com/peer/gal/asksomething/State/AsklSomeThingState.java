package com.peer.gal.asksomething.State;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gal on 12/15/2018.
 */

public class AsklSomeThingState {

    public  AsklSomeThingState()
    {

    }

    private Map<String,String> map =  new HashMap<String,String>();


    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
}

package com.peer.gal.asksomething.State;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Gal on 12/15/2018.
 *
 * https://stackoverflow.com/questions/14376807/how-to-read-write-string-from-a-file-in-android
 */

public class StateMgr {


    private Context mContext;

    public StateMgr ( Context pContext)
    {
        mContext = pContext;
    }

    public void Init ()
    {

    }

    public AsklSomeThingState LoadState ()
    {

        File path = mContext.getFilesDir();

        File file = new File(path, "AsklSomeThingState.json");

        int length = (int) file.length();

        byte[] bytes = new byte[length];

        FileInputStream in = null;

        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            in.read(bytes);
            in.close();
        }
            catch (IOException e) {
                e.printStackTrace();
            }

        String jsonString = new String(bytes);

        Gson gson = new Gson();
        AsklSomeThingState myClass = gson.fromJson(jsonString, AsklSomeThingState.class);
        return myClass;


    }

    public void SaveState ( AsklSomeThingState pAsklSomeThingState) {
        Gson gson = new Gson();

        String thepAsklSomeThingStateasJson = gson.toJson(pAsklSomeThingState);

        File path = mContext.getFilesDir();

        File file = new File(path, "AsklSomeThingState.json");

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            stream.write(thepAsklSomeThingStateasJson.getBytes());

            stream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }



    }



}

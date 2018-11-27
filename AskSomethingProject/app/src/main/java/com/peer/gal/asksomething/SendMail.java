package com.peer.gal.asksomething;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

//https://stackoverflow.com/questions/18778240/solve-error-javax-mail-authenticationfailedexception

public class SendMail extends AppCompatActivity {
    ArrayList<String> MyAddresses = new ArrayList<String>();
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        MyAddresses.add("galpeer2@gmail.com");
        onClick(null);
     /*   tv = (TextView)findViewById(R.id.textView);

        //    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //      fab.setOnClickListener(new View.OnClickListener() {
        //        @Override
        //    public void onClick(View view) {
        //       Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //             .setAction("Action", null).show();

    //   });
            String theAddress = getLocalIpAddress();
        tv.setText(theAddress);

            Log.e("LOG_TAG", theAddress);


            AsyncHttpServer server = new AsyncHttpServer();
            //List<WebSocket> _sockets = new ArrayList<WebSocket>();

            server.get("/", new HttpServerRequestCallback() {
                @Override
                public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {
                    String youasked = request.getQuery().toString();


//                Multimap theQuery = request.getQuery();

//                Set<String> gt = theQuery.keySet();



                    List<String> Sendfor2 = request.getQuery().get("Sendfor");

                    String theNameOfTheUser = Sendfor2.get( 0 );

                    //              String SendFor = request.getQuery().getString("SendFor");





                    response.send("Hello Gal !!!" + youasked);
                }
            });

// listen on port 5000
            server.listen(5000);
// browsing http://localhost:5000 will return Hello!!!

        }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            Log.e("LOG_TAG", ex.toString());
        }
        return null;
    }*/
    }

    class Task implements Runnable {

        @Override

        public void run() {
            try {



/*
                GMailSender sender = new GMailSender("galpeerschool@gmail.com","----");
               */
//
                GMailSender sender = new GMailSender("zvikapeerm@gmail.com","---");

                sender.sendMail("This is Subject",
                        "This is Body",
                        "galpeerschool@gmail.com","zvikapeerm@gmail.com");

                int j = 6;

                j = 9;



            }

            catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }

        }
    }


    public void onClick(View v) {

            new Thread(new Task()).start();



    }
}

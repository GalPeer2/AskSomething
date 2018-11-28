package com.peer.gal.asksomething;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
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
//https://stackoverflow.com/questions/25547828/i-want-to-send-html-type-in-email-body-with-gmail-sender

public class SendMail extends AppCompatActivity {
    ArrayList<String> MyAddresses = new ArrayList<String>();
    TextView tv;
    String ans1, ans2, ans3, ans4, que;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        MyAddresses.add("galpeer2@gmail.com");
        SendTestEmail(null);

        StartHttpServer();

    }

    public void SendTestEmail(View v) {

        new Thread(new Task()).start();

    }

    protected void StartHttpServer() {
        String theAddress = getLocalIpAddress();

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

                String theNameOfTheUser = Sendfor2.get(0);

                List<String> answerlist = request.getQuery().get("answer");

                String theanswer = answerlist.get(0);

                response.send("Hello Gal !!!" + youasked+ " the question " + theanswer  );
            }
        });

        // listen on port 5000
        server.listen(5000);
        // browsing http://localhost:5000 will return Hello!!!

    }

    public String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
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
    }


    class Task implements Runnable {
        String bodyMail;

        @Override
        public void run() {
            try {
                String ans1, ans2, ans3, ans4, que;
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    ans1 = extras.getString("ans1");
                    ans2 = extras.getString("ans2");
                    ans3 = extras.getString("ans3");
                    ans4 = extras.getString("ans4");
                    que = extras.getString("que");

                }



/*
                GMailSender sender = new GMailSender("galpeerschool@gmail.com","----");
               */
//
                GMailSender sender = new GMailSender("zvikapeerm@gmail.com", "-------");

                // #m.setBody(Html.fromHtml("<h1>Title</h1><br>Username "+ username +" and password"));

                String theAddress = getLocalIpAddress();

                bodyMail = ("<h1>Title</h1><br>Click me to send <a href=\"http:" + theAddress + ":5000/?Sendfor=Botoshanski&answer=6\">Click her for answer 6 </a>");


                                /*
                        bodyMail = "Hello, you have been asked a question" + "\n"
                                + "~~~~~~" + que + "~~~~~~~~" + "\n"
                                + "please touch the suitable link according to your answer" + "\n" + "\n" + "\n" +
                                ans1 + "-----" + "this would be replaced by link" + "\n" +
                                ans2 + "-----" + "this would be replaced by link" + "\n" +
                                ans3 + "-----" + "this would be replaced by link" + "\n" +
                                ans4 + "-----" + "this would be replaced by link" + "\n" + "\n" + "\n" + "\n" +
                                "thank you";
*/
                sender.sendMail("question from AskSomething", bodyMail, "zvikapeerm@gmail.com", "galpeer2@gmail.com");

                int j = 6;

                j = 9;


            } catch (Exception e) {
                Log.e("SendMail", e.getMessage(), e);
            }

        }
    }

}





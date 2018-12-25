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
import com.peer.gal.asksomething.State.AsklSomeThingState;
import com.peer.gal.asksomething.State.StateMgr;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

//https://stackoverflow.com/questions/18778240/solve-error-javax-mail-authenticationfailedexception
//https://stackoverflow.com/questions/25547828/i-want-to-send-html-type-in-email-body-with-gmail-sender

//https://stackoverflow.com/questions/18778240/solve-error-javax-mail-authenticationfailedexception



public class SendMail extends AppCompatActivity {
    ArrayList<String> MyAddresses;
    User user;
    Question questionToUpload;
    StateMgr theStateMgr;
    AsklSomeThingState asklSomeThingState;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        theStateMgr = new StateMgr (this );
        asklSomeThingState= theStateMgr.LoadState();
        user=asklSomeThingState.getDictionary().get(asklSomeThingState.getUserName());

        MyAddresses=user.getMyEmailAddresses();
        MyAddresses.add("galpeer2@gmail.com");
        SendTestEmail(null);

        //StartHttpServer();

    }

    public void SendTestEmail(View v) {

        new Thread(new Task()).start();

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
            String ans1, ans2, ans3, ans4, que;
            que = "question";
            ans1 = "ans1";
            ans2 = "ans2";
            ans3 = "ans3";
            ans4 = "ans4";

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                ans1 = extras.getString("ans1");
                ans2 = extras.getString("ans2");
                ans3 = extras.getString("ans3");
                ans4 = extras.getString("ans4");
                que = extras.getString("que");

            }
            questionToUpload=new Question(que,ans1,ans2,ans3,ans4);
            user.getMyHistoryQuestions().add(questionToUpload);
            theStateMgr.SaveState(asklSomeThingState);


            for (String mailAddress : MyAddresses) {
                try {



/*
                GMailSender sender = new GMailSender("galpeerschool@gmail.com","----");
               */
//
                    GMailSender sender = new GMailSender("asksomethingsystem@gmail.com", "android666");

                    // m.setBody(Html.fromHtml("<h1>Title</h1><br>Username "+ username +" and password"));

                    String theAddress = getLocalIpAddress();

                    bodyMail = ("<h1>" + que + "</h1><br>Clickme to send: " +
                            "<br><a href=\"http://" + theAddress + ":5000/?Sendfor="+mailAddress+"&question="+que+"&sendfrom="+user.getName()+"&answer="+ans1+"\">"+ans1+"</a>"+
                           "<br><a href=\"http://" + theAddress + ":5000/?Sendfor="+mailAddress+"&question="+que+"&sendfrom="+user.getName()+"&answer="+ans2+"\">"+ans2+"</a>"+
                            "<br><a href=\"http://" + theAddress + ":5000/?Sendfor="+mailAddress+"&question="+que+"&sendfrom="+user.getName()+"&answer="+ans3+"\">"+ans3+"</a>"+
                           "<br><a href=\"http://" + theAddress + ":5000/?Sendfor="+mailAddress+"&question="+que+"&sendfrom="+user.getName()+"&answer="+ans4+"\">"+ans4+"</a>");

                    /*

*/

                    sender.sendMail("Question From AskSomething from "+ user.getName(), bodyMail, "asksomethingsystem@gmail.com", mailAddress);


                    int j = 6;

                    j = 9;


                } catch (Exception e) {
                    Log.e("SendMail", e.getMessage(), e);
                }

            }
        }
    }
    public Question getQuestionBytext(ArrayList<Question> questions,String theQuestionText)
    {
        for (Question question : questions)
        {
            if (question.getQue().equals(theQuestionText))
                return question;
        }
        return null;
    }

    public boolean containStringIn4Lists(ArrayList<String>a,ArrayList<String>b,ArrayList<String>c,ArrayList<String>d, String string)
    {
        for (String s:a)
        {
            if (s.equals(string))
                return true;
        }
        for (String s:b)
        {
            if (s.equals(string))
                return true;
        }
        for (String s:c)
        {
            if (s.equals(string))
                return true;
        }
        for (String s:d)
        {
            if (s.equals(string))
                return true;
        }
        return false;
    }


}





package com.peer.gal.asksomething;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

import com.koushikdutta.async.http.server.AsyncHttpServer;
import com.koushikdutta.async.http.server.AsyncHttpServerRequest;
import com.koushikdutta.async.http.server.AsyncHttpServerResponse;
import com.koushikdutta.async.http.server.HttpServerRequestCallback;
import com.peer.gal.asksomething.State.StateMgr;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import com.peer.gal.asksomething.State.AsklSomeThingState;
import com.peer.gal.asksomething.State.StateMgr;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class Listen2MailResultsService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_StartListen  = "com.peer.gal.asksomething.action.StartListen2Emails";
    //private static final String ACTION_BAZ = "com.peer.gal.asksomething.action.BAZ";

    // TODO: Rename parameters
    //private static final String EXTRA_PARAM1 = "com.peer.gal.asksomething.extra.PARAM1";
    //private static final String EXTRA_PARAM2 = "com.peer.gal.asksomething.extra.PARAM2";

    public Listen2MailResultsService() {
        super("Listen2MailResultsService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startAction(Context context, String param1, String param2) {
        Intent intent = new Intent(context, Listen2MailResultsService.class);
        intent.setAction(ACTION_StartListen);
        //intent.putExtra(EXTRA_PARAM1, param1);
        //intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    /*
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, Listen2MailResultsService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }
*/
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_StartListen.equals(action)) {
                //   final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                //   final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionStartListen2Emails();
            /*
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
            */
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionStartListen2Emails() {
        StartHttpServer ();
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

    private  StateMgr mStateMgr = new StateMgr(this);

    protected void StartHttpServer() {
        String theAddress = getLocalIpAddress();

        Log.e("LOG_TAG", theAddress);

        AsyncHttpServer server = new AsyncHttpServer();
        //List<WebSocket> _sockets = new ArrayList<WebSocket>();

        server.get("/", new HttpServerRequestCallback() {
            @Override
            public void onRequest(AsyncHttpServerRequest request, AsyncHttpServerResponse response) {

                AsklSomeThingState asklSomeThingState = mStateMgr.LoadState ();

                List<String> Sendfor2 = request.getQuery().get("Sendfor");

                String theNameOfTheUser = Sendfor2.get(0);

                List<String> answerlist = request.getQuery().get("answer");

                String theanswer = answerlist.get(0);

                List<String> questionlist = request.getQuery().get("question");

                String thequestion = questionlist.get(0);

                List<String> senderlist = request.getQuery().get("sendfrom");

                String theSender = questionlist.get(0);
               User user=asklSomeThingState.getDictionary().get(theSender);

                Question questionDealed = getQuestionBytext(user.getMyHistoryQuestions(),thequestion);

                String questionDeletedMeg =

                        "<!DOCTYPE html> \r\n" +
                                "<html>  \r\n " +
                                "<head> \r\n" +
                                "   <title>Error</title> \r\n " +
                                "   <meta charset=\"utf-8\" /> \r\n" +
                                "</head> \r\n " +
                                "<body> \r\n" +

                                "  <h1>Sorry, "+ user.getName()+"has deleted the question"+"</h1> \r\n" +

                                "</body> \r\n" +
                                "</html>";

                if (questionDealed==null)
                    response.send(questionDeletedMeg);
                else {
                    boolean shakran = containStringIn4Lists(questionDealed.getVotersForAns1(), questionDealed.getVotersForAns2()
                            , questionDealed.getVotersForAns3(), questionDealed.getVotersForAns4(), theNameOfTheUser);
                    String theThankAswere =

                            "<!DOCTYPE html> \r\n" +
                                    "<html>  \r\n " +
                                    "<head> \r\n" +
                                    "   <title>Thank you </title> \r\n " +
                                    "   <meta charset=\"utf-8\" /> \r\n" +
                                    "</head> \r\n " +
                                    "<body> \r\n" +

                                    "  <h1> "+user.getName()+" Thanks you for voting " + theanswer + "</h1> \r\n" +

                                    "</body> \r\n" +
                                    "</html>";
                    String allreadyVoted =
                            "<!DOCTYPE html> \r\n" +
                                    "<html>  \r\n " +
                                    "<head> \r\n" +
                                    "   <title>cheate trying!!! </title> \r\n " +
                                    "   <meta charset=\"utf-8\" /> \r\n" +
                                    "</head> \r\n " +
                                    "<body> \r\n" +
                                    "  <h1>You have allready voted!! "+user.getName() +" wont recieve your second answer" + "</h1> \r\n" +
                                    "</body> \r\n" +
                                    "</html>";

                    if (shakran == true)
                        response.send(allreadyVoted);
                    else {
                        response.send(theThankAswere);
                        switch (theanswer) {
                            case ("ans1"): {
                                questionDealed.getVotersForAns1().add(theNameOfTheUser);
                                break;
                            }
                            case ("ans2"): {
                                questionDealed.getVotersForAns2().add(theNameOfTheUser);
                                break;
                            }
                            case ("ans3"): {
                                questionDealed.getVotersForAns3().add(theNameOfTheUser);
                                break;
                            }
                            case ("ans4"): {
                                questionDealed.getVotersForAns4().add(theNameOfTheUser);
                                break;
                            }
                        }
                    }
                    mStateMgr.SaveState(asklSomeThingState);
                }


            }
        });

        // listen on port 5000
        server.listen(5000);
        // browsing http://localhost:5000 will return Hello!!!

    }

    public Question getQuestionBytext(ArrayList<Question> questions, String theQuestionText)
    {
        for (Question question : questions)
        {
            if (question.getQue().equals(theQuestionText))
                return question;
        }
        return null;//case not found question
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

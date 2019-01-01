package com.peer.gal.asksomething;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class InvelopeActivity extends AppCompatActivity {

    Invelope invelope;
    InvelopeView invelopView;
    InvelopeMover invelopeMover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        invelope = new Invelope(this.getWindowManager().getDefaultDisplay().getWidth()
                , this.getWindowManager().getDefaultDisplay().getHeight());
        invelopView = new InvelopeView(this);
        invelopView.setInvelope(invelope);
        setContentView(invelopView);
        invelopeMover = new InvelopeMover(invelope, invelopView);
        invelopeMover.start();
     //   new Thread(new MoveNextChecker(invelope)).start();
        startTheCheckingForEnvelopeArrive();
        //   startActivity(new Intent(InvelopeActivity.this,MainActivity.class));
    }

        public void startTheCheckingForEnvelopeArrive() {

            new Thread(new MoveNextChecker(invelope)).start();

        }

        class MoveNextChecker implements Runnable {
            Invelope invelope;
            public MoveNextChecker(Invelope invelope)
            {
                this.invelope=invelope;
            }
            @Override
            public void run() {
                while (true)
                {
                    if (invelope.getPoints()[2].y<0) {
                        Toast.makeText(InvelopeActivity.this,"arrive end",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(InvelopeActivity.this, MainActivity.class));
                    }
            }
              //  startActivity(new Intent(InvelopeActivity.this,MainActivity.class));

            }
        }




    }


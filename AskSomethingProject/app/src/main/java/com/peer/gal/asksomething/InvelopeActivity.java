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
    }

        /*

        class MoveNextChecker extends Thread{
            Invelope invelope;
            public MoveNextChecker(Invelope invelope)
            {
                this.invelope=invelope;
            }
            @Override
            public void run() {
                while (true)
                {
                    try {
                        MoveNextChecker.sleep(2);
                    }
                    catch (InterruptedException e) {
                    e.printStackTrace();
                    }

                    if (invelope.getPoints()[2].y<0) {
                        Toast.makeText(InvelopeActivity.this,"arrive end",Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(InvelopeActivity.this, MainActivity.class));
                    }
            }
              //  startActivity(new Intent(InvelopeActivity.this,MainActivity.class));

            }
        }
        */


    class InvelopeMover extends Thread {
        private Invelope theInvelope;
        private InvelopeView invelopView;

        public InvelopeMover(Invelope theInvelope, InvelopeView invelopView) {
            this.theInvelope = theInvelope;
            this.invelopView = invelopView;
        }

        @Override
        public void run() {
            super.run();
            while (theInvelope.getPoints()[2].y > -20) {
                this.theInvelope.moveTheEnvelope();
                this.invelopView.postInvalidate();


                try {
                    InvelopeMover.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();

                }
            }
        //    Toast.makeText(InvelopeActivity.this,"Question sent",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(InvelopeActivity.this,MainActivity.class));

        }
    }
}


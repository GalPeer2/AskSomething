package com.peer.gal.asksomething;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class InvelopeActivity extends AppCompatActivity {

    Invelope invelope;
    InvelopeView invelopView;
    InvelopeMover invelopeMover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        invelope=new Invelope(this.getWindowManager().getDefaultDisplay().getWidth()
                ,this.getWindowManager().getDefaultDisplay().getHeight());
        invelope=new Invelope(1500,2000);/////////////////////////
        invelopView=new InvelopeView(this);
        invelopView.setInvelope(invelope);
        setContentView(invelopView);
        invelopeMover=new InvelopeMover(invelope,invelopView);
        invelopeMover.start();
    }
}

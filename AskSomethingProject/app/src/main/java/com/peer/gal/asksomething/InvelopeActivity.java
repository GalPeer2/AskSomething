package com.peer.gal.asksomething;

import android.content.Intent;
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
        invelopView=new InvelopeView(this);
        invelopView.setInvelope(invelope);
        setContentView(invelopView);
        invelopeMover=new InvelopeMover(invelope,invelopView);
        invelopeMover.start();
        while (invelope.getPoints()[2].y>0)
        {

        }
     //   startActivity(new Intent(InvelopeActivity.this,MainActivity.class));

    }
}

package com.peer.gal.asksomething;

/**
 * Created by Gal on 12/25/2018.
 */

public class InvelopeMover extends Thread {
    private Invelope theInvelope;
    private InvelopeView invelopView;

    public InvelopeMover(Invelope theInvelope, InvelopeView invelopView) {
        this.theInvelope = theInvelope;
        this.invelopView = invelopView;
    }

    @Override
    public void run() {
        super.run();
        while (theInvelope.getPoints()[2].y > -19) {
            this.theInvelope.moveTheEnvelope();
            this.invelopView.postInvalidate();


            try {
                InvelopeMover.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();

            }
        }
    }
}

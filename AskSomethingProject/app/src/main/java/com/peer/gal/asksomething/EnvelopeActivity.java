package com.peer.gal.asksomething;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Gal on 12/25/2018.
 */

public class EnvelopeActivity extends Activity {
    Invelope invelope;
    InvelopeView invelopView;
    InvelopeMover invelopeMover;

    public EnvelopeActivity()
    {}


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        invelope=new Invelope(this.getWindowManager().getDefaultDisplay().getWidth()
                ,this.getWindowManager().getDefaultDisplay().getHeight());
        invelopView=new InvelopeView(this);
        invelopView.setInvelope(invelope);
        setContentView(invelopView);
        invelopeMover=new InvelopeMover(invelope,invelopView);
        invelopeMover.start();
    }


    /**
     * Created by Gal on 12/25/2018.
     */
/*
    public static class InvelopView extends View {
        Invelope invelope;
        Paint paint;

        public void setInvelope(Invelope invelope) {
            this.invelope = invelope;
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            for (int i = 0; i < 4; i++) {
                canvas.drawLine(invelope.getPoints()[i].x, invelope.getPoints()[i].y
                        , invelope.getPoints()[i + 1].x, invelope.getPoints()[i + 1].y, paint);
            }
            canvas.drawLine(invelope.getPoints()[1].x, invelope.getPoints()[1].y
                    , invelope.getPoints()[4].x, invelope.getPoints()[4].y, paint);


        }

        public InvelopView(Context context) {
            super(context);
            paint = new Paint();
            paint.setStrokeWidth(3);
            paint.setColor(Color.BLUE);


        }
        */
        /*
        class Invelope{
            private int maxWidth;
            private int maxHeight;
            private Point [] points =new Point[5];
            public Point[] getPoints() {
                return points;
            }

            public Invelope(int maxWidth, int maxHeight)
            {
                this.maxHeight=maxHeight;
                this.maxWidth=maxWidth;
                points[0]=new Point(1/10*maxWidth,3/4*maxHeight);//7.5/10 the top
                points[1]=new Point(1/5*maxWidth,8/10*maxHeight);  //left up
                points[2]=new Point(1/5*maxWidth,9/10*maxHeight); // left down
                points[3]=new Point(0,9/10*maxHeight);         //right down
                points[4]=new Point(0,8/10*maxHeight);           //right up
            }
            public void moveTheEnvelope()
            {
                if (points[1].x<=3/5*maxWidth) {
                    for (Point point : points)
                        point.x++;
                    return ;

                }
                if (points[0].y-points[1].y<maxHeight/20)
                {
                    points[0].y++;
                    return;
                }
                if (points[1].y>0)
                {
                    for (Point point : points)
                        point.y--;
                    return;
                }
            }
            */


        /*
        class com.peer.gal.asksomething.InvelopeMover extends Thread
        {
            private Invelope theInvelope;
            private com.peer.gal.asksomething.EnvelopeActivity.InvelopView invelopView;

            public com.peer.gal.asksomething.InvelopeMover(Invelope theInvelope,com.peer.gal.asksomething.EnvelopeActivity.InvelopView invelopView) {
                this.theInvelope = theInvelope;
                this.invelopView = invelopView;
            }

                @Override
                public void run() {
                    while (theInvelope.getPoints()[1].y>1) {
                        this.theInvelope.moveTheEnvelope();
                        this.invelopView.postInvalidate();

                        try {
                            com.peer.gal.asksomething.InvelopeMover.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();

                        }

                    }
                }
        }

         */


}

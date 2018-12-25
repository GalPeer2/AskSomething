package com.peer.gal.asksomething;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Gal on 12/25/2018.
 */

public class InvelopeView extends View {

    Invelope invelope;
    Paint paint;
    Bitmap note;

    public void setInvelope(Invelope invelope) {
        this.invelope = invelope;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(note,null,invelope.getRectangle(),null);
        for (int i = 0; i < 5; i++) {
            int j =i+1;
            if (j==5)j=0;
            canvas.drawLine(invelope.getPoints()[i].x, invelope.getPoints()[i].y
                    , invelope.getPoints()[j].x, invelope.getPoints()[j].y, paint);
        }
        canvas.drawLine(invelope.getPoints()[1].x, invelope.getPoints()[1].y
                , invelope.getPoints()[4].x, invelope.getPoints()[4].y, paint);



    }

    public InvelopeView(Context context) {
        super(context);
        paint = new Paint();
        paint.setStrokeWidth(13);
        paint.setColor(Color.BLUE);
        note = BitmapFactory.decodeResource(context.getResources(),R.drawable.thebitmap);


    }
}

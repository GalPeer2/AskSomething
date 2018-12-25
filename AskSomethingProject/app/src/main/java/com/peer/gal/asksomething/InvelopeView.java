package com.peer.gal.asksomething;

import android.content.Context;
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

    public InvelopeView(Context context) {
        super(context);
        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLUE);


    }
}

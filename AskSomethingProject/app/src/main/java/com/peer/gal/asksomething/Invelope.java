package com.peer.gal.asksomething;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Gal on 12/25/2018.
 */

public class Invelope {
    private int maxWidth;
    private int maxHeight;
    private Point[] points =new Point[5];
    public Point[] getPoints() {
        return points;
    }
    Rect rectangle;


    public Invelope(int maxWidth, int maxHeight)
    {
        this.maxHeight=maxHeight;
        this.maxWidth=maxWidth;
        points[0]=new Point(2*(maxWidth/10),12*(maxHeight/20));//7.5/10 the top
        points[1]=new Point(2*(maxWidth/5),15*(maxHeight/20));  //left up
        points[2]=new Point(2*(maxWidth/5),19*(maxHeight/20)); // left down
        points[3]=new Point(0,19*(maxHeight/20));         //right down
        points[4]=new Point(0,15*(maxHeight/20));           //right up
        rectangle=new Rect((20*maxWidth)/20,(11*maxHeight)/40,(26*maxWidth)/20,(18*maxHeight)/40);
    }

    public Rect getRectangle() {
        return rectangle;
    }

    public void moveTheEnvelope()
    {
        if (points[1].x<=(7*maxWidth/10)) {
            for (Point point : points)
               point.x=point.x+2;
            return ;

        }
        if (rectangle.left>points[3].x+(maxWidth/30))
        {
            rectangle.left=rectangle.left-6;
            rectangle.right=rectangle.right-6;
            return;
        }

        if (rectangle.bottom<points[2].y-maxHeight/60)
        {
            rectangle.top=rectangle.top+5;
            rectangle.bottom=rectangle.bottom+5;
            return;
        }
        if (points[0].y-points[1].y<3*(maxHeight/20))
        {
            points[0].y++;
            return;
        }
        if (points[2].y>-20)
        {
            for (Point point : points)
                point.y=point.y-6;
            rectangle.top=rectangle.top-6;
            rectangle.bottom=rectangle.bottom-6;
            return;
        }
    }
}

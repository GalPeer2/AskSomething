package com.peer.gal.asksomething;

import android.graphics.Point;

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

    public Invelope(int maxWidth, int maxHeight)
    {
        this.maxHeight=maxHeight;
        this.maxWidth=maxWidth;
        points[0]=new Point(1*(maxWidth/10),3*(maxHeight/4));//7.5/10 the top
        points[1]=new Point(1*(maxWidth/5),8*(maxHeight/10));  //left up
        points[2]=new Point(1*(maxWidth/5),9*(maxHeight/10)); // left down
        points[3]=new Point(0,9*(maxHeight/10));         //right down
        points[4]=new Point(0,8*(maxHeight/10));           //right up
    }
    public void moveTheEnvelope()
    {
        if (points[1].x<=3*(maxWidth/5)) {
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
}

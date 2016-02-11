package com.example.nicka.myapplication;

/**
 * Created by Nicka on 2/8/2016.
 */

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class FaceSurface extends SurfaceView{

    private Path path1 = new Path();
    private Path path2 = new Path();
    Path path3 = new Path();

    public FaceSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotCacheDrawing(false);

    }

    /*
     * onDraw method handles canvas and drawing (like paint() in java)
     */
    @Override
    public void onDraw(Canvas canvas){
        super.onDraw(canvas);

        Paint background = new Paint();


    }



}

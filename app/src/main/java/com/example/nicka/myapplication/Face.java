/**
 * This is a surface view class that draws the face and features onto the screen.
 *
 * @author Nicholas Scacciotti
 * @date 2/7/16
 *
 * Helper methods
 *      drawHair: When called, draws the hair style that corresponds with the
 *          selected index value
 *
 *      drawNose: When called, draws the nose style that corresponds with the
 *          selected index value
 *
 *      drawEyes: When called, draws the eye style that corresponds with the
 *          selected index value
 *
 *      randomize: Sets random variables for Face class
 */

package com.example.nicka.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceView;
import java.util.Random;

/**
 *  Face SurfaceView for face app
 */
public class Face extends SurfaceView {

    //initializes all vairables used for Face class
    public int skinColorR;
    public int eyeColorR;
    public int hairColorR;
    public int skinColorG;
    public int eyeColorG;
    public int hairColorG;
    public int skinColorB;
    public int eyeColorB;
    public int hairColorB;
    Path hair1 = new Path();
    Path hair2 = new Path();
    Path hair3 = new Path();
    Path hairStyles[] = {hair1, hair2, hair3 };
    int hairStylesIndex = 0;
    int eyeStyle = 0;
    Path eye1 = new Path();
    Path eye3 = new Path();
    Path nose1 = new Path();
    Path nose2 = new Path();
    int noseStyle = 0;
    Paint face = new Paint();
    Paint hair = new Paint();
    Paint eye = new Paint();

    /*
     * Face class constructor, runs randomize() method
     *      to set initialized values to random values
     */
    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotCacheDrawing(false);
        randomize();
    }

    /*
     * onDraw - draws the face and runs the methods for drawing
     *      the hair, eyes, and nose
     */
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        face.setARGB(255, this.skinColorR, this.skinColorG, this.skinColorB);
        canvas.drawOval(500, 150, 1250, 725, face);

        drawHair(canvas);
        drawEye(canvas);
        drawNose(canvas);

    }
    /*
     * drawHair - creates three hair Paths and draw one based on
     *      current style selected
    */
    public void drawHair(Canvas canvas){
        hair.setARGB(255, hairColorR, hairColorG, hairColorB);

        hair1.moveTo(510, 380); //saiyan
        hair1.lineTo(500, 50);
        hair1.lineTo(600, 210);
        hair1.lineTo(700, 25);
        hair1.lineTo(800, 140);
        hair1.lineTo(950, 65);
        hair1.lineTo(1000, 140);
        hair1.lineTo(1050, 25);
        hair1.lineTo(1150, 210);
        hair1.lineTo(1250, 50);
        hair1.lineTo(1240, 380);
        hair1.lineTo(980, 190);

        hair2.moveTo(510, 380); //trump
        hair2.lineTo(600, 85);
        hair2.lineTo(1150, 25);
        hair2.lineTo(900, 75);
        hair2.lineTo(1500, 60);
        hair2.lineTo(1200, 160);
        hair2.lineTo(1300, 380);
        hair2.lineTo(1000, 180);
        hair2.lineTo(750, 250);

        hair3.moveTo(510, 380); //marine
        hair3.lineTo(510, 130);
        hair3.lineTo(1250, 130);
        hair3.lineTo(1250, 380);
        hair3.lineTo(1000, 200);
        hair3.lineTo(800, 200);

        canvas.drawPath(hairStyles[hairStylesIndex], hair);


    }

    /*
     * drawEye - creates two eye Paths, and two ovals and draw one
     *      based on current style selected
    */
    public void drawEye(Canvas canvas){
        eye.setARGB(255, this.eyeColorR, this.eyeColorG, this.eyeColorB);
        canvas.drawOval(675, 410, 725, 470, eye);
        canvas.drawOval(1000, 410, 1050, 470, eye);

        Paint eyeLine = new Paint();
        eyeLine.setColor(0xFF000000);
        eyeLine.setStyle(Paint.Style.STROKE);
        eyeLine.setStrokeWidth(3);

        eye1.moveTo(600, 350); //left
        eye1.lineTo(750, 400);
        eye1.lineTo(725, 500);
        eye1.lineTo(625, 470);
        eye1.lineTo(600, 350);
        eye1.moveTo(1125, 350);//right
        eye1.lineTo(975, 400);
        eye1.lineTo(1000, 500);
        eye1.lineTo(1100, 470);
        eye1.lineTo(1125, 350);

        eye3.moveTo(650, 500);//left
        eye3.arcTo(650, 375, 750, 510, 180, 180, false);
        eye3.lineTo(750, 500);
        eye3.moveTo(650, 500);
        eye3.arcTo(650, 472, 750, 510, 180, 180, false);
        eye3.moveTo(975, 500);//right
        eye3.arcTo(975, 375, 1075, 510, 180, 180, false);
        eye3.lineTo(1075, 500);
        eye3.moveTo(975, 500);
        eye3.arcTo(975, 472, 1075, 510, 180, 180, false);

        if(eyeStyle == 0){
            canvas.drawPath(eye1, eyeLine);
        }
        else if(eyeStyle == 1) {
            //eye2
            canvas.drawOval(625, 385, 750, 495, eyeLine); //left
            canvas.drawOval(975, 385, 1100, 495, eyeLine); //right
        }
        else if(eyeStyle == 2){
            canvas.drawPath(eye3, eyeLine);
        }
    }

    /*
     * drawNose - creates two nose Paths, and two ovals and draws
     *      one based on current style selected
    */
    public void drawNose(Canvas canvas) {
        Paint noseLine = new Paint();
        noseLine.setARGB(200, 4, 5, 6);
        noseLine.setStyle(Paint.Style.STROKE);
        noseLine.setStrokeWidth(3);

        nose1.moveTo(812, 550); //triangular
        nose1.lineTo(862.5f, 450);
        nose1.lineTo(912, 550);
        nose1.lineTo(862.5f, 545);
        nose1.lineTo(812, 550);

        nose2.moveTo(812, 520); //rounded
        nose2.arcTo(812, 450, 912, 550, 180, 180, false);
        nose2.lineTo(912, 535);
        nose2.moveTo(812, 520);
        nose2.arcTo(812, 520, 862.5f, 550, 180, 180, false);
        nose2.arcTo(862.5f, 520, 912, 550, 180, 180, false);

        if (noseStyle == 0) {
            canvas.drawPath(nose1, noseLine);
        } else if (noseStyle == 1) {
            canvas.drawPath(nose2, noseLine);
        } else if (noseStyle == 2) {
            canvas.drawOval(845, 420, 855, 550, noseLine); //He Who Must Not Be Named
            canvas.drawOval(870, 420, 880, 550, noseLine);
        }
    }

    /*
     *  randomize - puts random values for all variables
     */
    public void randomize(){
        Random rand = new Random();

        skinColorR = rand.nextInt(256);
        eyeColorR = rand.nextInt(256);
        hairColorR = rand.nextInt(256);
        skinColorG = rand.nextInt(256);
        eyeColorG = rand.nextInt(256);
        hairColorG = rand.nextInt(256);
        skinColorB = rand.nextInt(256);
        eyeColorB = rand.nextInt(256);
        hairColorB = rand.nextInt(256);

        hairStylesIndex = rand.nextInt(3);
        noseStyle = rand.nextInt(3);
        eyeStyle = rand.nextInt(3);
        //onDraw method needs to be redrawn
        invalidate();
    }
}


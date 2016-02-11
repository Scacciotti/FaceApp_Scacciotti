/**
 * This is main class that implements the Face SurfaceView class and interface.
 *
 * @author Nicholas Scacciotti
 * @date 2/7/16
 *
 * Helper Methods
 *
 *      addSpinnerItems: Establishes items in the spinners for hair, nose, and
 *          eye styles.  Main code taken from external source, see citation below.
 */

package com.example.nicka.myapplication;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.ArrayList;
import java.util.List;

/*
 * MainActivity class for face app
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        SeekBar.OnSeekBarChangeListener, Spinner.OnItemSelectedListener {
    /*
     * GUI variables
     */
    protected SeekBar barBlue = null;
    protected SeekBar barGreen = null;
    protected SeekBar barRed = null;
    protected TextView valBlue = null;
    protected TextView valGreen = null;
    protected TextView valRed = null;
    protected Face faceView;
    protected RadioButton radioHair;
    protected RadioButton radioEyes;
    protected RadioButton radioSkin;
    protected Spinner hairSpin;
    protected Spinner eyeSpin;
    protected Spinner noseSpin;
    protected Button random;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * onCreate inflates the GUI, initializes the instance variables and sets up
     * this activity as a click listener
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initializes user inputted items
        barRed = (SeekBar) findViewById(R.id.redBar);
        barRed.setOnSeekBarChangeListener(this);
        barBlue = (SeekBar) findViewById(R.id.blueBar);
        barBlue.setOnSeekBarChangeListener(this);
        barGreen = (SeekBar) findViewById(R.id.greenBar);
        barGreen.setOnSeekBarChangeListener(this);

        valBlue = (TextView) findViewById(R.id.blueVal);
        valGreen = (TextView) findViewById(R.id.greenVal);
        valRed = (TextView) findViewById(R.id.redVal);

        random = (Button)findViewById(R.id.randomButton);
        random.setOnClickListener(this);

        faceView = (Face) findViewById(R.id.faceSurfView);

        radioEyes = (RadioButton) findViewById(R.id.eyesRadio);
        radioEyes.setOnClickListener(this);
        radioHair = (RadioButton) findViewById(R.id.hairRadio);
        radioHair.setOnClickListener(this);
        radioSkin = (RadioButton) findViewById(R.id.skinRadio);
        radioSkin.setOnClickListener(this);

        addSpinnerItems();
        //sets the color bars to current location
        int red = faceView.hairColorR;
        int green = faceView.hairColorG;
        int blue = faceView.hairColorB;
        barBlue.setProgress(blue);
        barRed.setProgress(red);
        barGreen.setProgress(green);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * The click handler for the buttons.
     */
    @Override
    public void onClick(View v) {
        //sets holders for current and changed values
        //for updating seekbar thumbs
        int red = barRed.getProgress();
        int green = barGreen.getProgress();
        int blue = barBlue.getProgress();
        if (v.getId() == R.id.skinRadio) {
            red =faceView.skinColorR;
            green = faceView.skinColorG;
            blue = faceView.skinColorB;
        }
        else if (v.getId() == R.id.hairRadio) {
            red = faceView.hairColorR;
            green = faceView.hairColorG;
            blue = faceView.hairColorB;
        }
        else if (v.getId() == R.id.eyesRadio) {
            red = faceView.eyeColorR;
            green = faceView.eyeColorG;
            blue = faceView.eyeColorB;
        }
        barBlue.setProgress(blue);
        barRed.setProgress(red);
        barGreen.setProgress(green);

        //implements the randomize method from Face class
        if(v.getId() == R.id.randomButton){
            faceView.randomize();
        }
    }

    /*
     *   onProgressChanged - Used to edit the displayed color value,
     *      and set the appropriate color variables to the current values
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        int redVal = 0;
        int greenVal = 0;
        int blueVal = 0;
        if (seekBar == this.barBlue) {
            this.valBlue.setText("" + progress);
            blueVal = progress;
            greenVal = barGreen.getProgress();
            redVal = barRed.getProgress();
        }
        else if (seekBar == this.barRed) {
            this.valRed.setText("" + progress);
            redVal = progress;
            greenVal = barGreen.getProgress(); //Integer.parseInt(valGreen.toString());
            blueVal = barBlue.getProgress(); //Integer.parseInt(valBlue.toString());
        }
        else if (seekBar == this.barGreen) {
            this.valGreen.setText("" + progress);
            greenVal = progress;
            blueVal = barBlue.getProgress(); //Integer.parseInt(valBlue.toString());
            redVal = barRed.getProgress(); //Integer.parseInt(valRed.toString());
        }

        if (radioSkin.isChecked()) {
            faceView.skinColorR = redVal;
            faceView.skinColorG = greenVal;
            faceView.skinColorB = blueVal;
        }
        else if (radioHair.isChecked()) {
            faceView.hairColorR = redVal;
            faceView.hairColorG = greenVal;
            faceView.hairColorB = blueVal;
        }
        else if (radioEyes.isChecked()) {
            faceView.eyeColorR = redVal;
            faceView.eyeColorG = greenVal;
            faceView.eyeColorB = blueVal;
        }
        //The onDraw() method needs to be redrawn
        faceView.invalidate();
    }

    /*
     * Method is not used
     */
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    /*
     * Method is not used
     */
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    /*
     * Method is not used
     */
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.nicka.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    /*
     * Method is not used
     */
    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.nicka.myapplication/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /*
            Method that establishes the Spinners' menus
     */
    /*
        External Citation
        Date: 8 Feb 2016
        Problem: Couldn't figure out how to properly establish the lists for Spinners
        Resource: www.mkyong.com/android/android-spinner-drop-down-list-example/
        Solution: I modified and used the example code from this post.
     */
    public void addSpinnerItems(){
        hairSpin = (Spinner) findViewById(R.id.hairSpinner);
        hairSpin.setOnItemSelectedListener(this);
        eyeSpin = (Spinner) findViewById(R.id.eyeSpinner);
        eyeSpin.setOnItemSelectedListener(this);
        noseSpin = (Spinner) findViewById(R.id.noseSpinner);
        noseSpin.setOnItemSelectedListener(this);

        List<String> hairList = new ArrayList<String>();
        hairList.add("Saiyan");
        hairList.add("Trump");
        hairList.add("Marine");

        List<String> eyeList = new ArrayList<String>();
        eyeList.add("Angry");
        eyeList.add("Oval");
        eyeList.add("Wierd");

        List<String> noseList = new ArrayList<String>();
        noseList.add("Triangular");
        noseList.add("Rounded");
        noseList.add("Voldemort");

        ArrayAdapter<String> dataAdapH = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, hairList);
        dataAdapH.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSpin.setAdapter(dataAdapH);

        ArrayAdapter<String> dataAdapN = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, noseList);
        dataAdapN.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        noseSpin.setAdapter(dataAdapN);

        ArrayAdapter<String> dataAdapE = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, eyeList);
        dataAdapE.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eyeSpin.setAdapter(dataAdapE);

        hairSpin.setSelection(faceView.hairStylesIndex);
        noseSpin.setSelection(faceView.noseStyle);
        eyeSpin.setSelection(faceView.eyeStyle);
    }

    /*
     *  onItemSelected - changes the index of the selected facial feature
     *      based on the new style chosen
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent == hairSpin && parent.getItemAtPosition(position).toString().equals("Saiyan")){
            faceView.hairStylesIndex = 0;
        }
        else if(parent == hairSpin && parent.getItemAtPosition(position).toString().equals("Trump")){
            faceView.hairStylesIndex = 1;
        }
        else if(parent == hairSpin && parent.getItemAtPosition(position).toString().equals("Marine")){
            faceView.hairStylesIndex = 2;
        }
        else if(parent == eyeSpin && parent.getItemAtPosition(position).toString().equals("Angry")){
            faceView.eyeStyle = 0;
        }
        else if(parent == eyeSpin && parent.getItemAtPosition(position).toString().equals("Oval")){
            faceView.eyeStyle = 1;
        }
        else if(parent == eyeSpin && parent.getItemAtPosition(position).toString().equals("Wierd")){
            faceView.eyeStyle = 2;
        }
        else if(parent == noseSpin && parent.getItemAtPosition(position).toString().equals("Triangular")){
            faceView.noseStyle = 0;
        }
        else if(parent == noseSpin && parent.getItemAtPosition(position).toString().equals("Rounded")){
            faceView.noseStyle = 1;
        }
        else if(parent == noseSpin && parent.getItemAtPosition(position).toString().equals("Voldemort")){
            faceView.noseStyle = 2;
        }
        //onDraw method needs to be redrawn
        faceView.invalidate();
    }

    /*
     * Method is not used
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
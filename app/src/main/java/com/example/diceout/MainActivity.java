package com.example.diceout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Images
    private ImageView IntroImage, dieOneImg, dieTwoImg, dieTreeImg;

    //Score counter
    private int score;

    //Holds game score (text field)
    private TextView infoTxt, scoreTxt, helpTxt;

    //Roll button
    //private Button rollBtn;

    //Random object
    private static Random rnd;

    //Die value
    private final int numOfDice = 3;
    int[] die;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rollDice(view);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //       .setAction("Action", null).show();
            }
        });

        this.setImages(); //Display intro image only
        rnd = new Random(); //Random number object

        //ArrayList object with 3 integers inside
        die = new int[numOfDice];

        score = 0; //Initial score value
        infoTxt = (TextView) findViewById(R.id.infoTxt); //Display score
        scoreTxt = (TextView) findViewById(R.id.scoreTxt); //Display info
        helpTxt = (TextView) findViewById(R.id.helpTxt); //Help text
        //rollBtn = (Button) findViewById(R.id.rollBtn); //Instantiate Roll button

        //Shows welcome toast message
        Toast.makeText(this.getApplicationContext(), "Wellcome to DiceOut game!", Toast.LENGTH_SHORT).show();
    }

    //Generates random number
    private static int rndNum(){
        return rnd.nextInt(6) + 1;
    }

    //Roll dice method
    public void rollDice(View v){

        helpTxt.setVisibility(View.INVISIBLE);
        IntroImage.setVisibility(View.INVISIBLE);
        //scoreTxt.setText(score); //BUG - this code is wrong, app is crushes on clickBtn event

        for(int i = 0; i < numOfDice; i++){
            die[i] = rndNum();
        }

        setDieImg(die[0], dieOneImg);
        setDieImg(die[1], dieTwoImg);
        setDieImg(die[2], dieTreeImg);

        int newScore = setScore(die[0], die[1], die[2]);
        score = score + newScore;

        if(newScore > 0){

            Toast.makeText(this.getApplicationContext(), "Congratulation, you won " + newScore + " points!", Toast.LENGTH_SHORT).show();
        }

        scoreTxt.setText(String.format("Score: %d", score));

        String message = String.format("You rolled a %d - %d - %d", die[0], die[1], die[2]);
        infoTxt.setText(message);
    }

    //Return points
    private static int setScore(int dieOne, int dieTwo, int dieTree){

        int output = 0;

        /*
            If you get a matching pair you score 50 points.
            If you get three of a kind, you'll score based on the combination,
            100 for three ones, 200 for three twos, 300 for three threes, et criteria.
        */

        if(dieOne == dieTwo && dieOne == dieTree){

            switch(dieOne){

                case(1):
                    output = 100;
                    break;
                case(2):
                    output = 200;
                    break;
                case(3):
                    output = 300;
                    break;
                case(4):
                    output = 400;
                    break;
                case(5):
                    output = 500;
                    break;
                case(6):
                    output = 600;
                    break;
            }
        }
        else if(dieOne == dieTwo || dieOne == dieTree || dieTwo == dieTree){

            output = 50;
        }

        return output;
    }

    //Set die image
    private void setDieImg(int num, ImageView view){

        //Prefix and postfix for image name
        String imageName = "";
        String prefix = "dice_";
        String postfix = ".png";

        switch(num){

            case 1:
                imageName = prefix + "one" + postfix;
                break;
            case 2:
                imageName = prefix + "two" + postfix;
                break;
            case 3:
                imageName = prefix + "tree" + postfix;
                break;
            case 4:
                imageName = prefix + "four" + postfix;
                break;
            case 5:
                imageName = prefix + "five" + postfix;
                break;
            case 6:
                imageName = prefix + "six" + postfix;
                break;
            default:
                break;
        }

        try{

            InputStream stream = getAssets().open(imageName);
            Drawable d = Drawable.createFromStream(stream, null);
            view.setImageDrawable(d);

            //view.setImageDrawable(android.graphics.);
            view.setVisibility(View.VISIBLE);
        }
        catch(IOException e){
            //Error
            e.printStackTrace();
        }

    }

    //Display intro image only
    public void setImages(){

        IntroImage = (ImageView)findViewById(R.id.IntroImage);
        IntroImage.setVisibility(View.VISIBLE);
        dieOneImg = (ImageView)findViewById(R.id.dieOneImg);
        dieOneImg.setVisibility(View.INVISIBLE);
        dieTwoImg = (ImageView)findViewById(R.id.dieTwoImg);
        dieTwoImg.setVisibility(View.INVISIBLE);
        dieTreeImg = (ImageView)findViewById(R.id.dieTreeImg);
        dieTreeImg.setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

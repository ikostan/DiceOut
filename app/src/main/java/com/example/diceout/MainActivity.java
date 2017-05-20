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
    private TextView scoreTxt;

    //Roll button
    private Button rollBtn;

    //Random object
    private static Random rnd;

    //Die value
    private final int numOfDice = 3;
    int[] dice;

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        this.setImages(); //Display intro image only
        rnd = new Random(); //Random number object

        //ArrayList object with 3 integers inside
        dice = new int[numOfDice];

        score = 0; //Initial score value
        scoreTxt = (TextView) findViewById(R.id.scoreTxt); //Display score
        rollBtn = (Button) findViewById(R.id.rollBtn); //Instantiate Roll button

        //Shows welcome toast message
        Toast.makeText(this.getApplicationContext(), "Wellcome to DiceOut game!", Toast.LENGTH_SHORT).show();
    }

    //Generates random number
    private static int rndNum(){
        return rnd.nextInt(6) + 1;
    }

    //Roll dice method
    public void rollDice(View v){

        IntroImage.setVisibility(View.INVISIBLE);
        scoreTxt.setText(String.format("Score: %d", score));
        //scoreTxt.setText(score); //BUG - this code is wrong, app is crushes on clickBtn event

        for(int i = 0; i < numOfDice; i++){
            dice[i] = rndNum();
        }

        setDieImg(dice[0], dieOneImg);
        setDieImg(dice[1], dieTwoImg);
        setDieImg(dice[2], dieTreeImg);

        String message = String.format("You rolled a %d - %d - %d", dice[0], dice[1], dice[2]);
        scoreTxt.setText(message);
        //Toast.makeText(this.getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();
    }

    //Set die image
    private void setDieImg(int num, ImageView view){

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
        }
        catch(IOException e){

            e.printStackTrace();
        }

        //view.setImageDrawable(android.graphics.);
        view.setVisibility(View.VISIBLE);
    }

    private void setImages(){

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

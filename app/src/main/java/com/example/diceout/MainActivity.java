package com.example.diceout;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.TextView;
import android.widget.Button;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    //Score counter
    private int score;

    //Holds game score (text field)
    private TextView scoreTxt;

    //Roll button
    private Button rollBtn;

    //Random object
    private static Random rnd;

    //Die value
    int die;

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

        rnd = new Random(); //Random number object
        score = 0; //Initial score value
        scoreTxt = (TextView) findViewById(R.id.scoreTxt);
        rollBtn = (Button) findViewById(R.id.rollBtn);

        //Shows welcome toast message
        Toast.makeText(this.getApplicationContext(), "Wellcome to DiceOut game!", Toast.LENGTH_SHORT).show();
    }

    //Generates random number
    private static int rndNum(){
        return rnd.nextInt(6) + 1;
    }

    //Roll dice method
    public void rollDice(View v){

        scoreTxt.setText(String.format("Score: %d", score));
        //scoreTxt.setText(score); //BUG - this code is wrong, app is crushes on clickBtn event
        die = rndNum();
        String message = String.format("You rolled a %d", die);
        //Toast.makeText(this.getApplicationContext(), randomValue, Toast.LENGTH_SHORT).show();
        scoreTxt.setText(message);
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

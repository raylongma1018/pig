package com.example.ray.pig;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    private FrameLayout die1, die2;
    private Button roll, hold;
    private int totalpoint;
    private int totalpoint2;
    private int roundpoint;
    private int round=0;
    private boolean changeplayer = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        roundpoint=0;
        Intent intent = getIntent();
        totalpoint = intent.getIntExtra("score", 0);
        totalpoint2 = intent.getIntExtra("score2",0);
        round = intent.getIntExtra("round",0);
        TextView roundturn = (TextView) findViewById(R.id.round);
        roundturn.setText("round: " + Integer.toString(round));
        TextView player1point = (TextView) findViewById(R.id.p1);
        player1point.setText("P1: " + Integer.toString(totalpoint));
        TextView player2point = (TextView) findViewById(R.id.p2);
        player2point.setText("P2: " + Integer.toString(totalpoint2));
        roll = (Button) findViewById(R.id.button);
        roll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });
        hold = (Button)findViewById(R.id.hold);
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                round +=1;
                totalpoint += roundpoint;

                Intent intent = new Intent(MainActivity.this,Player2.class);
                intent.putExtra("score", totalpoint);
                intent.putExtra("score2",totalpoint2);
                intent.putExtra("round",round);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        });
        die1 = (FrameLayout) findViewById(R.id.die1);
        die2 = (FrameLayout) findViewById(R.id.die2);
        roundpoint
                =0;
    }

    //get two random ints between 1 and 6 inclusive
    public void rollDice() {
        int val1 = 1 + (int) (6 * Math.random());
        int val2 = 1 + (int) (6 * Math.random());
        setDie(val1, die1);
        setDie(val2, die2);
        roundpoint = roundpoint +val1+val2;
        TextView player1point = (TextView) findViewById(R.id.p1);
        player1point.setText("P1: " + Integer.toString(totalpoint+roundpoint));
        TextView player2point = (TextView) findViewById(R.id.p2);
        player2point.setText("P2: " + Integer.toString(totalpoint2));
        if (val1 ==1 || val2 ==1)
        {
            round+=1;


            changeplayer =true;
            roundpoint =0;
            Intent intent = new Intent(MainActivity.this,Player2.class);
            intent.putExtra("score", totalpoint);
            intent.putExtra("score2", totalpoint2);
            intent.putExtra("round",round);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }

        if ((totalpoint +roundpoint) >=100)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("You Won!");
            alertDialog.setMessage("mother fucker");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
    }
    //set the appropriate picture for each die per int
    public void setDie(int value, FrameLayout layout) {
        Drawable pic = null;


        switch (value) {
            case 1:
                pic = getResources().getDrawable(R.drawable.die_face_1);
                break;
            case 2:
                pic = getResources().getDrawable(R.drawable.die_face_2);
                break;
            case 3:
                pic = getResources().getDrawable(R.drawable.die_face_3);
                break;
            case 4:
                pic = getResources().getDrawable(R.drawable.die_face_4);
                break;
            case 5:
                pic = getResources().getDrawable(R.drawable.die_face_5);
                break;
            case 6:
                pic = getResources().getDrawable(R.drawable.die_face_6);
                break;
        }


        layout.setBackground(pic);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

 /*  AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("You Won!");
                alertDialog.setMessage("Yipeeaieahhh!");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();*/
/*   AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();*/
// Handle action bar item clicks here. The action bar will
// automatically handle clicks on the Home/Up button, so long
// as you specify a parent activity in AndroidManifest.xml.
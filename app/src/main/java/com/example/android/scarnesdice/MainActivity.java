package com.example.android.scarnesdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {
    private Random rnd = new Random(System.nanoTime()) ;
    private Button ROLL,HOLD,RESET ;
    private TextView YourScore, ComputerScore,TimeScore ;
    private ImageView Dice ;
    private int num,numc ;
    private int userTurnScore=0,computerTurnScore=0,UserOverall,computerOverall ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ROLL=(Button) findViewById(R.id.button) ;
        HOLD=(Button) findViewById(R.id.button2) ;
        RESET=(Button) findViewById(R.id.button3) ;
        YourScore=(TextView) findViewById(R.id.textView2) ;
        ComputerScore=(TextView) findViewById(R.id.textView3) ;
        TimeScore=(TextView) findViewById(R.id.textView4) ;
        Dice=(ImageView) findViewById(R.id.imageView) ;
    }
    public void ROLL(View view){
        num = rnd.nextInt(6)+1 ;
        if(num==1){
            Dice.setImageResource(R.drawable.dice1);
        }if(num==2){
            Dice.setImageResource(R.drawable.dice2);
        }if(num==3){
            Dice.setImageResource(R.drawable.dice3);
        }if(num==4){
            Dice.setImageResource(R.drawable.dice4);
        }if(num==5){
            Dice.setImageResource(R.drawable.dice5);
        }if(num==6){
            Dice.setImageResource(R.drawable.dice6);
        }if(num!=1){
            userTurnScore+=num ;
            TimeScore.setText("Score:"+""+userTurnScore);
          //  Toast.makeText(MainActivity.this,""+userTurnScore, Toast.LENGTH_SHORT).show();
        }if(num==1){
            userTurnScore=0 ;
            TimeScore.setText("Score"+""+userTurnScore);
            Computer();
         //   Toast.makeText(MainActivity.this, "Computer Turn", Toast.LENGTH_SHORT).show();
        }
    }
    public void HOLD (View view){
        UserOverall+=userTurnScore ;
        YourScore.setText("YourScore:"+""+UserOverall);
        userTurnScore=0 ;
        TimeScore.setText("Score"+""+userTurnScore);
        Computer();
     //   Toast.makeText(MainActivity.this, "Computer Turn", Toast.LENGTH_SHORT).show();
        declaration();
    }
    public void Computer(){
        numc=rnd.nextInt(6)+1 ;
        while(numc!=1&&computerTurnScore<=20){
            computerTurnScore+=numc ;
            TimeScore.setText("Score:"+""+computerTurnScore);
        }
        if(numc==1){
            computerTurnScore=0 ;
            TimeScore.setText("Score:"+""+computerTurnScore);
        }else if(computerTurnScore>20){
            computerOverall+=computerTurnScore ;
            computerTurnScore=0 ;
            TimeScore.setText("Score:"+""+computerTurnScore);
            declaration();
        }
        ComputerScore.setText("ComputerScore:"+""+computerOverall);
    }
    public void declaration(){
        if(computerOverall>100||UserOverall>100){
            if(computerOverall>=100) {
                Toast.makeText(MainActivity.this, "Computer Wins", Toast.LENGTH_SHORT).show();
            }else if(UserOverall>=100){
                Toast.makeText(MainActivity.this, "User Wins", Toast.LENGTH_SHORT).show();
            }
            ROLL.setEnabled(false);
            HOLD.setEnabled(false);
        }
    }
    public void RESET(View view){
        computerOverall=0 ;
        UserOverall=0 ;
        userTurnScore=0 ;
        computerTurnScore=0 ;
        ROLL.setEnabled(true);
        HOLD.setEnabled(true);
        TimeScore.setText("Score:"+""+0);
        YourScore.setText("YourScore:"+""+0);
        ComputerScore.setText("ComputerScore:"+""+0);
    }
    protected void onPause(){
        super.onPause();
    }
}

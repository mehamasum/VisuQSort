package com.meha.myapp;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Locale;

public class theoryActivity extends AppCompatActivity {

    TextToSpeech t1;
    boolean firstrun=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);

        final String [] algomala = {
                "Assume, each element is a card.\n" ,
                "Lets say the leftmost card is the \"Pivot\"\nCheck each card from left to right.\n" ,
                "If a card is less than the pivot:\nIt is a red card!\nSwap that card with the FIRST green card!\n" ,
                "If it is not, it is a green card.\n Continue opening the next card.\n" ,
                "After opening all the cards, \nswap the LAST red card with the pivot.\n" ,
                "This will \"Partition\" the array into 3 parts:\nCards smaller than pivot, Pivot, Greater than or equal to Pivot.\n" ,
                "Then, redo these steps for the first and the third partition!\n"
        };


        final SeekBar progressBar=(SeekBar)findViewById(R.id.algoBar);
        final TextView algoText=(TextView) findViewById(R.id.algoView);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        
        progressBar.setProgress(0);
        String temp=algomala[0] + "\n(" + (0+1) + "/7)";
        algoText.setText(temp);


     //   t1.speak(algomala[0], TextToSpeech.QUEUE_FLUSH, null);

     //   while(t1.isSpeaking());

        progressBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;


            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                t1.speak(algomala[progress], TextToSpeech.QUEUE_FLUSH, null);
                final String temp=algomala[progress] + "\n(" + (progress+1) + "/7)";
                algoText.setText(temp);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        Runnable runnable = new Runnable() {

            @Override
            public void run() {

                while(true){

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if(firstrun) {
                        firstrun=false;
                        t1.speak(algomala[0], TextToSpeech.QUEUE_FLUSH, null);
                    }

                    while(t1.isSpeaking());


                    if(progressBar.getProgress()<6)
                        progressBar.setProgress(progressBar.getProgress()+1);

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }
            }
        };

        new Thread(runnable).start();
    }

    public void back(View v)
    {
        theoryActivity.this.finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}

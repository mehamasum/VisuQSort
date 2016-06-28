package com.meha.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.util.Scanner;

public class Credits extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        final TextView textView = (TextView) findViewById(R.id.creditView);
        textView.setMovementMethod(new ScrollingMovementMethod());



        Runnable runnable = new Runnable() {

            String algo="" +
                    "        Mehedi Hasan Masum\n" +
                    "        Md.Fahim Arefin\n" +
                    "        Tanvir Shahriar Rifat\n" +
                    "        Mohammad Washeef Ibn Waliur\n";

            @Override
            public void run() {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String printer="";
                Scanner sc = new Scanner(algo);
                while(sc.hasNext()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    printer+=sc.nextLine()+"\n";
                    final String temp = printer;
                    textView.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText(temp);
                        }
                    });
                }
            }
        };

        new Thread(runnable).start();

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

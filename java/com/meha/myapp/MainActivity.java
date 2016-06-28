package com.meha.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int ara[];
    int TOL;
    public void theory(View v)
    {
        Intent intent=new Intent(MainActivity.this,theoryActivity.class);
        startActivity(intent);
    }

    public void credits(View v)
    {
        Intent intent=new Intent(MainActivity.this,Credits.class);
        startActivity(intent);
    }


    public void simulation(View v)
    {
        random();
        Intent intent=new Intent(MainActivity.this,Simulation.class);
        intent.putExtra("inSize",10);
        intent.putExtra("inputs", ara);
        startActivity(intent);
    }

    public void userInput(View v)
    {
        Intent intent=new Intent(MainActivity.this,InputActivity.class);
        startActivity(intent);
    }


    public void random()
    {
        int max=99;
        int min=1;
        for(int i=0;i<10;i++)
        {
            int randomNum = min + (int)(Math.random() * ((max - min) + 1));
            ara[i]=randomNum;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_new);

        ara= new int[10];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
        /*
        Show dialogue before exiting
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Set Number Of Inputs");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Set",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        TOL = 5;
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        TOL = 10;
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
         */
    }

    @Override
    public void onBackPressed() {
        finish();
    }

}
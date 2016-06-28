package com.meha.myapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class InputActivity extends AppCompatActivity {

    TextView largeText;
    private ProgressBar progressBar;
    private Button[] button;
    private TextView inputList;
    int count=0,ara[],digit=0,number=0;
    int TOL =10;
    NumberPicker numPicker;
    TextView numText;
    Button numButton,defButton;
    public void openDialog() {
        final Dialog dialog = new Dialog(InputActivity.this); // Context, this, etc.
        dialog.setContentView(R.layout.num_input);
        dialog.setTitle("Set Number of Inputs");
        numPicker=(NumberPicker)dialog.findViewById(R.id.numPicker);
        numPicker.setMinValue(1);
        numPicker.setMaxValue(10);
        numPicker.setWrapSelectorWheel(true);
        numButton=(Button)dialog.findViewById(R.id.numButton);
        defButton=(Button)dialog.findViewById(R.id.defButton);
        numButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                TOL=numPicker.getValue();
                numText.setText("Added : "+count+" of "+String.valueOf(TOL));
                Log.i("fahim", String.valueOf(TOL));
                dialog.dismiss();
            }
        });
        defButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                TOL=10;
                numText.setText("Added : "+count+" of "+String.valueOf(TOL));
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        numText=(TextView) findViewById(R.id.numText);
        openDialog();
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        inputList = (TextView) findViewById(R.id.inputList);
        button= new Button[15];
        button[0] = (Button) findViewById(R.id.zero);
        button[1] = (Button) findViewById(R.id.one);
        button[2] = (Button) findViewById(R.id.two);
        button[3] = (Button) findViewById(R.id.three);
        button[4] = (Button) findViewById(R.id.four);
        button[5] = (Button) findViewById(R.id.five);
        button[6] = (Button) findViewById(R.id.six);
        button[7] = (Button) findViewById(R.id.seven);
        button[8] = (Button) findViewById(R.id.eight);
        button[9] = (Button) findViewById(R.id.nine);
        button[10] = (Button) findViewById(R.id.del);
        button[11] = (Button) findViewById(R.id.add);
        
        
        largeText = (TextView) findViewById(R.id.inputView);

        button[13] = (Button) findViewById(R.id.autoFill);

        ara= new int[TOL];
    }

    public void button_0(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits allowed", Toast.LENGTH_SHORT).show();
        }
        else{
            digit++;
            number= number*10 + 0;

            if(number==0)
                digit--;
        }

        largeText.setText(String.valueOf(number));
    }

    public void button_1(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits allowed", Toast.LENGTH_SHORT).show();
        }
        else{
            digit++;
            number= number*10 + 1;
        }

        largeText.setText(String.valueOf(number));
    }

    public void button_2(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits allowed", Toast.LENGTH_SHORT).show();
        }
        else{
            digit++;
            number= number*10 + 2;
        }

        largeText.setText(String.valueOf(number));
    }

    public void button_3(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits allowed", Toast.LENGTH_SHORT).show();
        }
        else{
            digit++;
            number= number*10 + 3;
        }

        largeText.setText(String.valueOf(number));
    }

    public void button_4(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits allowed", Toast.LENGTH_SHORT).show();
        }
        else{
            digit++;
            number= number*10 + 4;
        }

        largeText.setText(String.valueOf(number));
    }

    public void button_5(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits allowed", Toast.LENGTH_SHORT).show();
        }
        else{
            digit++;
            number= number*10 + 5;
        }

        largeText.setText(String.valueOf(number));
    }

    public void button_6(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits allowed", Toast.LENGTH_SHORT).show();
        }
        else{
            digit++;
            number= number*10 + 6;
        }

        largeText.setText(String.valueOf(number));
    }

    public void button_7(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits allowed", Toast.LENGTH_SHORT).show();
        }
        else{
            digit++;
            number= number*10 + 7;
        }

        largeText.setText(String.valueOf(number));
    }

    public void button_8(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits allowed", Toast.LENGTH_SHORT).show();
        }
        else{
            digit++;
            number= number*10 + 8;
        }

        largeText.setText(String.valueOf(number));
    }

    public void button_9(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits allowed", Toast.LENGTH_SHORT).show();
        }
        else{
            digit++;
            number= number*10 + 9;
        }

        largeText.setText(String.valueOf(number));
    }

    public void button_add(View v)
    {
        numText.setText("Added : "+count+" of "+String.valueOf(TOL));
        if(count==TOL){
            Intent i = new Intent(InputActivity.this, Simulation.class);
            i.putExtra("inputs", ara);
            i.putExtra("inSize",TOL);
            startActivity(i);
        }
        else {
            ara[count] = number;
            count++;
            progressBar.setProgress(count*100/TOL);

            String str= inputList.getText().toString();
            str= str+ "  ";
            str= str+ String.valueOf(number);
            inputList.setText(str);
            numText.setText("Added : "+count+" of "+String.valueOf(TOL));

            str= String.valueOf(number);
            str= str+ " added";
            //Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();

            number=0;
            digit=0;
            largeText.setText("00");

        }

        if(count==TOL){
            button[11].setText("DONE");
            for (int j = 0; j <= 10; j++)
                button[j].setClickable(false);
            button[13].setClickable(false);
        }
    }

    public void button_del(View v){

        digit--;
        number= number/10;


        largeText.setText(String.valueOf(number));
    }


    public void autoFill(View v){

        if(count==TOL){
            Intent i = new Intent(InputActivity.this, Simulation.class);
            i.putExtra("inputs", ara);
            i.putExtra("inSize",TOL);
            startActivity(i);
        }
        else {
            int max = 99;
            int min = 1;
            for (int i = count; i < TOL; i++) {
                int randomNum = min + (int) (Math.random() * ((max - min) + 1));
                ara[i] = randomNum;
                count++;
                progressBar.setProgress(count*100/TOL);

                String str = inputList.getText().toString();
                str = str + "  ";
                str = str + String.valueOf(randomNum);
                inputList.setText(str);
                numText.setText("Added : "+count+" of "+String.valueOf(TOL));

                for (int j = 0; j <= 11; j++)
                    button[j].setClickable(false);

                button[13].setText("Done");
            }
        }
    }


    private void doFakeWork() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_input, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();


        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}

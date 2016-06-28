package com.meha.myapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class dataChanger extends Activity {

    Thread th;
    int[] a = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
    int [] dup_a;
    Button[] btn = new Button[10];
    int TOL = 10;
    int val,number;
    int repIndex;//Button index, which has to be replaced
    int digit;
    int PURPLE = Color.argb(50, 163, 73, 164);
    TextView oldText;
    Button repB;
    Button[] button;
    TextView newText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_changer);
        oldText=(TextView)findViewById(R.id.oldText);
        Bundle extras = getIntent().getExtras();
        a= extras.getIntArray("inputs");
        TOL=extras.getInt("inSize");
        digit=0;
        digit=0;
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.cLay);
        RelativeLayout row = new RelativeLayout(this);
        RelativeLayout.LayoutParams rowParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rowParam.addRule(RelativeLayout.CENTER_HORIZONTAL);
        row.setLayoutParams(rowParam);

        LinearLayout holder = new LinearLayout(this);

        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());
        Log.i("meha", "" + px);

        for (int j = 0; j < TOL; j++) {
            btn[j] = new Button(this);
            btn[j].setText(Integer.toString(a[j]));
            btn[j].setId(j);
            btn[j].setBackgroundColor(Color.argb(50, 0, 0, 0));
            LinearLayout.LayoutParams lparam = new LinearLayout.LayoutParams(
                    px / 4, px / 4
            );

            btn[j].setLayoutParams(lparam);
            holder.addView(btn[j]);
        }
        repB=btn[0];
        for(int j=0;j<TOL;j++)
        {
            btn[j].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    bClick(view);
                }
            });
        }
        row.addView(holder);
        layout.addView(row);
        newText = (TextView) findViewById(R.id.newText);
        button= new Button[15];

        button[0] = (Button) findViewById(R.id.zeroS);
        button[1] = (Button) findViewById(R.id.oneS);
        button[2] = (Button) findViewById(R.id.twoS);
        button[3] = (Button) findViewById(R.id.threeS);
        button[4] = (Button) findViewById(R.id.fourS);
        button[5] = (Button) findViewById(R.id.fiveS);
        button[6] = (Button) findViewById(R.id.sixS);
        button[7] = (Button) findViewById(R.id.sevenS);
        button[8] = (Button) findViewById(R.id.eightS);
        button[9] = (Button) findViewById(R.id.nineS);
        button[10] = (Button) findViewById(R.id.delS);
        button[11] = (Button) findViewById(R.id.addS);
        newText.setText("New Value : ");
      //  button[13] = (Button) findViewById(R.id.autoFillS);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(dataChanger.this, Simulation.class);
        i.putExtra("inputs", a);
        i.putExtra("inSize",TOL);
        startActivity(i);
        finish();
    }

    public void button_0(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits are allowed :(", Toast.LENGTH_LONG).show();
        }
        else{
            digit++;
            number= number*10 + 0;

            if(number==0)
                digit--;
        }

        newText.setText("New Value : " + String.valueOf(number));
    }

    public void button_1(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits are allowed :(", Toast.LENGTH_LONG).show();
        }
        else{
            digit++;
            number= number*10 + 1;
        }

        newText.setText("New Value : " + String.valueOf(number));
    }

    public void button_2(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits are allowed :(", Toast.LENGTH_LONG).show();
        }
        else{
            digit++;
            number= number*10 + 2;
        }

        newText.setText("New Value : " + String.valueOf(number));
    }

    public void button_3(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits are allowed :(", Toast.LENGTH_LONG).show();
        }
        else{
            digit++;
            number= number*10 + 3;
        }

        newText.setText("New Value : " + String.valueOf(number));
    }

    public void button_4(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits are allowed :(", Toast.LENGTH_LONG).show();
        }
        else{
            digit++;
            number= number*10 + 4;
        }

        newText.setText("New Value : " + String.valueOf(number));
    }

    public void button_5(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits are allowed :(", Toast.LENGTH_LONG).show();
        }
        else{
            digit++;
            number= number*10 + 5;
        }

        newText.setText("New Value : " + String.valueOf(number));
    }

    public void button_6(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits are allowed :(", Toast.LENGTH_LONG).show();
        }
        else{
            digit++;
            number= number*10 + 6;
        }

        newText.setText("New Value : " + String.valueOf(number));
    }

    public void button_7(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits are allowed :(", Toast.LENGTH_LONG).show();
        }
        else{
            digit++;
            number= number*10 + 7;
        }
        newText.setText("New Value : " + String.valueOf(number));
    }

    public void button_8(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits are allowed :(", Toast.LENGTH_LONG).show();
        }
        else{
            digit++;
            number= number*10 + 8;
        }

        newText.setText("New Value : " + String.valueOf(number));
    }

    public void button_9(View v){

        if(digit==2){
            Toast.makeText(getApplicationContext(), "Maximum two digits are allowed :(", Toast.LENGTH_LONG).show();
        }
        else{
            digit++;
            number= number*10 + 9;
        }

        newText.setText("New Value : " + String.valueOf(number));
    }

    public void button_add(View v)
    {
        oldText.setText("Old Value : "+Integer.toString(a[repIndex]));
        a[repIndex]=number;
        btn[repIndex].setText(Integer.toString(number));
        newText.setText("New Value : " + String.valueOf(number));
        btn[repIndex].setBackgroundColor(PURPLE);
        for(int i=0;i<TOL;i++) {
            ColorDrawable drawable = (ColorDrawable) btn[i].getBackground();
            int color = drawable.getColor();
            if (color != PURPLE) {
                btn[i].setBackgroundColor(Color.argb(50, 0, 0, 0));
            }
        }
        oldText.setText("Done! Touch any array element to select.");

        Log.i("Fahim ",number + " index "+repIndex);
        number =0;
        digit=0;
    }

    public void finishButton(View v)
    {
        Log.i("fahim",String.valueOf(TOL) );
        Intent i = new Intent(dataChanger.this, Simulation.class);
        i.putExtra("inputs", a);
        i.putExtra("inSize",TOL);
        startActivity(i);
        finish();
    }

    public void button_del(View v){

        digit--;
        number= number/10;
        newText.setText("New Value : " + String.valueOf(number));
    }


    public void autoFill(View v)
    {
            int max = 99;
            int min = 1;
            number = min + (int) (Math.random() * ((max - min) + 1));
            newText.setText("New Value : " + String.valueOf(number));
    }


    public void bClick(View view)
    {

        ColorDrawable drawable = (ColorDrawable) repB.getBackground();
        int color = drawable.getColor();
        if (color != PURPLE)
            repB.setBackgroundColor(Color.argb(50, 0, 0, 0));
        repB = (Button)view;
        for(int j=0;j<TOL;j++)
        {
            if(btn[j]==repB)
                repIndex=j;
        }
        String buttonText = repB.getText().toString();
        repB.setBackgroundColor(Color.argb(255,227,233,84));
        val=Integer.parseInt(buttonText);
        oldText.setText("Old Value : "+buttonText);
        //rollView.setText("Roll : " +String.valueOf(roll));
        newText.setText("New Value : "+number);
        //activityCommander.updateList(buttonText);
    }


}

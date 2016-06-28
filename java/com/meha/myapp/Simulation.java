package com.meha.myapp;


import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;


public class Simulation extends Activity implements AnimationListener {
    Thread th;
    int[] a = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3};
    int [] dup_a;
    Button[] btn = new Button[10];
    int TOL = 10, pivot;
    int LEFT, RIGHT;
    Handler handler;
    Button btn1;

    int SLEEP_TIME = 2000;
    int GREEN = Color.argb(100, 34, 177, 76);
    int BLUE = Color.argb(100, 50, 150, 255);
    int PURPLE = Color.argb(100, 163, 73, 164);
    int RED = Color.argb(100, 255, 80, 80);
    SeekBar speedBar;
    TextView speedView;
    TextView textView;
    Button bPause;
    int pauseState=0;

    // Animation
    Animation blink;

    @Override
    public void onAnimationEnd(Animation animation) {
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }

    public void pause(View v)
    {
        if(pauseState==0) {
            bPause.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_play, 0, 0, 0);
            bPause.setText("Resume");
            pauseState=1;
        }
        else
        {
            bPause.setCompoundDrawablesWithIntrinsicBounds( R.drawable.ic_pause, 0, 0, 0);
            bPause.setText("Pause");
            pauseState=0;
        }
    }

    public void restart(View v)
    {
        Intent intent = getIntent();
        //intent.putExtra("inputs", dup_a);
        finish();
        startActivity(intent);
        Log.i("meha", "restart chapsi");
    }

    public void change_data(View v)
    {
        Intent intent=new Intent(Simulation.this,dataChanger.class);
        intent.putExtra("inputs", a);
        intent.putExtra("inSize",TOL);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation);

        Bundle extras = getIntent().getExtras();
        a= extras.getIntArray("inputs");
        TOL=extras.getInt("inSize");

        textView = (TextView) findViewById(R.id.describe);
        bPause=(Button)findViewById(R.id.bPause);
        speedBar=(SeekBar)findViewById(R.id.speedBar);
        speedView=(TextView) findViewById(R.id.speedView);


        // load the animation
        blink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);

        // set animation listener
        blink.setAnimationListener(this);


        RelativeLayout layout = (RelativeLayout) findViewById(R.id.ll);
        RelativeLayout row = new RelativeLayout(this);
        RelativeLayout.LayoutParams rowParam = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        rowParam.addRule(RelativeLayout.CENTER_HORIZONTAL);


        LinearLayout holder = new LinearLayout(this);
        speedBar.setProgress(4);
        speedView.setText("Animation Speed : " + speedBar.getProgress()*10+" %");
        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                progress = progresValue;
                speedView.setText("Animation Speed : " + progress*10+" %" );
                //Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                speedView.setText("Animation Speed : " + progress*10+" %" );
                //Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int val=(400*progress);
                SLEEP_TIME=5000-val;
                speedView.setText("Animation Speed : " + progress*10+" %");
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();
            }
        });


        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, r.getDisplayMetrics());
        Log.i("meha", "" + px);

        rowParam.setMargins(0, px/2, 0, 0);  // left, top, right, bottom
        row.setLayoutParams(rowParam);

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
        row.addView(holder);
        layout.addView(row);

        Runnable runner = new Runnable() {
            @Override
            public void run() {
                int left = 0;
                int right = TOL-1;

                dup_a=a.clone();
                quick(left, right);


                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("Done!");
                    }
                });

                for (int k = 0; k < TOL; k++)
                    Log.i("meha", "" + a[k]);

            }
        };

        th = new Thread(runner);
        th.start();


    }


    void quick(int low, int high) {
        if (low < high) {

            trap();
            int part = partition(low, high);
            doFakeWork();

            trap();
            for (int j = low; j <= part - 1; j++) {
                setBlack(j);
            }
            trap();
            textView.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText("Left part now!");
                }
            });
            trap();
            quick(low, part - 1);


            doFakeWork();

            trap();
            for (int j = part + 1; j <= high; j++) {
                setBlack(j);
            }
            trap();
            textView.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText("Right part now!");
                }
            });
            trap();
            quick(part + 1, high);
        }
        else if (low == high) {
            trap();
            setYello(low);
        }
    }


    int partition(int low, int high) {

        trap();
        pivot = a[low];
        setBlue(low);

        final int fpivot=pivot;
        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("This is the Pivot"+" ("+fpivot+")");
            }
        });

        trap();
        int lastopen = low;
        for (int j = low + 1; j <= high; j++) {
            trap();
            if (a[j] < pivot) {
                trap();
                setRed(j);

                trap();
                lastopen++;

                trap();
                swapUIIn(j, lastopen, a[j], a[lastopen]);
                swap(j, lastopen);

            }
            else {
                trap();
                setGreen(j);
                final int bigger=a[j];
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(""+bigger+" >= pivot"+" ("+fpivot+")"+". Cool.");
                    }
                });

            }
        }

        doFakeWork();

        swapUIOut(lastopen, low, a[lastopen], a[low]);
        swap(lastopen, low); //swap the pivot

        trap();
        setYello(lastopen);

        trap();
        return lastopen;
    }


    void swap(int x, int y) {
        Log.i("meha", "mathy swapping:" + a[x] + " " + a[y]);

        int temp = a[x];
        Log.i("meha", "MA1>  " + a[x]);
        a[x] = a[y];
        Log.i("meha", "MA1>>  " + a[x]);

        Log.i("meha", "MA2>  " + a[y]);
        a[y] = temp;
        Log.i("meha", "MA2>>  " + a[y]);

    }

    void swapUIIn(int ff, int ss, int data1, int data2) {

        final int first = ff, second = ss, firstData = data1, secData = data2;
        final String temp = btn[first].getText().toString();

        ColorDrawable buttonColor1 = (ColorDrawable) btn[first].getBackground();
        final int first_color = buttonColor1.getColor();
        ColorDrawable buttonColor2 = (ColorDrawable) btn[second].getBackground();
        final int second_color = buttonColor2.getColor();

        final int aj=data1;
        final int lastopen=data2, fpivot=pivot;
        Log.i("meha", "swapping:" + firstData + " " + secData);
        textView.post(new Runnable() {
            @Override
            public void run() {
                if (first != second)
                    textView.setText("" + aj + " < Pivot (" + fpivot + ") ! Swap it with the FIRST green card (" + lastopen + ")");
                else
                    textView.setText("" + aj + " < Pivot (" + fpivot + ") ! But no green card here!");
            }
        });

        doFakeWork();



        if (first != second) {
            try {
                btn[first].post(new Runnable() {
                    @Override
                    public void run() {
                        btn[first].startAnimation(blink);
                    }
                });
                btn[second].post(new Runnable() {
                    @Override
                    public void run() {
                        btn[second].startAnimation(blink);
                    }
                });
            } catch (Exception e){
                Log.i("meha",e.toString());
            }
            doFakeWork();

            btn[first].post(new Runnable() {
                @Override
                public void run() {
                    Log.i("meha", "GUI1>  " + btn[first].getText().toString());
                    btn[first].setText("" + secData);
                    Log.i("meha", "GUI1>>  " + btn[first].getText().toString());
                    btn[first].setBackgroundColor(GREEN);
                }
            });

            btn[second].post(new Runnable() {
                @Override
                public void run() {
                    Log.i("meha", "GUI2>  " + btn[second].getText().toString());
                    btn[second].setText("" + firstData);
                    Log.i("meha", "GUI2>>  " + btn[second].getText().toString());
                    btn[second].setBackgroundColor(RED);
                }
            });

            textView.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText("");
                }
            });


        }


    }

    void swapUIOut(int ff, int ss, int data1, int data2) {

        final int first = ff, second = ss, firstData = data1, secData = data2;
        final int lastopen=data1, fpivot=data2;

        textView.post(new Runnable() {
            @Override
            public void run() {
                if(first!=second)
                    textView.setText("Swapping pivot ("+fpivot+") with the LAST red card ("+lastopen+")");
                else
                    textView.setText("Swapping pivot ("+fpivot+"). But no red card to swap.");
            }
        });

        doFakeWork();


        if(first!=second) {
            try {
                btn[first].post(new Runnable() {
                    @Override
                    public void run() {
                        btn[first].startAnimation(blink);
                    }
                });
                btn[second].post(new Runnable() {
                    @Override
                    public void run() {
                        btn[second].startAnimation(blink);
                    }
                });
            } catch (Exception e){
                Log.i("meha",e.toString());
            }
            doFakeWork();


            btn[first].post(new Runnable() {
                @Override
                public void run() {
                    Log.i("meha", "GUI1>  " + btn[first].getText().toString());
                    btn[first].setText("" + secData);
                    Log.i("meha", "GUI1>>  " + btn[first].getText().toString());
                    btn[first].setBackgroundColor(BLUE);
                }
            });

            btn[second].post(new Runnable() {
                @Override
                public void run() {
                    Log.i("meha", "GUI2>  " + btn[second].getText().toString());
                    btn[second].setText("" + firstData);
                    Log.i("meha", "GUI2>>  " + btn[second].getText().toString());
                    if (first != second) btn[second].setBackgroundColor(RED);
                }
            });

            textView.post(new Runnable() {
                @Override
                public void run() {
                    textView.setText("");
                }
            });
        }


    }

    private void doFakeWork() {

        for(int i=0; i<SLEEP_TIME/100; i++){

            trap();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    void trap(){
        while(pauseState==1);
    }

    void setGreen(int idx) {
        final int i = idx;
        doFakeWork();
        btn[i].post(new Runnable() {
            @Override
            public void run() {
                btn[i].setBackgroundColor(GREEN);
            }
        });

    }

    void setRed(int idx) {
        final int i = idx;
        doFakeWork();
        btn[i].post(new Runnable() {
            @Override
            public void run() {
                btn[i].setBackgroundColor(RED);
            }
        });
    }

    void setBlue(int idx) {
        final int i = idx;
        doFakeWork();
        btn[i].post(new Runnable() {
            @Override
            public void run() {
                btn[i].setBackgroundColor(BLUE);
                // btn[i].setBackgroundResource(R.drawable.buttonborder);
            }
        });
    }

    void setYello(int idx) {
        final int i = idx;
        doFakeWork();

        textView.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("Done with this!");
            }
        });

        btn[i].post(new Runnable() {
            @Override
            public void run() {
                btn[i].setBackgroundColor(Color.argb(50, 255, 200, 15));
            }
        });

    }

    void setBlack(int idx) {
        final int i = idx;
        btn[i].post(new Runnable() {
            @Override
            public void run() {
                btn[i].setBackgroundColor(Color.argb(50, 0, 0, 0));
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sim, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.change)
        {
            Intent intent=new Intent(Simulation.this,dataChanger.class);
            intent.putExtra("inputs", a);
            intent.putExtra("inSize",TOL);
            startActivity(intent);
            finish();
        }
        else if (item.getItemId()==R.id.back) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
            return(true);
        }
        return (super.onOptionsItemSelected(item));
    }

    void setPurp(int idx) {
        final int i = idx;
        btn[i].post(new Runnable() {
            @Override
            public void run() {
                btn[i].setBackgroundColor(PURPLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
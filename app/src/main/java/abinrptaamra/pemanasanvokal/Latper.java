package abinrptaamra.pemanasanvokal;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.TextView;
import android.widget.Chronometer;
import android.os.SystemClock;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnClickListener;



public class Latper extends ActionBarActivity {
    Toolbar toolbar;
    Handler handler;
    TextView animasi;


    Animation in;
    Animation out;
    Animation inHold;
    Animation outHold;

    int fadeCount;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latper);
        final Chronometer stopWatch = (Chronometer) findViewById(R.id.chronometer);
        final ImageButton playbutton = (ImageButton) findViewById(R.id.playbutton);
        final ImageButton stopbutton = (ImageButton) findViewById(R.id.stopbutton);

        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(), MainActivityMenu.class));
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        animasi = (TextView) findViewById(R.id.animation);
        animasi.setText("");

        fadeCount = 0;

        handler = new Handler();

        in = new AlphaAnimation(0.0f, 1.0f);
        in.setDuration(1000);

        inHold = new AlphaAnimation(0.0f, 1.0f);
        inHold.setDuration(2000);
        inHold.setStartOffset(8000);

        out = new AlphaAnimation(1.0f, 0.0f);
        out.setDuration(1000);
        out.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationEnd(Animation animation) {
                fadeCount++;
                if (fadeCount == 7) {
                    animasi.setText("Tahan nafas");
                    stopWatch.setBase(SystemClock.elapsedRealtime());
                    stopWatch.start();
                    } else {
                        if (fadeCount == 1) {
                            animasi.setText("Keluarkan lewat mulut");
                        } else {
                            if (fadeCount == 2) {
                                animasi.setText("Tarik nafas dari hidung");
                            } else {
                                if (fadeCount == 3) {
                                    animasi.setText("Keluarkan lewat mulut");
                                } else {
                                    if (fadeCount == 4) {
                                        animasi.setText("Tarik nafas dari hidung");
                                    } else {
                                        if (fadeCount == 5) {
                                        animasi.setText("Keluarkan lewat mulut");
                                        } else {
                                            animasi.setText("Tarik nafas dari Hidung");
                                        }
                                    }
                                }
                            }
                        }
                        animasi.startAnimation(in);
                        handler.postDelayed(mFadeOut, 1000);
                    }
                }

            @Override
            public void onAnimationRepeat(Animation arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onAnimationStart(Animation arg0) {
                // TODO Auto-generated method stub

            }
        });









        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGrupdetik);
        rg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb8:
                        stopWatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                            @Override
                            public void onChronometerTick(Chronometer chronometer) {
                                if (chronometer.getText().toString().equalsIgnoreCase("00:09")) {
                                    stopWatch.stop();
                                    stopWatch.setText("00:00");
                                    Intent i = new Intent(getApplication() ,  Latper.class);
                                    startActivity(i);
                                }else {
                                    if (chronometer.getText().toString().equalsIgnoreCase("00:08")) {
                                        animasi.setText("Selesai.. Hembuskan dari mulut");
                                    }
                                }

                            }
                        });
                        break;
                    case R.id.rb12:
                        stopWatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                            @Override
                            public void onChronometerTick(Chronometer chronometer) {
                                if (chronometer.getText().toString().equalsIgnoreCase("00:13")) {
                                    stopWatch.stop();
                                    stopWatch.setText("00:00");
                                    Intent i = new Intent(getApplication() ,  Latper.class);
                                    startActivity(i);
                                }else {
                                    if (chronometer.getText().toString().equalsIgnoreCase("00:12")) {
                                        animasi.setText("Selesai.. Hembuskan dari mulut");
                                    }
                                }
                            }
                        });
                        break;
                    case R.id.rb16:
                        stopWatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                            @Override
                            public void onChronometerTick(Chronometer chronometer) {
                                if (chronometer.getText().toString().equalsIgnoreCase("00:17")) {
                                    stopWatch.stop();
                                    stopWatch.setText("00:00");
                                    Intent i = new Intent(getApplication() ,  Latper.class);
                                    startActivity(i);
                                }else {
                                    if (chronometer.getText().toString().equalsIgnoreCase("00:16")) {
                                        animasi.setText("Selesai.. Hembuskan dari mulut");
                                    }
                                }

                            }
                        });
                        break;
                    case R.id.rb20:
                        stopWatch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                            @Override
                            public void onChronometerTick(Chronometer chronometer) {
                                if (chronometer.getText().toString().equalsIgnoreCase("00:21")) {
                                    stopWatch.stop();
                                    stopWatch.setText("00:00");
                                    Intent i = new Intent(getApplication() ,  Latper.class);
                                    startActivity(i);
                                }else {
                                    if (chronometer.getText().toString().equalsIgnoreCase("00:20")) {
                                        animasi.setText("Selesai.. Hembuskan dari mulut");
                                    }
                                }
                            }
                        });
                        break;
            }
        }
    });
        playbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (rg.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(getApplicationContext(), "Pilih Detik Latihan", Toast.LENGTH_SHORT).show();
                } else {
                    animasi.setText("Tarik nafas dari hidung");
                    animasi.startAnimation(in);
                    handler.postDelayed(mFadeOut, 1000);


                }


            }
        });

        stopbutton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                animasi.setText("");
                stopWatch.setBase(SystemClock.elapsedRealtime());
                stopWatch.stop();
                fadeCount= 0;

            }
        });

    }
    public void onBackPressed() {
        finish();
    }
    private Runnable mFadeOut =new Runnable(){

        @Override
        public void run() {
            //Speed up the last fade-out so that the Activity opens faster
            if (fadeCount == 2){
                out.setDuration(1000);
            }
            animasi.startAnimation(out);
        }
    };
}
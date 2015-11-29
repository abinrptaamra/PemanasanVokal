package abinrptaamra.pemanasanvokal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.SeekBar;
import android.media.MediaPlayer;
import android.os.Handler;
import android.widget.Toast;

/**
 * Created by User on 16/11/2015.
 */
public class Demo extends ActionBarActivity {

    int[] songhum = {R.raw.humc2, R.raw.humd2,  R.raw.hume2,  R.raw.humf2,  R.raw.humg2, R.raw.huma2, R.raw.humb2,  R.raw.humc3};
    int[] songgoogo = {R.raw.googoc2, R.raw.googod2, R.raw.googoe2, R.raw.googof2, R.raw.googog2, R.raw.googoa2, R.raw.googob2, R.raw.googoc3};
    int[] songgnung = {R.raw.gnungc2, R.raw.gnungd2, R.raw.gnunge2, R.raw.gnungf2, R.raw.gnungg2, R.raw.gnunga2, R.raw.gnungb2, R.raw.gnungc3};
    int[] songaaah = {R.raw.aaahc2, R.raw.aaahd2, R.raw.aaahe2, R.raw.aaahf2, R.raw.aaahg2, R.raw.aaaha2, R.raw.aaahb2, R.raw.aaahc3};
    int[] songooze = {R.raw.oozec2, R.raw.oozed2, R.raw.oozee2, R.raw.oozef2, R.raw.oozeg2, R.raw.oozea2, R.raw.oozeb2, R.raw.oozec3};

    int songdemohum = R.raw.demo_aaah;
    int songdemogoogo = R.raw.demo_googo;
    int songdemognung = R.raw.demo_gnung;
    int songdemogaaah = R.raw.demo_aaah;
    int songdemoooze = R.raw.demo_ooze;

    Toolbar toolbar;
    MediaPlayer mediaPlayer;
    ImageButton buttonPlayPause;
    ImageButton nextbtnd;
    ImageButton prevbtnd;
    Button latihanbtn;
    TextView timePos;
    TextView textdemo;
    SeekBar seekBar;
    Handler handler;
    private int stateMediaPlayer;
    private final int stateMP_NotStarter = 0;
    private final int stateMP_Playing = 1;
    private final int stateMP_Pausing = 2;

    private int mediaPos;
    private int mediaMax;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);

        initMediaPlayer();
        Bundle b = getIntent().getExtras();
        int Song = b.getInt("Integer");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), Song);


        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = (new Intent(getApplicationContext(), PilihPemanasan.class));
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });



        textdemo = (TextView) findViewById(R.id.textdemo);
        String flag = String.valueOf(getIntent().getStringExtra("Demo"));
        if(flag.equalsIgnoreCase("demohum")) {
            textdemo.setText("Pemanasan Hum");
        }
        if(flag.equalsIgnoreCase("demogoogo")) {
            textdemo.setText("Pemanasan Googo");
        }
        if(flag.equalsIgnoreCase("demognung")) {
            textdemo.setText("Pemanasan Gnung");
        }
        if(flag.equalsIgnoreCase("demoaaah")) {
            textdemo.setText("Pemanasan Aaah");
        }
        if(flag.equalsIgnoreCase("demoooze")) {
            textdemo.setText("Pemanasan Ooze");
        }



        seekBar = (SeekBar) findViewById(R.id.seekBar2);
        timePos = (TextView) findViewById(R.id.TimeSeek2);
        buttonPlayPause = (ImageButton) findViewById(R.id.playbutton);
        buttonPlayPause.setOnClickListener(buttonPlayPauseOnClickListener);
        nextbtnd = (ImageButton) findViewById(R.id.nextbtnD);
        nextbtnd.setOnClickListener(nextbtndOnClickListener);
        latihanbtn = (Button) findViewById(R.id.lthnbtn);
        latihanbtn.setOnClickListener(latihanbtnOnClickListener);
        prevbtnd = (ImageButton) findViewById(R.id.prevbtnD);
        prevbtnd.setOnClickListener(prevbtndOnClickListener);
        seekBar.setOnSeekBarChangeListener(seekBarOnSeekChangeListener);
        mediaPos = mediaPlayer.getCurrentPosition();
        mediaMax = mediaPlayer.getDuration();
        seekBar.setMax(mediaMax); // Set the Maximum range of the
        // seekBar.setProgress(mediaPos);// set
        // current progress to song's
        seekBar.setProgress(mediaPos);// set current progress to song's
        handler.removeCallbacks(moveSeekBarThread);
        handler.postDelayed(moveSeekBarThread, 100);



    }

    private Runnable moveSeekBarThread = new Runnable() {


        public void run() {
            if (mediaPlayer.isPlaying()) {

                int mediaPos_new = mediaPlayer.getCurrentPosition();
                int mediaMax_new = mediaPlayer.getDuration();

                seekBar.setMax(mediaMax_new);
                seekBar.setProgress(mediaPos_new);
                String seconds = String.valueOf((mediaPos_new % 60000) / 1000);
                String minutes = String.valueOf(mediaPos_new / 60000);
                if (seconds.length() == 1) {
                    timePos.setText("0" + minutes + ":0" + seconds);
                }else {
                    timePos.setText("0" + minutes + ":" + seconds);
                }
                handler.postDelayed(this, 100); // Looping the thread after 0.1
                // seconds
            } else {
                int mediaPos_new = mediaPlayer.getCurrentPosition();
                int mediaMax_new = mediaPlayer.getDuration();

                seekBar.setMax(mediaMax_new);
                seekBar.setProgress(mediaPos_new);
                String seconds = String.valueOf((mediaPos_new % 60000) / 1000);
                String minutes = String.valueOf(mediaPos_new / 60000);
                if (seconds.length() == 1) {
                    timePos.setText("0" + minutes + ":0" + seconds);
                }else {
                    timePos.setText("0" + minutes + ":" + seconds);
                }
                handler.postDelayed(this, 100); // Looping the thread after 0.1
                // seconds
            }

        }


    };


    protected void onPause() {
        seekBar.setProgress(0);
        super.onPause();
    }

    private void initMediaPlayer() {
        handler = new Handler();
        mediaPlayer = new MediaPlayer();
        stateMediaPlayer = stateMP_NotStarter;
    }
    ImageButton.OnClickListener buttonPlayPauseOnClickListener = new ImageButton.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            switch (stateMediaPlayer) {
                case stateMP_NotStarter:
                    mediaPlayer.start();
                    buttonPlayPause
                            .setImageResource(R.drawable.pausebutton);
                    stateMediaPlayer = stateMP_Playing;
                    break;
                case stateMP_Playing:
                    mediaPlayer.pause();
                    buttonPlayPause
                            .setImageResource(R.drawable.playbutton);
                    stateMediaPlayer = stateMP_Pausing;
                    break;
                case stateMP_Pausing:
                    mediaPlayer.start();
                    buttonPlayPause
                            .setImageResource(R.drawable.pausebutton);
                    stateMediaPlayer = stateMP_Playing;
                    break;
            }
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

                // @Override
                public void onCompletion(MediaPlayer arg0) {
                    // File has ended !!!

                    buttonPlayPause.setImageResource(R.drawable.playbutton);
                    stateMediaPlayer = stateMP_NotStarter;
                }
            });
        }

    };
    ImageButton.OnClickListener nextbtndOnClickListener = new ImageButton.OnClickListener() {

        @Override
        public void onClick(View v) {
            mediaPlayer.stop();
            buttonPlayPause.setImageResource(R.drawable.playbutton);
            stateMediaPlayer = stateMP_NotStarter;
            String flag = String.valueOf(getIntent().getStringExtra("Demo"));
            if(flag.equalsIgnoreCase("demohum")){
                Intent demogoogo = new Intent(Demo.this, Demo.class);
                demogoogo.putExtra("Integer", songdemogoogo);
                demogoogo.putExtra("Demo", "demogoogo");
                startActivity(demogoogo);
            }
            if(flag.equalsIgnoreCase("demogoogo")) {
                Intent demognung = new Intent(Demo.this, Demo.class);
                demognung.putExtra("Integer", songdemognung);
                demognung.putExtra("Demo","demognung");
                startActivity(demognung);
            }
            if(flag.equalsIgnoreCase("demognung")) {
                Intent demoaaah = new Intent(Demo.this, Demo.class);
                demoaaah.putExtra("Integer", songdemogaaah);
                demoaaah.putExtra("Demo","demoaaah");
                startActivity(demoaaah);
            }
            if(flag.equalsIgnoreCase("demoaaah")) {
                Intent demoooze = new Intent(Demo.this, Demo.class);
                demoooze.putExtra("Integer", songdemoooze);
                demoooze.putExtra("Demo","demoooze");
                startActivity(demoooze);
            }
            if(flag.equalsIgnoreCase("demoooze")) {
                Toast.makeText(getApplicationContext(), "ini adalah Demo latihan terakhir", Toast.LENGTH_SHORT).show();
                Intent demoooze = new Intent(Demo.this, Demo.class);
                demoooze.putExtra("Integer", songdemoooze);
                demoooze.putExtra("Demo","demoooze");
                startActivity(demoooze);
            }
        }
    };

    ImageButton.OnClickListener prevbtndOnClickListener = new ImageButton.OnClickListener() {

        @Override
        public void onClick(View v) {
            mediaPlayer.stop();
            buttonPlayPause.setImageResource(R.drawable.playbutton);
            stateMediaPlayer = stateMP_NotStarter;
            String flag = String.valueOf(getIntent().getStringExtra("Demo"));
            if(flag.equalsIgnoreCase("demohum")) {
                Toast.makeText(getApplicationContext(), "ini adalah Demo latihan Pertama", Toast.LENGTH_SHORT).show();
                Intent demohum = new Intent(Demo.this, Demo.class);
                demohum.putExtra("Integer", songdemohum);
                demohum.putExtra("Demo", "demohum");
                startActivity(demohum);
            }
            if(flag.equalsIgnoreCase("demogoogo")){
                Intent demohum = new Intent(Demo.this, Demo.class);
                demohum.putExtra("Integer", songdemohum);
                demohum.putExtra("Demo", "demohum");
                startActivity(demohum);
            }
            if(flag.equalsIgnoreCase("demognung")){
                Intent demogoogo = new Intent(Demo.this, Demo.class);
                demogoogo.putExtra("Integer", songdemogoogo);
                demogoogo.putExtra("Demo","demogoogo");
                startActivity(demogoogo);
            }
            if(flag.equalsIgnoreCase("demoaaah")){
                Intent demognung = new Intent(Demo.this, Demo.class);
                demognung.putExtra("Integer", songdemognung);
                demognung.putExtra("Demo","demognung");
                startActivity(demognung);
            }
            if(flag.equalsIgnoreCase("demoooze")){
                Intent demoaaah = new Intent(Demo.this, Demo.class);
                demoaaah.putExtra("Integer", songdemogaaah);
                demoaaah.putExtra("Demo","demoaaah");
                startActivity(demoaaah);
            }
        }
    };

    Button.OnClickListener latihanbtnOnClickListener = new ImageButton.OnClickListener() {

        @Override
        public void onClick(View v) {
            mediaPlayer.stop();
            buttonPlayPause.setImageResource(R.drawable.playbutton);
            stateMediaPlayer = stateMP_NotStarter;
            String flag = String.valueOf(getIntent().getStringExtra("Demo"));
            if(flag.equalsIgnoreCase("demohum")) {
                Intent hum = new Intent(Demo.this, Pemanasan.class);
                hum.putExtra("Integer", songhum);
                hum.putExtra("Flag", "optionone");
                hum.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(hum);
            }
            if(flag.equalsIgnoreCase("demogoogo")) {
                Intent googo = new Intent(Demo.this, Pemanasan.class);
                googo.putExtra("Integer", songgoogo);
                googo.putExtra("Flag","optiontwo");
                googo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(googo);
            }
            if(flag.equalsIgnoreCase("demognung")) {
                Intent gnung = new Intent(Demo.this, Pemanasan.class);
                gnung.putExtra("Integer", songgnung);
                gnung.putExtra("Flag","optionthree");
                gnung.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gnung);
            }
            if(flag.equalsIgnoreCase("demoaaah")) {
                Intent aaah = new Intent(Demo.this, Pemanasan.class);
                aaah.putExtra("Integer", songaaah);
                aaah.putExtra("Flag", "optionfour");
                aaah.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(aaah);
            }
            if(flag.equalsIgnoreCase("demoooze")) {
                Intent ooze = new Intent(Demo.this, Pemanasan.class);
                ooze.putExtra("Integer", songooze);
                ooze.putExtra("Flag", "optionfive");
                ooze.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ooze);
            }
        }
    };
    SeekBar.OnSeekBarChangeListener seekBarOnSeekChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            // TODO Auto-generated method stub

            if (fromUser) {
                mediaPlayer.seekTo(progress);
                seekBar.setProgress(progress);
            }

        }
    };
    public void onBackPressed() {
        if (mediaPlayer != null && mediaPlayer.isPlaying())
            mediaPlayer.stop();
        finish();
        Intent i = new Intent(getApplicationContext(), PilihPemanasan.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
        onDestroy();
    }
}


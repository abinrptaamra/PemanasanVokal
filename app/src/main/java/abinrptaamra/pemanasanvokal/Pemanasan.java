package abinrptaamra.pemanasanvokal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.SeekBar;
import android.widget.ArrayAdapter;
import android.widget.AdapterView;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.widget.Spinner;
import android.widget.Toast;


public class Pemanasan extends PilihPemanasan{

    int songdemohum = R.raw.demo_aaah;
    int songdemogoogo = R.raw.demo_googo;
    int songdemognung = R.raw.demo_gnung;
    int songdemogaaah = R.raw.demo_aaah;
    int songdemoooze = R.raw.demo_ooze;

    String[] nada_dasar;
    MediaPlayer mediaPlayer;
    ImageButton buttonPlayPause;
    ImageButton nextbtn;
    ImageButton prevbtn;
    Button demobtn;
    TextView timePos;
    TextView textpemanasan;
    SeekBar seekBar;
    Handler handler;
    Toolbar toolbar;

    private int stateMediaPlayer;
    private final int stateMP_NotStarter = 0;
    private final int stateMP_Playing = 1;
    private final int stateMP_Pausing = 2;

    private int mediaPos;
    private int mediaMax;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.latpem);

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

        textpemanasan = (TextView) findViewById(R.id.textnamapemanasan);
        String flag = String.valueOf(getIntent().getStringExtra("Flag"));
        if(flag.equalsIgnoreCase("optionone")) {
            textpemanasan.setText("Pemanasan Hum");
        }
        if(flag.equalsIgnoreCase("optiontwo")) {
            textpemanasan.setText("Pemanasan Googo");
        }
        if(flag.equalsIgnoreCase("optionthree")) {
            textpemanasan.setText("Pemanasan Gnung");
        }
        if(flag.equalsIgnoreCase("optionfour")) {
            textpemanasan.setText("Pemanasan Aaah");
        }
        if(flag.equalsIgnoreCase("optionfive")) {
            textpemanasan.setText("Pemanasan Ooze");
        }
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        timePos = (TextView) findViewById(R.id.TimeSeek);
        buttonPlayPause = (ImageButton) findViewById(R.id.playbutton);
        buttonPlayPause.setOnClickListener(buttonPlayPauseOnClickListener);
        nextbtn = (ImageButton) findViewById(R.id.nextbtn);
        nextbtn.setOnClickListener(nextbtnOnClickListener);
        demobtn = (Button) findViewById(R.id.demobtn);
        demobtn.setOnClickListener(demobtnOnClickListener);
        prevbtn = (ImageButton) findViewById(R.id.prevbtn);
        prevbtn.setOnClickListener(prevbtnOnClickListener);
        seekBar.setOnSeekBarChangeListener(seekBarOnSeekChangeListener);
        initMediaPlayer();
        mediaPos = mediaPlayer.getCurrentPosition();
        mediaMax = mediaPlayer.getDuration();
        seekBar.setMax(mediaMax); // Set the Maximum range of the
        // seekBar.setProgress(mediaPos);// set
        // current progress to song's
        seekBar.setProgress(mediaPos);// set current progress to song's
        handler.removeCallbacks(moveSeekBarThread);
        handler.postDelayed(moveSeekBarThread, 100);
        nada_dasar = this.getResources().getStringArray(R.array.nada_dasar);
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.nada_dasar, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View v,
                                       int position, long id) {

                mediaPlayer.release();
                buttonPlayPause.setImageResource(R.drawable.playbutton);
                stateMediaPlayer = stateMP_NotStarter;
                pilihItemSpinner(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }

        });





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



    private void initMediaPlayer() {
        handler = new Handler();
        mediaPlayer = new MediaPlayer();
        stateMediaPlayer = stateMP_NotStarter;
    }




    public void pilihItemSpinner(int songIndex){
        Bundle b = getIntent().getExtras();
        int[] Song = b.getIntArray("Integer");
        mediaPlayer = MediaPlayer.create(getApplicationContext(), Song[songIndex]);
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
            mediaPlayer.setOnCompletionListener(new OnCompletionListener() {

                // @Override
                public void onCompletion(MediaPlayer arg0) {
                    // File has ended !!!

                    buttonPlayPause.setImageResource(R.drawable.playbutton);
                    stateMediaPlayer = stateMP_NotStarter;
                }
            });
        }

    };
    ImageButton.OnClickListener nextbtnOnClickListener = new ImageButton.OnClickListener() {

        @Override
        public void onClick(View v) {
            mediaPlayer.stop();
            buttonPlayPause.setImageResource(R.drawable.playbutton);
            stateMediaPlayer = stateMP_NotStarter;
            String flag = String.valueOf(getIntent().getStringExtra("Flag"));
            if(flag.equalsIgnoreCase("optionone")){
                Intent googo = new Intent(Pemanasan.this, Pemanasan.class);
                googo.putExtra("Integer", songgoogo);
                googo.putExtra("Flag","optiontwo");
                googo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(googo);
            }
            if(flag.equalsIgnoreCase("optiontwo")) {
                Intent gnung = new Intent(Pemanasan.this, Pemanasan.class);
                gnung.putExtra("Integer", songgnung);
                gnung.putExtra("Flag","optionthree");
                gnung.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gnung);
            }
            if(flag.equalsIgnoreCase("optionthree")) {
                Intent aaah = new Intent(Pemanasan.this, Pemanasan.class);
                aaah.putExtra("Integer", songaaah);
                aaah.putExtra("Flag","optionfour");
                aaah.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(aaah);
            }
            if(flag.equalsIgnoreCase("optionfour")) {
                Intent ooze = new Intent(Pemanasan.this, Pemanasan.class);
                ooze.putExtra("Integer", songooze);
                ooze.putExtra("Flag", "optionfive");
                ooze.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ooze);
            }
            if(flag.equalsIgnoreCase("optionfive")) {
                Toast.makeText(getApplicationContext(), "ini adalah latihan terakhir", Toast.LENGTH_SHORT).show();
                Intent ooze = new Intent(Pemanasan.this, Pemanasan.class);
                ooze.putExtra("Integer", songooze);
                ooze.putExtra("Flag", "optionfive");
                ooze.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ooze);
            }
        }
    };

    ImageButton.OnClickListener prevbtnOnClickListener = new ImageButton.OnClickListener() {

        @Override
        public void onClick(View v) {
            mediaPlayer.stop();
            buttonPlayPause.setImageResource(R.drawable.playbutton);
            stateMediaPlayer = stateMP_NotStarter;
            String flag = String.valueOf(getIntent().getStringExtra("Flag"));
            if(flag.equalsIgnoreCase("optionone")) {
                Toast.makeText(getApplicationContext(), "ini adalah latihan Pertama", Toast.LENGTH_SHORT).show();
                Intent hum = new Intent(Pemanasan.this, Pemanasan.class);
                hum.putExtra("Integer", songhum);
                hum.putExtra("Flag", "optionone");
                hum.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(hum);
            }
            if(flag.equalsIgnoreCase("optiontwo")){
                Intent hum = new Intent(Pemanasan.this, Pemanasan.class);
                hum.putExtra("Integer", songhum);
                hum.putExtra("Flag", "optionone");
                hum.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(hum);
            }
            if(flag.equalsIgnoreCase("optionthree")){
                Intent googo = new Intent(Pemanasan.this, Pemanasan.class);
                googo.putExtra("Integer", songgoogo);
                googo.putExtra("Flag", "optiontwo");
                googo.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(googo);
            }
            if(flag.equalsIgnoreCase("optionfour")){
                Intent gnung = new Intent(Pemanasan.this, Pemanasan.class);
                gnung.putExtra("Integer", songgnung);
                gnung.putExtra("Flag", "optionthree");
                gnung.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(gnung);
            }
            if(flag.equalsIgnoreCase("optionfive")){
                Intent aaah = new Intent(Pemanasan.this, Pemanasan.class);
                aaah.putExtra("Integer", songaaah);
                aaah.putExtra("Flag", "optionfour");
                aaah.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(aaah);
            }
        }
    };

    Button.OnClickListener demobtnOnClickListener = new ImageButton.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (mediaPlayer != null && mediaPlayer.isPlaying())
                mediaPlayer.stop();
            buttonPlayPause.setImageResource(R.drawable.playbutton);
            stateMediaPlayer = stateMP_NotStarter;
            String flag = String.valueOf(getIntent().getStringExtra("Flag"));
            if(flag.equalsIgnoreCase("optionone")) {
                Intent demohum = new Intent(Pemanasan.this, Demo.class);
                demohum.putExtra("Integer", songdemohum);
                demohum.putExtra("Demo", "demohum");
                startActivity(demohum);
            }
            if(flag.equalsIgnoreCase("optiontwo")) {
                Intent demogoogo = new Intent(Pemanasan.this, Demo.class);
                demogoogo.putExtra("Integer", songdemogoogo);
                demogoogo.putExtra("Demo", "demogoogo");
                startActivity(demogoogo);
                }
            if(flag.equalsIgnoreCase("optionthree")) {
                Intent demognung = new Intent(Pemanasan.this, Demo.class);
                demognung.putExtra("Integer", songdemognung);
                demognung.putExtra("Demo", "demognung");
                startActivity(demognung);
            }
            if(flag.equalsIgnoreCase("optionfour")) {
                Intent demoaaah = new Intent(Pemanasan.this, Demo.class);
                demoaaah.putExtra("Integer", songdemogaaah);
                demoaaah.putExtra("Demo", "demoaaah");
                startActivity(demoaaah);
            }
            if(flag.equalsIgnoreCase("optionfive")) {
                Intent demoooze = new Intent(Pemanasan.this, Demo.class);
                demoooze.putExtra("Integer", songdemoooze);
                demoooze.putExtra("Demo", "demoooze");
                startActivity(demoooze);
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

    }

}
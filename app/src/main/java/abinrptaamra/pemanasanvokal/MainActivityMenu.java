package abinrptaamra.pemanasanvokal;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class MainActivityMenu extends ActionBarActivity {
    private long lastPressedTime;
    private Toolbar toolbar;
    private static final int PERIOD = 2000;
   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main_activity_menu);

       toolbar = (Toolbar) findViewById(R.id.app_bar);
       setSupportActionBar(toolbar);

        Button Pemanasan = (Button) findViewById(R.id.btnpemanasan);
        Pemanasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivityMenu.this, PilihPemanasan.class);
                startActivity(intent);
            }
        });


        Button Pernafasan = (Button) findViewById(R.id.btnlatper);
        Pernafasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivityMenu.this, Latper.class);
                startActivity(intent);
            }
        });



        }



        @Override
        public boolean onKeyDown(int keyCode, KeyEvent event) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                switch (event.getAction()) {
                    case KeyEvent.ACTION_DOWN:
                        if (event.getDownTime() - lastPressedTime < PERIOD) {
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "Tekan Kembali Untuk Keluar",
                                    Toast.LENGTH_SHORT).show();
                            lastPressedTime = event.getEventTime();
                        }
                        return true;
                }
            }
            return false;
        }
    }

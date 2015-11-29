package abinrptaamra.pemanasanvokal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class PilihPemanasan extends ActionBarActivity {
    Button btnhum, btngoogo, btngnung, btnaaah, btnooze;
    private Toolbar toolbar;


    public static int[] songhum = {R.raw.humc2, R.raw.humd2,  R.raw.hume2,  R.raw.humf2,  R.raw.humg2, R.raw.huma2, R.raw.humb2,  R.raw.humc3};
    public static int[] songgoogo = {R.raw.googoc2, R.raw.googod2, R.raw.googoe2, R.raw.googof2, R.raw.googog2, R.raw.googoa2, R.raw.googob2, R.raw.googoc3};
    public static int[] songgnung = {R.raw.gnungc2, R.raw.gnungd2, R.raw.gnunge2, R.raw.gnungf2, R.raw.gnungg2, R.raw.gnunga2, R.raw.gnungb2, R.raw.gnungc3};
    public static int[] songaaah = {R.raw.aaahc2, R.raw.aaahd2, R.raw.aaahe2, R.raw.aaahf2, R.raw.aaahg2, R.raw.aaaha2, R.raw.aaahb2, R.raw.aaahc3};
    public static int[] songooze = {R.raw.oozec2, R.raw.oozed2, R.raw.oozee2, R.raw.oozef2, R.raw.oozeg2, R.raw.oozea2, R.raw.oozeb2, R.raw.oozec3};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pilih_pemanasan);

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


        btnhum = (Button) findViewById(R.id.btnhum);
        btnhum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent hum = new Intent(PilihPemanasan.this, Pemanasan.class);
                hum.putExtra("Integer", songhum);
                hum.putExtra("Flag", "optionone");
                startActivity(hum);
            }
        });
        btngoogo = (Button) findViewById(R.id.btngoogo);
        btngoogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent googo = new Intent(PilihPemanasan.this, Pemanasan.class);
                googo.putExtra("Integer", songgoogo);
                googo.putExtra("Flag","optiontwo");
                startActivity(googo);
            }
        });
        btngnung = (Button) findViewById(R.id.btngnung);
        btngnung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent gnung = new Intent(PilihPemanasan.this, Pemanasan.class);
                gnung.putExtra("Integer", songgnung);
                gnung.putExtra("Flag","optionthree");
                startActivity(gnung);
            }
        });
        btnaaah = (Button) findViewById(R.id.btnaaah);
        btnaaah.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        Intent aaah = new Intent(PilihPemanasan.this, Pemanasan.class);
                        aaah.putExtra("Integer", songaaah);
                        aaah.putExtra("Flag", "optionfour");
                        startActivity(aaah);
                    }
                });
        btnooze = (Button) findViewById(R.id.btnooze);
        btnooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent ooze = new Intent(PilihPemanasan.this, Pemanasan.class);
                ooze.putExtra("Integer", songooze);
                ooze.putExtra("Flag", "optionfive");
                startActivity(ooze);
            }
        });

    }



    }





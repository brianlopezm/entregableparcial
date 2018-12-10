package com.example.brian.entregableparcial;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityEntregable3 extends AppCompatActivity {

    private boolean active=false;
    private boolean restart=false;
    private int cant=0;
    public static final String TAG= MainActivity.class.getCanonicalName();
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences mPreferences = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor= mPreferences.edit();
        preferencesEditor.putInt("cant",cant);
        preferencesEditor.putBoolean("restart",restart);
        preferencesEditor.apply();

    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences mPreferences = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor= mPreferences.edit();
        preferencesEditor.putInt("cant",cant);
        preferencesEditor.putBoolean("restart",restart);
        preferencesEditor.apply();

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        SharedPreferences mPreferences = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor preferencesEditor= mPreferences.edit();
        preferencesEditor.putInt("cant",cant);
        preferencesEditor.putBoolean("restart",restart);
        preferencesEditor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregable3);

        SharedPreferences mPreferences=getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
        cant=mPreferences.getInt("cant",0);
        restart=mPreferences.getBoolean("restart",false);

        final TextView tv= (TextView) findViewById(R.id.tView_time);
        final Button start= (Button) findViewById(R.id.butt_start);
        final Button stop= (Button) findViewById(R.id.butt_stop);
        final Button reset= (Button) findViewById(R.id.butt_reset);

        stop.setEnabled(false);
        reset.setEnabled(false);
        tv.setText(String.valueOf(cant));


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                active = true;
                if (cant!=0 && restart){
                    cant=0;
                    restart = false;
                }

                start.setEnabled(false);
                stop.setEnabled(true);
                AsyncTask<?, ?, ?> asyncTask = new AsyncTask<Object, Integer, Integer>() {
                    @Override
                    protected Integer doInBackground(Object... objects) {
                        while (active) {
                            try {
                                synchronized (ActivityEntregable3.this) {
                                    ActivityEntregable3.this.wait(1000);
                                }
                                if (!active)
                                    break;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            cant++;
                            Log.d(TAG, "Van " + cant + " segundos");
                            this.publishProgress(cant);

                        }

                        return cant;
                    }
                    @Override
                    protected void onProgressUpdate(Integer... progress) {
                        tv.setText(String.valueOf(progress[0]));

                    }
                    @Override
                    protected void onPostExecute(Integer result) {
                        tv.setText( result + " sec");
                    }
                };

                asyncTask.execute();
            }
        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setEnabled(false);
                start.setEnabled(true);
                reset.setEnabled(true);
                active=false;
                synchronized (ActivityEntregable3.this){
                    ActivityEntregable3.this.notifyAll();
                }
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setEnabled(false);
                start.setEnabled(true);
                reset.setEnabled(false);
                restart=true;
                active=false;
                tv.setText("0");
                synchronized (ActivityEntregable3.this){
                    ActivityEntregable3.this.notifyAll();
                }

            }
        });



    }
}

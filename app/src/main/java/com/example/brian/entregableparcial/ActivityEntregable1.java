package com.example.brian.entregableparcial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ActivityEntregable1 extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregable1);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG,"El usuario apretó el botón");
                String s = "Hola Mundo!";
                Toast toast = Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG);
                toast.show();
            }
        });


    }
}

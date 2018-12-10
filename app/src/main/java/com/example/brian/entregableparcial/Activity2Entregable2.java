package com.example.brian.entregableparcial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class Activity2Entregable2 extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getCanonicalName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_entregable2);

        Button b= (Button) findViewById(R.id.butt_MULTIPLICAR);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nro1 = Integer.valueOf(((EditText) findViewById(R.id.editText_nro1)).getText().toString());
                int nro2 = Integer.valueOf(((EditText) findViewById(R.id.editText_nro2)).getText().toString());
                Intent i = new Intent();
                int result= nro1*nro2;
                i.putExtra("MULT",result);
                setResult(RESULT_OK,i);
                finish();
            }
        });
    }

}

package com.example.brian.entregableparcial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Boton que llama a Entregable 1
        Button be1 = (Button) findViewById(R.id.butt_1);
        be1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ActivityEntregable1.class);
                startActivity(i);
            }
        });

        //Boton que llama a Entregable 2
        Button be2 = (Button) findViewById(R.id.butt_2);
        be2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ActivityEntregable2.class);
                startActivity(i);
            }
        });

        //Boton que llama a Entregable 3
        Button be3 = (Button) findViewById(R.id.butt_3);
        be3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ActivityEntregable3.class);
                startActivity(i);
            }
        });

        //Boton que llama a Entregable 4
        Button be4 = (Button) findViewById(R.id.butt_4);
        be4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,ActivityEntregable4.class);
                startActivity(i);
            }
        });
    }
}

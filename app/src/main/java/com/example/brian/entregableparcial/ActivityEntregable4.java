package com.example.brian.entregableparcial;

import android.os.Messenger;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityEntregable4 extends AppCompatActivity {

    public static final String ITERATION="iteration";

    private static final String TAG=MessengerService.class.getCanonicalName();

    private  TextView tv;
    private  TextView tv2;
    private  TextView tv3;
    private  LocalReciever reciever= new LocalReciever();
    public static final  String WHO="who";

    private class LocalReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent){
            String msg = intent.getStringExtra(MyIntentService.RESPONSE);
            int who = intent.getIntExtra(WHO,-1);
            switch (who){
                case MyIntentService.ME:
                    tv.setText(msg);
                    break;
                case MyService.ME:
                    tv2.setText(msg);
                    break;
                case MessengerService.ME:
                    tv3.setText(msg);
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entregable4);

        tv=(TextView) findViewById(R.id.tView_serv);
        tv2= (TextView) findViewById(R.id.tView_intents);
        tv3=(TextView) findViewById(R.id.tView_msg);
        Button s1= (Button) findViewById(R.id.butt_intent);
        Button s2=(Button) findViewById(R.id.butt_Service);


        s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0;i<4;i++){
                    Intent mServiceIntent = new Intent(ActivityEntregable4.this,MyService.class);
                    mServiceIntent.putExtra(ITERATION, i);
                    startService(mServiceIntent);
                }
            }
        });

        s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<4;i++){
                    Intent mServiceIntent = new Intent(ActivityEntregable4.this,MyIntentService.class);
                    mServiceIntent.putExtra(ITERATION, i);
                    startService(mServiceIntent);
                }
            }
        });
        LocalBroadcastManager.getInstance(this).registerReceiver(reciever, new IntentFilter(MyIntentService.RESPONSE_ACTION));
    }

  
    @Override
    protected void onDestroy(){
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(reciever);
    }

}


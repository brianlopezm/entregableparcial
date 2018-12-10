package com.example.brian.entregableparcial;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class MessengerService extends Service {

    private static final String TAG=MessengerService.class.getCanonicalName();
    public static final int ME=3;
    private final Random nGenerator= new Random();
    private final int NEXT_RANDOM=1;
    private incomingHandler msh;


    class incomingHandler extends Handler{
        public incomingHandler(){

        }

        @Override
        public  void handleMessage(Message msg){
            switch (msg.what){
                case NEXT_RANDOM:
                    String text= "Random generator " +nGenerator.nextInt(100);
                    Log.d(TAG,text);
                    Intent response=new Intent(MyIntentService.RESPONSE_ACTION);
                    response.putExtra(MyIntentService.RESPONSE,text);
                    response.putExtra(ActivityEntregable4.WHO,ME);
                    LocalBroadcastManager.getInstance(MessengerService.this).sendBroadcast(response);
                    break;
                default:
                    super.handleMessage(msg);

            }
        }

    }

    final Messenger mMessenger = new Messenger(new incomingHandler());
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Toast.makeText(getApplicationContext(),"binding",Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){

        return START_REDELIVER_INTENT;
    }

}
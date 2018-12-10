package com.example.brian.entregableparcial;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.beans.PropertyChangeEvent;

public class MyService extends Service {

    public static final int ME=2;
    private  static final String TAG= MyService.class.getCanonicalName();
    private Looper serviceLooper;
    private MyServiceHandler msh;
    private class MyServiceHandler extends Handler{
        public  MyServiceHandler(Looper looper){
            super(looper);
        }
        @Override
        public  void handleMessage(Message message){
            Intent intent = (Intent) message.obj;
            int iteration = intent.getIntExtra(ActivityEntregable4.ITERATION,0);
            String msg = "Procesando iteracion " + iteration;
            Intent response= new Intent(MyIntentService.RESPONSE_ACTION);
            response.putExtra(MyIntentService.RESPONSE,msg);
            response.putExtra(ActivityEntregable4.WHO,ME);
            Log.d(TAG,msg);
            try{
                Thread.sleep(3000);

            }
            catch(InterruptedException e){
                e.printStackTrace();;
            }
            LocalBroadcastManager.getInstance(MyService.this).sendBroadcast(response);
            stopSelf(message.arg1);
        }
    }
    public MyService() {

    }

    @Override
    public void onCreate(){
        HandlerThread thread = new HandlerThread("MyServiceExample",Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        serviceLooper=thread.getLooper();
        msh= new MyServiceHandler(serviceLooper);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        final Message msg = msh.obtainMessage();
        msg.arg1=startId;
        msg.obj=intent;

        new Thread(new Runnable() {
            @Override
            public void run() {
                msh.sendMessage(msg);
            }
        }).start();

        return START_STICKY;
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return  null;
    }
}
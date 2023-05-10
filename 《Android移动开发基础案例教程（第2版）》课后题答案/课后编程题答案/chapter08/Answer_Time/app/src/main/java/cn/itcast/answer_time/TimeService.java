package cn.itcast.answer_time;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.format.Time;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class TimeService extends Service {
    int time = 60;
    private Timer timer;
    private TimerTask task;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        timer = new Timer();     //创建计时器对象
        task = new TimerTask() {
            @Override
            public void run() {
                time = time - 1;
                Intent intent = new Intent();
                intent.setAction("cn.itcast.answer_time");
                intent.putExtra("time",time);
                sendBroadcast(intent);
                if(time <=0){
                    stopSelf();
                }
            }
        };
        timer.schedule(task,0,1000);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
        task = null;
        timer = null;
    }
}

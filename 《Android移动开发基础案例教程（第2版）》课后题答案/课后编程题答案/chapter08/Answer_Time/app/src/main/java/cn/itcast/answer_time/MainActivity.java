package cn.itcast.answer_time;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private boolean flag = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    Intent intent = new Intent(MainActivity.this,TimeService.class);
                    startService(intent);
                    button.setText("倒计时60秒");
                }
            }
        });
        MyBroadcastReceiver  receiver = new MyBroadcastReceiver(); //实例化广播接收者
        String action = "cn.itcast.answer_time";
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
        registerReceiver(receiver,intentFilter); //注册广播
    }

    public class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int time = intent.getIntExtra("time",60);
            if(time < 60 && time >0){
                flag = false;
            }else{
                flag = true;
            }
            button.setText("倒计时"+time+"秒");
            Log.e("MainActivity","shengyu "+time);
        }
    }
}

package cn.itcast.answer_customdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        final CommonDialog dialog = new CommonDialog(MainActivity.this);
        dialog.setTitle("提示");
        dialog.setMessage("您确定要退出本应用吗？");
        dialog.setNegtive("取消");
        dialog.setPositive("确定");
        dialog.setOnClickBottomListener(new CommonDialog.
                OnClickBottomListener() {
            @Override
            public void onPositiveClick() { //确定按钮的点击事件
                dialog.dismiss();
                MainActivity.this.finish();
            }
            @Override
            public void onNegtiveClick() { //取消按钮的点击事件
                dialog.dismiss();
            }
        });
        dialog.show();
        return true;
    }
}

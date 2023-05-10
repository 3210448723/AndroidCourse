package cn.itcast.login;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_username,et_psw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_username = findViewById(R.id.et_username);
        et_psw = findViewById(R.id.et_psw);
        findViewById(R.id.btn_login).setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        String username = et_username.getText().toString().trim();
        String psw = et_psw.getText().toString().trim();
        if(username== null ||username.isEmpty()){
            Toast.makeText(MainActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        if(psw == null || psw.isEmpty()){
            Toast.makeText(MainActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences sp = getSharedPreferences("login",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("用户名", username);
        editor.putString("密码",psw);
        editor.commit();
        Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
    }
}

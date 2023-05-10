package cn.itcast.answer_addition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 private EditText ed_addend;
 private EditText ed_augend;
 private TextView tv_result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed_addend = findViewById(R.id.addend);
        ed_augend = findViewById(R.id.augend);
        tv_result = findViewById(R.id.result);
        findViewById(R.id.equal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addend = ed_addend.getText().toString().trim();
                String augend = ed_augend.getText().toString().trim();
                if(addend ==null && addend.isEmpty()){
                    Toast.makeText(MainActivity.this,"请输入加数",Toast.LENGTH_SHORT).show();
                    return;
                }else if (augend ==null && augend.isEmpty()){
                    Toast.makeText(MainActivity.this,"请输入被加数",Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    int result = Integer.parseInt(addend)+Integer.parseInt(augend);
                    tv_result.setText(""+result);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"请输入整数",Toast.LENGTH_SHORT).show();
                }



            }
        });
    }
}

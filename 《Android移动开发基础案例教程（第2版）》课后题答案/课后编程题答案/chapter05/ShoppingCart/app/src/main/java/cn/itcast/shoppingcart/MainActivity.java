package cn.itcast.shoppingcart;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText et_name,et_price,et_number;
    private ListView listView;
    private String name,price,number;
    private  MyHelper myHelper;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = findViewById(R.id.et_name);
        et_price = findViewById(R.id.et_price);
        et_number =  findViewById(R.id.et_number);
        listView = findViewById(R.id.listView);
        Button add = findViewById(R.id.add);
        Button query = findViewById(R.id.query);
        Button update = findViewById(R.id.update);
        Button delete = findViewById(R.id.delete);
        add.setOnClickListener(this);
        query.setOnClickListener(this);
        update.setOnClickListener(this);
        delete.setOnClickListener(this);
        myHelper = new MyHelper(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add:
                db = myHelper.getWritableDatabase();
                name = et_name.getText().toString();
                price = et_price.getText().toString();
                number = et_number.getText().toString();
                ContentValues values = new ContentValues();        //创建ContentValues对象
                values.put("name", name);             //将数据添加到ContentValues对象
                values.put("price", price);
                values.put("number", number);
                db.insert("cart", null, values);
                db.close();
                Toast.makeText(this, "信息已添加", Toast.LENGTH_SHORT).show();
                break;
            case R.id.query:
                Log.e("yanwenhua","123");
                List<CartBean> list = new ArrayList<>();
                db = myHelper.getWritableDatabase();
                Cursor cursor = db.query("cart", null, null, null, null,
                        null, null);
                if (cursor.getCount() == 0) {
                    Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show();
                } else {
                    while (cursor.moveToNext()) {
                        CartBean cartBean = new CartBean();
                        int nameIndex = cursor.getColumnIndex("name");
                        int priceIndex = cursor.getColumnIndex("price");
                        int numberIndex = cursor.getColumnIndex("number");
                        String name = cursor.getString(nameIndex);
                        String price = cursor.getString(priceIndex);
                        String number = cursor.getString(numberIndex);
                        Log.e("yanwenhua","cursor.getCount();--"+cursor.getCount()+"name-"+name+"  "+price+"  "+number);
                        cartBean.setName(name);
                        cartBean.setPrice(price);
                        cartBean.setNumber(number);
                        list.add(cartBean);
                    }
                    CartAdapter adapter = new CartAdapter(MainActivity.this,list);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
                cursor.close();
                db.close();
                break;
            case  R.id.update:
                name = et_name.getText().toString();
                price = et_price.getText().toString();
                number = et_number.getText().toString();
                db = myHelper.getWritableDatabase();
                values = new ContentValues();       // 要修改的数据
                values.put("number",number);
                values.put("price",price);
                db.update("cart", values, "name=?",
                        new String[]{name}); // 更新并得到行数
                db.close();
                Toast.makeText(this, "信息已修改", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                db = myHelper.getWritableDatabase();
                db.delete("cart", null, null);
                List<CartBean> list2 = new ArrayList<>();
                CartAdapter adapter = new CartAdapter(MainActivity.this,list2);
                listView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                db.close();
                Toast.makeText(this, "信息已删除", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}

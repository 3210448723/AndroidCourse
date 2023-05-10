package cn.itcast.readcontacts;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button query_contacts = findViewById(R.id.query_contacts);
        query_contacts.setOnClickListener(this);
        textView = findViewById(R.id.tv);
    }
    @Override
    public void onClick(View v) {
        //查询联系人的电话号码
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{"android.permission.READ_CONTACTS"}, 1);
    }
    public void fetchContactInformation() {
        String id,name,phoneNumber;
        String contacts = "";
        ContentResolver contentResolver = this.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while(cursor.moveToNext()) {
            id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            contacts+= "姓名："+name+"\n";
            //获取 Phone Number
            Cursor phoneCursor = contentResolver.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id, null, null);
            contacts+= "电话：";
            while(phoneCursor.moveToNext()) {
                phoneNumber = phoneCursor.getString(
                        phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                System.out.println("id="+id+" name="+name+" phoneNumber="+phoneNumber);
                contacts+= phoneNumber +"         ";
            }
            contacts+="\n\n";
            phoneCursor.close();
        }
        textView.setText(contacts);
        cursor.close();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"已获取权限",Toast.LENGTH_SHORT).show();
                    fetchContactInformation();
                }else{

                }
            }
        }
    }
}

package cn.itcast.answer_customdialog;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class CommonDialog extends AlertDialog {
    private TextView titleTv ;               //显示的标题
    private TextView messageTv ;             //显示的消息
    private Button negtiveBn ,positiveBn;  //确认和取消按钮
    public CommonDialog(Context context) {
        super(context);
    }
    private String message;
    private String title;
    private String positive,negtive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        initView();     //初始化界面控件
        initEvent();    //初始化界面控件的点击事件
    }
    //初始化界面控件
    private void initView() {
        negtiveBn = (Button) findViewById(R.id.negtive);
        positiveBn = (Button) findViewById(R.id.positive);
        titleTv = (TextView) findViewById(R.id.title);
        messageTv = (TextView) findViewById(R.id.message);
    }
    //初始化界面控件的显示数据
    private void refreshView() {
        //如果自定义了title和message会 显示自定义的信息，否则不显示title和message的信息
        if (!TextUtils.isEmpty(title)) {
            titleTv.setText(title);                 //设置标题控件的文本为自定义的title
            titleTv.setVisibility(View.VISIBLE); //标题控件设置为显示状态
        }else {
            titleTv.setVisibility(View.GONE);     //标题控件设置为隐藏状态
        }
        if (!TextUtils.isEmpty(message)) {
            messageTv.setText(message); //设置消息控件的文本为自定义的message信息
        }
        //如果自定义了按钮的文本，则按钮显示自定义的文本，否则，按钮显示“确定”或“取消”文本
        if (!TextUtils.isEmpty(positive)) {
            positiveBn.setText(positive); //设置按钮的文本为自定义的文本信息
        }else {
            positiveBn.setText("确定");    //设置按钮文本为“确定”
        }
        if (!TextUtils.isEmpty(negtive)) {
            negtiveBn.setText(negtive);
        }else {
            negtiveBn.setText("取消");
        }
    }
    //初始化界面的确定和取消监听器
    private void initEvent() {
        //设置确定按钮的点击事件的监听器
        positiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickBottomListener!= null) {
                    onClickBottomListener.onPositiveClick();
                }
            }
        });
        //设置取消按钮的点击事件的监听器
        negtiveBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ( onClickBottomListener!= null) {
                    onClickBottomListener.onNegtiveClick();
                }
            }
        });
    }
    @Override
    public void show() {
        super.show();
        refreshView();
    }
    public interface OnClickBottomListener{
        void onPositiveClick();//实现确定按钮点击事件的方法
        void onNegtiveClick(); //实现取消按钮点击事件的方法
    }
    //设置确定取消按钮的回调
    public OnClickBottomListener onClickBottomListener;
    public CommonDialog setOnClickBottomListener(OnClickBottomListener
                                                         onClickBottomListener){
        this.onClickBottomListener = onClickBottomListener;
        return this;
    }
    public CommonDialog setMessage(String message) {
        this.message = message;
        return this ;
    }
    public CommonDialog setTitle(String title) {
        this.title = title;
        return this ;
    }
    public CommonDialog setPositive(String positive) {
        this.positive = positive;
        return this ;
    }
    public CommonDialog setNegtive(String negtive) {
        this.negtive = negtive;
        return this ;
    }
}

package cn.itcast.shoppingcart;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;
public class CartAdapter extends BaseAdapter {
    private List<CartBean> list;
    private LayoutInflater layoutInflater;
    public CartAdapter(Context context, List<CartBean> list){
        this.layoutInflater = LayoutInflater.from(context);
        this.list = list;
    }
    @Override
    public int getCount() {
        Log.e("CartAdapter","list.size()--"+list.size());
        return list.size();
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.listview_item,null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }
        CartBean cartBean = list.get(position);
        viewHolder.tv_name.setText("商品名称："+cartBean.getName());
        viewHolder.tv_price.setText("商品价格："+cartBean.getPrice());
        viewHolder.tv_number.setText("商品数量："+cartBean.getNumber());
        Log.e("CartAdapter","cartBean.getName()-"+cartBean.getName()+"  "+cartBean.getPrice()+"  "+cartBean.getNumber());
        return convertView;
    }
    class ViewHolder{
        TextView tv_name;
        TextView tv_price;
        TextView tv_number;
        public ViewHolder(View view){
            tv_name =  view.findViewById(R.id.tv_name);
            tv_price =  view.findViewById(R.id.tv_price);
            tv_number = view.findViewById(R.id.tv_number);
        }
    }

}

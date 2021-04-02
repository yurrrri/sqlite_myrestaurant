package ddwucom.mobile.finalreport;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;

public class ResAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<RestaurantData> restaurantDataList;
    private LayoutInflater inflater;
    int image_source = 0;

    public ResAdapter(Context context, int layout, ArrayList<RestaurantData> restaurantDataList) {

        this.context = context;
        this.layout = layout;
        this.restaurantDataList = restaurantDataList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return restaurantDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return restaurantDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return restaurantDataList.get(position).get_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        ViewHolder holder;

        if (restaurantDataList.get(pos).get_id()==1){
            image_source = R.mipmap.bangigobchang;
        } else if (restaurantDataList.get(pos).get_id()==2){
            image_source = R.mipmap.ddchicken;
        } else if (restaurantDataList.get(pos).get_id()==3){
            image_source = R.mipmap.garden;
        } else if (restaurantDataList.get(pos).get_id()==4){
            image_source = R.mipmap.ggomak;
        } else if (restaurantDataList.get(pos).get_id()==5){
            image_source = R.mipmap.hamburger;
        } else{
            image_source = R.mipmap.ic_launcher;
        }

        if (convertView == null) {
            convertView = inflater.inflate(layout, parent, false);

            holder = new ViewHolder();
            holder.tvId = convertView.findViewById(R.id.tvId);
            holder.imageView = convertView.findViewById(R.id.imageView);
            holder.ratingBar = convertView.findViewById(R.id.ratingBar);
            holder.tvName = convertView.findViewById(R.id.tvName);
            holder.tvMenu = convertView.findViewById(R.id.tvMenu);
            holder.tvReview = convertView.findViewById(R.id.tvReview);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvId.setText(String.valueOf(restaurantDataList.get(pos).get_id()));
        holder.imageView.setImageResource(image_source);
        holder.ratingBar.setRating(restaurantDataList.get(pos).getRating());
        holder.tvName.setText(restaurantDataList.get(pos).getName());
        holder.tvMenu.setText(restaurantDataList.get(pos).getMenu());
        holder.tvReview.setText(restaurantDataList.get(pos).getReview());
        return convertView;
    }

    //정적 클래스의 변수는 한번 초기화 후 같은 값을 계속 사용함
    static class ViewHolder{
        TextView tvId;
        ImageView imageView;
        RatingBar ratingBar;
        TextView tvName;
        TextView tvMenu;
        TextView tvReview;
    }
}

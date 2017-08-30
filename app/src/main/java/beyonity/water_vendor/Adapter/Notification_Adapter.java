package beyonity.water_vendor.Adapter;

/**
 * Created by RK on 8/30/2017.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import beyonity.water_vendor.Notification;
import beyonity.water_vendor.R;

public class Notification_Adapter extends RecyclerView.Adapter<Notification_Adapter.MyViewHolder>{

    private List<Notification> notificationList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView cust_name, cust_phone, time, day, year, can_count;

        public MyViewHolder(View view) {
            super(view);

            time = (TextView) view.findViewById(R.id.time);
            day = (TextView) view.findViewById(R.id.day);
            year = (TextView) view.findViewById(R.id.year);
            cust_name = (TextView) view.findViewById(R.id.cust_name);
            cust_phone = (TextView) view.findViewById(R.id.cust_phone);
            can_count = (TextView) view.findViewById(R.id.can_count);
        }
    }


    public Notification_Adapter(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notification_list, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Notification movie = notificationList.get(position);
        holder.time.setText(movie.getTime());
        holder.day.setText(movie.getDay());
        holder.year.setText(movie.getYear());
        holder.cust_name.setText(movie.getCust_name());
        holder.cust_phone.setText(movie.getCust_phone());
        holder.can_count.setText(movie.getCan_count());
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}

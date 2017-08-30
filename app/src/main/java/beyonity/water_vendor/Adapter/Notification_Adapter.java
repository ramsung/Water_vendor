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
        public TextView cust_name, cust_phone, time, day, year, can_count, cust_address;

        public MyViewHolder(View view) {
            super(view);

            time = (TextView) view.findViewById(R.id.time);
            day = (TextView) view.findViewById(R.id.day);
            year = (TextView) view.findViewById(R.id.year);
            cust_name = (TextView) view.findViewById(R.id.cust_name);
            cust_phone = (TextView) view.findViewById(R.id.cust_phone);
            can_count = (TextView) view.findViewById(R.id.can_count);
            cust_address = (TextView) view.findViewById(R.id.cust_address);
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
        Notification notif = notificationList.get(position);
        holder.time.setText(notif.getTime());
        holder.day.setText(notif.getDay());
        holder.year.setText(notif.getYear());
        holder.cust_name.setText(notif.getCust_name());
        holder.cust_phone.setText(notif.getCust_phone());
        holder.can_count.setText(notif.getCan_count());
        holder.cust_address.setText(notif.getCust_address());
    }

    @Override
    public int getItemCount() {
        return notificationList.size();
    }
}

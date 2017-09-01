package beyonity.water_vendor.Adapter;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

import beyonity.water_vendor.model.Sales;

/**
 * Created by RK on 9/1/2017.
 */

public class TodaysSales extends ArrayAdapter {
    private final List<Sales> todaySales;
    public TodaysSales(List<Sales> sales, Context context, int resource) {
        super(context, resource);
        this.todaySales = sales;
    }

    /*@Override
    public int getCount() {
        return Sales.size();
    }*/

    @Override
    public Object getItem(int position) {
        Sales sales = todaySales.get(position);
        return sales.getType() + " - " + sales.getAmount();
    }

    @Override
    public long getItemId(int position) {
        return todaySales.get(position).getId();
    }
}

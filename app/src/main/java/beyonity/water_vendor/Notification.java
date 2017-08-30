package beyonity.water_vendor;

/**
 * Created by RK on 8/30/2017.
 */

public class Notification {
    private String cust_name, cust_phone, year, time, day, can_count;

    public Notification() {
    }

    public Notification(String cust_name, String cust_phone, String year, String day, String can_count, String time) {

        this.time = time;
        this.day = day;
        this.year = year;
        this.cust_name = cust_name;
        this.cust_phone = cust_phone;
        this.can_count = can_count;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String year) {
        this.time = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String year) {
        this.day = year;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    public String getCust_name() {
        return cust_name;
    }

    public void setCust_name(String cust_name) {
        this.cust_name = cust_name;
    }

    public String getCust_phone() {
        return cust_phone;
    }

    public void setCust_phone(String cust_phone) {
        this.cust_phone = cust_phone;
    }

    public String getCan_count() {
        return can_count;
    }

    public void setCan_count(String can_count) {
        this.can_count = can_count;
    }
}
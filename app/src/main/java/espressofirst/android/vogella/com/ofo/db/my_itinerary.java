package espressofirst.android.vogella.com.ofo.db;

import org.litepal.crud.LitePalSupport;

/**
 * Created by 苏爷 on 2019/4/15.
 */

public class my_itinerary extends LitePalSupport {
    private String phone_number;
    private String bicycle_id;
    private float duration;
    private String start_time;
    private float fee;
    public String getPhone_number(){
        return phone_number;
    }
    public void setPhone_number(String phone_number){
        this.phone_number = phone_number;
    }
    public String getBicycle_id(){
        return bicycle_id;
    }
    public void setBicycle_id(String bicycle_id){
        this.bicycle_id = bicycle_id;
    }
    public float getDuration(){
        return duration;
    }
    public void setDuration(float duration){
        this.duration = duration;
    }
    public String getStart_time(){
        return start_time;
    }
    public void setStart_time(String start_time){
        this.start_time = start_time;
    }
    public float getFee(){
        return fee;
    }
    public void setFee(float fee){
        this.fee = fee;
    }
}

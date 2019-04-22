package espressofirst.android.vogella.com.ofo.db;

import org.litepal.crud.LitePalSupport;

/**
 * Created by 苏爷 on 2019/4/15.
 */

public class coupon extends LitePalSupport {
    private String coupon_id;
    private String phone_number;
    private int amount;
    public String getCoupon_id(){
        return coupon_id;
    }
    public void setCoupon_id(String coupon_id){
        this.coupon_id = coupon_id;
    }
    public String getPhone_number(){
        return phone_number;
    }
    public void setPhone_number(String phone_number){
        this.phone_number = phone_number;
    }
    public int getAmount(){
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }
}

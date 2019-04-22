package espressofirst.android.vogella.com.ofo.db;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

/**
 * Created by 苏爷 on 2019/4/15.
 */

public class feedback_record extends LitePalSupport {
    private String phone_number;
    private Date date1;
    private String type;
    public String getPhone_number(){
        return phone_number;
    }
    public void setPhone_number(String phone_number){
        this.phone_number = phone_number;
    }
    public Date getDate1(){
        return date1;
    }
    public void setDate1(Date date1){
        this.date1 = date1;
    }
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }
}

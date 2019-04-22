package espressofirst.android.vogella.com.ofo.db;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

/**
 * Created by 苏爷 on 2019/4/10.
 */

public class user extends LitePalSupport {
    private String phone_number;
    private String name;
    private byte[] avatar;
    private String sex;
    private Date birthday;
    public String getPhone_number(){
        return phone_number;
    }
    public void setPhone_number(String phone_number){
        this.phone_number = phone_number;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public byte[] getAvatar(){
        return avatar;
    }
    public void setAvatar(byte[] avatar){
        this.avatar = avatar;
    }
    public String getSex(){
        return sex;
    }
    public void setSex(String sex){
        this.sex = sex;
    }
    public Date getBirthday(){
        return birthday;
    }
    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
}

package espressofirst.android.vogella.com.ofo.db;

import org.litepal.crud.LitePalSupport;

/**
 * Created by 苏爷 on 2019/4/15.
 */

public class my_purse extends LitePalSupport {
    private String phone_number;
    private int card;
    private float money;
    private int deposit;
    public String getPhone_number(){
        return phone_number;
    }
    public void setPhone_number(String phone_number){
        this.phone_number =phone_number;
    }
    public int getCard(){
        return card;
    }
    public void setCard(int card){
        this.card = card;
    }
    public float getMoney(){
        return  money;
    }
    public void setMoney(float money){
        this.money = money;
    }
    public int getDeposit(){
        return deposit;
    }
    public void setDeposit(int deposit){
        this.deposit = deposit;
    }
}

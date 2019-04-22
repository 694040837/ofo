package espressofirst.android.vogella.com.ofo.db;

import org.litepal.crud.LitePalSupport;

/**
 * Created by 苏爷 on 2019/4/15.
 */

public class bicycle extends LitePalSupport {
    private String bicycle_id;
    private String unlock_code;
    private String longitude;
    private String dimension;
    public String getBicycle_id(){
        return bicycle_id;
    }
    public void setBicycle_id(String bicycle_id){
        this.bicycle_id = bicycle_id;
    }
    public String getUnlock_code(){
        return unlock_code;
    }
    public void setUnlock_code(String unlock_code){
        this.unlock_code = unlock_code;
    }
    public String getLongitude(){
        return longitude;
    }
    public void setLongitude(String longitude){
        this.longitude = longitude;
    }
    public String getDimension(){
        return dimension;
    }
    public void setDimension(String dimension){
        this.dimension = dimension;
    }
}

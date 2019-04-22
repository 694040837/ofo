package espressofirst.android.vogella.com.ofo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import espressofirst.android.vogella.com.ofo.db.user;

/**
 * Created by 苏爷 on 2019/4/15.
 * 注册界面
 */

public class Login2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        Bundle extras = getIntent().getExtras();
        String userName = extras.getString("userName");
        Toast.makeText(this, "尊敬的用户"+userName+",您的账号已验证成功！！！！！！！！！！！！！！！", Toast.LENGTH_SHORT).show();
        user User = new user();
        User.setPhone_number(userName);
        //drawableTools tools = new drawableTools();
        //User.setAvatar(tools.drawableToByte());
        User.save();
        //Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
    }
}

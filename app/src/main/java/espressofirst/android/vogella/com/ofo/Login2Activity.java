package espressofirst.android.vogella.com.ofo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Login2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        Bundle extras = getIntent().getExtras();
        String userName = extras.getString("userName");
        Toast.makeText(this, "尊敬的用户"+userName+",您的账号已验证成功！！！！！！！！！！！！！！！", Toast.LENGTH_SHORT).show();
    }
}

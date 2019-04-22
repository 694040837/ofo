package espressofirst.android.vogella.com.ofo;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mob.MobSDK;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import espressofirst.android.vogella.com.ofo.db.user;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    String APPKEY = "2abe56efce7a0";
    String APPSECRET = "e28f48191bf0cb7ab83975e31afc9af8";//Mob开发平台提供的SDK的使用key和密码
    // 手机号输入框
    private EditText inputPhoneEt;
    // 验证码输入框
    private EditText inputCodeEt;

    // 注册或登录按钮
    private Button commitBtn;

    //倒计时显示时间   可以手动更改。
    int i = 60;

    public static final String TAG = LoginActivity.class.getName();
    private ImageView iv_showCode;  //显示图片随机验证码
    private EditText et_phoneCode; //输入图片中的验证码
    //private EditText et_phoneNum;
    private Button  but_toSetCode; //获取短信验证码按钮
    //产生的图片中的验证码
    private String realCode;      //生成的图片中的随机验证码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    /**
     * 初始化控件
     */
    private void initView() {
        inputPhoneEt = (EditText) findViewById(R.id.editText);
        inputCodeEt = (EditText) findViewById(R.id.editText3);
        //requestCodeBtn = (Button) findViewById(R.id.button3);
        commitBtn = (Button) findViewById(R.id.button2);
        //requestCodeBtn.setOnClickListener(this);
        commitBtn.setOnClickListener(this);

        // 启动短信验证sdk
        MobSDK.init(this, APPKEY, APPSECRET);
        et_phoneCode = (EditText) findViewById(R.id.editText2);
        but_toSetCode = (Button) findViewById(R.id.button);
        but_toSetCode.setOnClickListener(this);
        iv_showCode = (ImageView) findViewById(R.id.imageView2);
        //将验证码用图片的形式显示出来
        iv_showCode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
        iv_showCode.setOnClickListener(this);

        EventHandler eventHandler = new EventHandler(){
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                handler.sendMessage(msg);
            }
        };
        //注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }


    @Override
    public void onClick(View v) {
        String phoneNums = inputPhoneEt.getText().toString();  //取出咱们输入的手机号
        switch (v.getId()) {
            case R.id.imageView2:
                iv_showCode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                Log.v(TAG,"realCode"+realCode);
                break;
            case R.id.button:
                String phoneCode = et_phoneCode.getText().toString().toLowerCase();
                //String msg = "生成的验证码："+realCode+"输入的验证码:"+phoneCode;
                //Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_LONG).show();

                if (phoneCode.equals(realCode)) {
                    //Toast.makeText(LoginActivity.this, phoneCode + "验证码正确", Toast.LENGTH_SHORT).show();

                    // 1. 判断手机号是不是11位并且看格式是否合理
                    if (!judgePhoneNums(phoneNums)) {
                        return;
                    }
                    // 2. 通过sdk发送短信验证
                    SMSSDK.getVerificationCode("86", phoneNums);
                    // 3. 把按钮变成不可点击，并且显示倒计时（正在获取）
                    but_toSetCode.setClickable(false);
                    but_toSetCode.setText("重新发送(" + i + ")");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (; i > 0; i--) {
                                handler.sendEmptyMessage(-9);
                                if (i <= 0) {
                                    break;
                                }
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            handler.sendEmptyMessage(-8);
                        }
                    }).start();
                } else {
                    Toast.makeText(LoginActivity.this,  "验证码错误", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.button2:
                //将收到的验证码和手机号提交再次核对
                SMSSDK.submitVerificationCode("86", phoneNums, inputCodeEt
                        .getText().toString());
                break;
        }

    }
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == -9) {
                but_toSetCode.setText("重新发送(" + i + ")");
            } else if (msg.what == -8) {
                but_toSetCode.setText("获取验证码");
                but_toSetCode.setClickable(true);
                i = 60;
            } else {
                int event = msg.arg1;
                int result = msg.arg2;
                Object data = msg.obj;
                Log.e("event", "event=" + event);
                if (result == SMSSDK.RESULT_COMPLETE) {
                    // 短信注册成功后，返回LoginActivity,然后提示
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功
                        Toast.makeText(getApplicationContext(), "提交验证码成功",
                                Toast.LENGTH_SHORT).show();
                        SQLiteDatabase db = Connector.getDatabase();
                        String phonenum = inputPhoneEt.getText().toString().trim();
                        //Log.d("LoginActivity",phonenum);
                        int flag = 0;
                        List<user> users = LitePal.findAll(user.class);
                        for(user User:users){
                            if(User.getPhone_number().equals(phonenum)){
                                app App = (app) getApplication();
                                App.setLoginUser(User);
                                flag = 1;
                            }
                        }
                        if(flag==1){             //已经注册过了
                            //跳转前清空活动栈

                            Intent intent = new Intent(LoginActivity.this,
                                    MapActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            /*Bundle bundle = new Bundle();
                            bundle.putString("userName",inputPhoneEt.getText().toString().trim());
                            intent.putExtras(bundle);*/
                            flag = 0;
                            startActivity(intent);
                        }
                        else {                //没注册呢，跳去注册
                            Intent intent = new Intent(LoginActivity.this,
                                    Login2Activity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("userName",inputPhoneEt.getText().toString().trim());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        Toast.makeText(getApplicationContext(), "正在获取验证码",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(LoginActivity.this,"验证码不正确",Toast.LENGTH_SHORT).show();
                        ((Throwable) data).printStackTrace();
                    }
                }
            }
        }
    };

    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        if (isMatchLength(phoneNums, 11)
                && isMobileNO(phoneNums)) {
            return true;
        }
        Toast.makeText(this, "手机号码输入有误！",Toast.LENGTH_SHORT).show();
        return false;
    }

    /**
     * 判断一个字符串的位数
     * @param str
     * @param length
     * @return
     */
    public static boolean isMatchLength(String str, int length) {
        if (str.isEmpty()) {
            return false;
        } else {
            return str.length() == length ? true : false;
        }
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobileNO(String mobileNums) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobileNums))
            return false;
        else
            return mobileNums.matches(telRegex);
    }

    @Override
    protected void onDestroy() {
        //反注册回调监听接口
        SMSSDK.unregisterAllEventHandler();
        super.onDestroy();
    }

}

package espressofirst.android.vogella.com.ofo;

import android.app.Application;

import org.litepal.LitePal;

import espressofirst.android.vogella.com.ofo.db.user;

/**
 * Created by 苏爷 on 2019/4/10.
 * 用来获取全局变量Login_user
 */

public class app extends Application {
    // 已登录的用户信息
    private user loginUser;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }

    public user getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(user loginUser) {
        this.loginUser = loginUser;
    }

    public boolean isLogin() {
        return loginUser != null;
    }
}

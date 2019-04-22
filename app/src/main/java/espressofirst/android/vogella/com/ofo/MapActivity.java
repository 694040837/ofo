package espressofirst.android.vogella.com.ofo;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

public class MapActivity extends AppCompatActivity implements View.OnClickListener{

    private MapView mapView;
    FloatingActionButton begin;
    FloatingActionButton refresh;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_map);
        begin = (FloatingActionButton)findViewById(R.id.begin);
        refresh = (FloatingActionButton)findViewById(R.id.refresh);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        begin.setOnClickListener(this);
        refresh.setOnClickListener(this);
        fab.setOnClickListener(this);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(""); //隐藏标题栏上的标题
        mapView = (MapView) findViewById(R.id.bmapView);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            // 让导航栏入口按钮显示出来
            actionBar.setDisplayHomeAsUpEnabled(true);
            // 为导航栏入口按钮设置一个图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        //Bundle extras = getIntent().getExtras();
        //String userName = extras.getString("userName");
    }

    // 重写「菜单创建时的回调方法」
    public boolean onCreateOptionsMenu(Menu menu){
        // 通过布局文件tool_action.xml创建Menu对象
        getMenuInflater().inflate(R.menu.toolbar_action, menu);
        return true; // 返回true表示允许创建的Menu对象显示
    }

    // 重写「菜单响应事件」
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){  // 获取被选中项的id
            case R.id.notification:
                // 跳出toast提示
                Toast.makeText(this, "我的消息", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home: // 导航栏入口按钮的响应事件
                //
                Toast.makeText(this, "个人中心", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.begin:{
                //TODO 开始扫描按钮点击事件
                Toast.makeText(this, "扫码用车", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.refresh:{
                //TODO 刷新定位按钮点击事件
                Toast.makeText(this, "刷新定位", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.fab:{
                //举报按钮点击事件
                Toast.makeText(this, "我的客服", Toast.LENGTH_SHORT).show();
                break;
            }
            default:
                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mapView.onPause();
    }

}

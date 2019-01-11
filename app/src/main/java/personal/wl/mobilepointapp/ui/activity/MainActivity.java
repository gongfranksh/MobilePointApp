package personal.wl.mobilepointapp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.LocationService;
import personal.wl.mobilepointapp.model.User;
import personal.wl.mobilepointapp.ui.fragment.AppsFragment;
import personal.wl.mobilepointapp.ui.fragment.AroundFragment;
import personal.wl.mobilepointapp.ui.fragment.HomeFragment;
import personal.wl.mobilepointapp.ui.fragment.MeFragment;
import personal.wl.mobilepointapp.ui.fragment.MoreFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentTabHost tabhost;

    private Class[] fragments = new Class[]{
            HomeFragment.class,AppsFragment.class ,
             AroundFragment.class,MeFragment.class,MoreFragment.class};
    private int[] resTitles = new int[]{
            R.string.tab_title_home, R.string.tab_title_application,
            R.string.tab_title_around,R.string.tab_title_me, R.string.tab_title_more};
    private int[] icons = new int[]{
            R.drawable.tab_home_selector, R.drawable.tab_application_selector,
             R.drawable.tab_around_selector,R.drawable.tab_me_selector,R.drawable.tab_more_selector};

    private Intent local;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(local);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setViewWithIntentData();
    }

    private void setViewWithIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            User user = (User) intent.getSerializableExtra(RegisterActivity.INTENT_USER);
            if (user != null) {
                tabhost.setCurrentTab(2);
            }
        }
    }

    private void initViews() {
        tabhost = (FragmentTabHost) findViewById(R.id.main_tabHost);
        tabhost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
        for (int i = 0; i < fragments.length; i++) {
            View view = getLayoutInflater().inflate(R.layout.item_tab, null);
            ImageView tabIcon = (ImageView) view.findViewById(R.id.item_tab_iv);
            TextView tabTitle = (TextView) view.findViewById(R.id.item_tab_tv);
            tabIcon.setImageResource(icons[i]);
            tabTitle.setText(resTitles[i]);
            tabhost.addTab(tabhost.newTabSpec("" + i).setIndicator(view), fragments[i], null);
            checkpermission();
            local = new Intent(this, LocationService.class);
            startService(local);
        }
    }

    private void checkpermission() {

        String[] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.INTERNET};
        int mRequestCode = 100;
        List<String> mPermissionList = new ArrayList<>();
        mPermissionList.clear();//清空已经允许的没有通过的权限
        //逐个判断是否还有未通过的权限
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) !=
                    PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限到mPermissionList中
            }
        }
        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, permissions, mRequestCode);
        }


    }

}

package personal.wl.mobilepointapp.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.common.LocationService;
import personal.wl.mobilepointapp.ui.base.BaseActivity;
import personal.wl.mobilepointapp.ui.fragment.CityFragment;


/**
 * Created by asus on 2016/9/29.
 */

public class CityActivity extends BaseActivity {

    public static final int CITY_RESULT_CODE = 4001;

    private ImageView mTitleBarIvBack;
    private TextView mTitleBarTvTitle;
    private TextView mTitleBarTvRight;
    private FrameLayout mContentLayout;
    private TextView mErrorTv;
    private LinearLayout mErrorLayout;
//    private Intent local;

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        stopService(local);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_info_activity);
        initView();

        getSupportFragmentManager().beginTransaction().replace(R.id.common_content_layout,new CityFragment()).commit();
    }

    private void initView() {
        mTitleBarIvBack = (ImageView) findViewById(R.id.common_titleBar_iv_back);
        mTitleBarTvTitle = (TextView) findViewById(R.id.common_titleBar_tv_title);
        mTitleBarTvRight = (TextView) findViewById(R.id.common_titleBar_tv_right);
        mContentLayout = (FrameLayout) findViewById(R.id.common_content_layout);
        mErrorTv = (TextView) findViewById(R.id.common_error_tv);
        mErrorLayout = (LinearLayout) findViewById(R.id.common_error_layout);

        Intent intent = getIntent();
        String cityname = intent.getStringExtra(AppConstant.KEY_CITY);

        mTitleBarTvTitle.setText(getString(R.string.current_city)+"-"+cityname);
        mTitleBarIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mErrorLayout.setVisibility(View.GONE);


//
//        checkpermission();
//        local= new Intent(this,LocationService.class);
//        startService(local);

    }

    //--add by weiliang-gong


    public void setTitle(String cityName) {
        mTitleBarTvTitle.setText(getString(R.string.current_city)+"-"+cityName);
    }
}

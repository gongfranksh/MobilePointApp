package personal.wl.mobilepointapp.ui.activity.SalesOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.io.Serializable;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.preference.SystemSettingConstant;
import personal.wl.mobilepointapp.ui.base.BaseActivity;
import personal.wl.mobilepointapp.ui.fragment.Sales.SaleOrderFragment;
import personal.wl.mobilepointapp.ui.fragment.Sales.SkuSelectFragment;
import personal.wl.mobilepointapp.utils.ToastUtil;

import static personal.wl.mobilepointapp.common.AppConstant.SKU_SELECT_RESULT_CODE;


public class SkuSelectActivity extends BaseActivity  {

    private ImageView mTitleBarIvBack;
    private TextView mTitleBarTvTitle;
    private TextView mTitleBarTvRight;
    private FrameLayout mContentLayout;
    private TextView mErrorTv;
    private LinearLayout mErrorLayout;
    private int skuselectfragment_id ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_info_activity);
        initView();
        skuselectfragment_id = getSupportFragmentManager().beginTransaction().replace(R.id.common_content_layout, new SkuSelectFragment()).commit();
    }

    public void initView() {
        mTitleBarIvBack = (ImageView) findViewById(R.id.common_titleBar_iv_back);
        mTitleBarTvTitle = (TextView) findViewById(R.id.common_titleBar_tv_title);
        mTitleBarTvRight = (TextView) findViewById(R.id.common_titleBar_tv_right);
        mContentLayout = (FrameLayout) findViewById(R.id.common_content_layout);
        mErrorTv = (TextView) findViewById(R.id.common_error_tv);
        mErrorLayout = (LinearLayout) findViewById(R.id.common_error_layout);

        mTitleBarTvRight.setVisibility(View.GONE);
        mTitleBarTvTitle.setText("商品清单");
        mTitleBarIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


}

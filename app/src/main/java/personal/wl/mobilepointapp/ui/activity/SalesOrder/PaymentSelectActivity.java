package personal.wl.mobilepointapp.ui.activity.SalesOrder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.ui.base.BaseActivity;
import personal.wl.mobilepointapp.ui.fragment.Sales.PaymentSelectFragment;
import personal.wl.mobilepointapp.ui.fragment.Sales.SkuSelectFragment;


public class PaymentSelectActivity extends BaseActivity  {

    private ImageView mTitleBarIvBack;
    private TextView mTitleBarTvTitle;
    private TextView mTitleBarTvRight;
    private FrameLayout mContentLayout;
    private TextView mErrorTv;
    private LinearLayout mErrorLayout;
    private int payselectfragment_id ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_info_activity);
        initView();
        payselectfragment_id = getSupportFragmentManager().beginTransaction().replace(R.id.common_content_layout, new PaymentSelectFragment()).commit();
    }

    public void initView() {
        mTitleBarIvBack = (ImageView) findViewById(R.id.common_titleBar_iv_back);
        mTitleBarTvTitle = (TextView) findViewById(R.id.common_titleBar_tv_title);
        mTitleBarTvRight = (TextView) findViewById(R.id.common_titleBar_tv_right);
        mContentLayout = (FrameLayout) findViewById(R.id.common_content_layout);
        mErrorTv = (TextView) findViewById(R.id.common_error_tv);
        mErrorLayout = (LinearLayout) findViewById(R.id.common_error_layout);

        mTitleBarTvRight.setVisibility(View.GONE);
        mTitleBarTvTitle.setText("收款");
        mTitleBarIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }


}

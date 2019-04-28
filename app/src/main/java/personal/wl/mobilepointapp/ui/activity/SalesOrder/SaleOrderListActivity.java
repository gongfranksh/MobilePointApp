package personal.wl.mobilepointapp.ui.activity.SalesOrder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.ui.base.BaseActivity;
import personal.wl.mobilepointapp.ui.fragment.Sales.SaleOrderFragment;
import personal.wl.mobilepointapp.ui.fragment.Sales.SaleOrderListFragment;


public class SaleOrderListActivity extends BaseActivity {
    private ImageView mTitleBarIvBack;
    private TextView mTitleBarTvTitle;
    private TextView mTitleBarTvRight;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_common_info_activity);
        initView();
        getSupportFragmentManager().beginTransaction().replace(R.id.common_content_layout, new SaleOrderListFragment()).commit();
    }

    public void initView() {
        mTitleBarIvBack = (ImageView) findViewById(R.id.common_titleBar_iv_back);
        mTitleBarTvTitle = (TextView) findViewById(R.id.common_titleBar_tv_title);
        mTitleBarTvRight = (TextView) findViewById(R.id.common_titleBar_tv_right);
        mTitleBarTvRight.setVisibility(View.GONE);
        mTitleBarTvTitle.setText("销售清单");
        mTitleBarIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}

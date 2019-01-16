package personal.wl.mobilepointapp.ui.activity.SalesOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.preference.SystemSettingConstant;
import personal.wl.mobilepointapp.ui.base.BaseActivity;
import personal.wl.mobilepointapp.utils.ToastUtil;


public class SaleOrderActivity extends BaseActivity implements View.OnClickListener {

    private ImageView skuscan;
    private ImageView memscan;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SystemSettingConstant.SCAN_QR_REQUEST) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    ToastUtil.show(this, "解析结果:" + result);
                }
            }
        }


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salesorder);
        initView();
    }

    public void initView() {
        memscan = findViewById(R.id.sales_order_img_memeber);
        skuscan = findViewById(R.id.sales_order_img_sku);

        skuscan.setOnClickListener(this);
        memscan.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sales_order_img_sku:
                ToastUtil.show(this, "san sku");
//                choseHeadImageFromCameraCapture();
                Intent intent = new Intent(this, SkuSelectActivity.class);
                startActivityForResult(intent, SystemSettingConstant.SKU_SELECTED_REQUEST);
                break;
            case R.id.sales_order_img_memeber:
                ToastUtil.show(this, "san member");
                break;
        }


    }
}

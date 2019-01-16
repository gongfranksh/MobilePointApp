package personal.wl.mobilepointapp.ui.activity.SalesOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.preference.SystemSettingConstant;
import personal.wl.mobilepointapp.ui.base.BaseActivity;
import personal.wl.mobilepointapp.utils.ToastUtil;


public class SkuSelectActivity extends BaseActivity implements View.OnClickListener {

    private ImageView skuscan;
    private TextView skuvalue;

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
                    skuvalue.setText(result);
                }
            }
        }


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sku_select);
        initView();
    }

    public void initView() {
        skuscan = findViewById(R.id.sku_titleBar_scan_img);
        skuvalue= findViewById(R.id.sku_text_input_barcode);
//
        skuscan.setOnClickListener(this);
//        memscan.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sku_titleBar_scan_img:
                ToastUtil.show(this, "san sku");
                Intent intent = new Intent(this, CaptureActivity.class);
                startActivityForResult(intent, SystemSettingConstant.SCAN_QR_REQUEST);
                break;
        }


    }
}

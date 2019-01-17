package personal.wl.mobilepointapp.ui.fragment.Sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.preference.SystemSettingConstant;
import personal.wl.mobilepointapp.ui.adapter.MPASkuListAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.ToastUtil;

import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CLICK_ITEM_VIEW;


public class SkuSelectFragment extends BaseFragment implements View.OnClickListener {

    private ImageView skuscan;
    private TextView skuvalue;

    private RecyclerView mskuRecyclerView;

    private MPASkuListAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sku_select, null);
        initView(view);
        initAdapter();
        return view;
    }

    private void initAdapter() {
        List<Product> data = new ArrayList<>();
        Product p = new Product();

        for (int i = 0; i <10 ; i++) {
        p.setTYPE(CLICK_ITEM_VIEW);
        p.setProName("hello"+i);
        p.setNormalPrice((long) 11.11+i);
        data.add(p);

        }
        adapter = new MPASkuListAdapter(data);
        mskuRecyclerView.setAdapter(adapter);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SystemSettingConstant.SCAN_QR_REQUEST) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    ToastUtil.show(getActivity(), "解析结果:" + result);
                    skuvalue.setText(result);
                }
            }
        }
    }

    public void initView(View view) {
        skuscan = view.findViewById(R.id.sku_titleBar_scan_img);
        skuvalue = view.findViewById(R.id.sku_text_input_barcode);
        mskuRecyclerView = view.findViewById(R.id.skulists);
        mskuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        skuscan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sku_titleBar_scan_img:
                ToastUtil.show(getActivity(), "san sku");
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, SystemSettingConstant.SCAN_QR_REQUEST);
                break;
            case R.id.sku_good_bt1:
                ToastUtil.show(getActivity(), "sku_good_bt1 sku");
                break;

        }
    }
}

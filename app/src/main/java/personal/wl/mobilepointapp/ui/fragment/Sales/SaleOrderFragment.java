package personal.wl.mobilepointapp.ui.fragment.Sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.preference.SystemSettingConstant;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.SkuSelectActivity;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.ToastUtil;


public class SaleOrderFragment extends BaseFragment implements View.OnClickListener {

    private ImageView skuscan;
    private ImageView memscan;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_salesorder, null);
        skuscan = view.findViewById(R.id.sales_order_img_sku);
        memscan = view.findViewById(R.id.sales_order_img_memeber);
        skuscan.setOnClickListener(this);
        memscan.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sales_order_img_sku:
                ToastUtil.show(getActivity(), "san sku");
                Intent intent = new Intent(getActivity(), SkuSelectActivity.class);
                startActivityForResult(intent, SystemSettingConstant.SKU_SELECTED_REQUEST);
                break;
            case R.id.sales_order_img_memeber:
                ToastUtil.show (getActivity(), "san member");
                break;
        }


    }
}

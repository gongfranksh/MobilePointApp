package personal.wl.mobilepointapp.ui.fragment.Sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.entity.pos.SaleDaily;
import personal.wl.mobilepointapp.preference.SystemSettingConstant;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.SkuSelectActivity;
import personal.wl.mobilepointapp.ui.adapter.MPASaleOrderListAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.ToastUtil;

import static personal.wl.mobilepointapp.common.AppConstant.SKU_SELECT_RESULT_CODE;
import static personal.wl.mobilepointapp.common.AppConstant.SKU_SELECT_RESULT_EXTRA_CODE;


public class SaleOrderFragment extends BaseFragment implements View.OnClickListener {

    private ImageView skuscan;
    private ImageView memscan;
    private RecyclerView productorderlistecyclerView;

    private List<SaleDaily> saleDailyList = new ArrayList<>();
    private SaleDaily onesales;
    private MPASaleOrderListAdapter mpaSaleOrderListAdapter;
    private List<Product> NeedProduct = new ArrayList<>();


    private TextView sales_total_amount;
    private TextView sales_detail_layout_buy_total_amount;
    private TextView sales_detail_layout_buy_total_qty;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SKU_SELECT_RESULT_CODE) {
            if (null != data) {
                NeedProduct = (List<Product>) data.getSerializableExtra(SKU_SELECT_RESULT_EXTRA_CODE);

                for (int i = 0; i < NeedProduct.size(); i++) {
                    onesales = new SaleDaily();
                    onesales = onesales.getSaleDailyFromProduct(NeedProduct.get(i));
                    saleDailyList.add(onesales);
                }
                mpaSaleOrderListAdapter.notifyDataSetChanged();
                ReflashValue();
            }
        }


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_salesorder, null);
        skuscan = view.findViewById(R.id.sales_order_img_sku);
        memscan = view.findViewById(R.id.sales_order_img_memeber);


        sales_detail_layout_buy_total_amount = view.findViewById(R.id.detail_layout_buy_amount);
        sales_detail_layout_buy_total_qty = view.findViewById(R.id.detail_layout_buy_qty);
        sales_total_amount = view.findViewById(R.id.sales_order_Total_Amount);

        productorderlistecyclerView = view.findViewById(R.id.sale_order_product_lists);
        productorderlistecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mpaSaleOrderListAdapter = new MPASaleOrderListAdapter(R.layout.item_databing_saleorder_list, saleDailyList);
        mpaSaleOrderListAdapter.openLoadAnimation();
        productorderlistecyclerView.setAdapter(mpaSaleOrderListAdapter);

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
                ToastUtil.show(getActivity(), "san member");
                break;
        }


    }


    public void ReflashValue() {

        double tmp_qty = 0.00, tmp_total_amount = 0.00;
        for (int i = 0; i < saleDailyList.size(); i++) {
            tmp_qty += saleDailyList.get(i).getSaleQty();
            tmp_total_amount += saleDailyList.get(i).getSaleAmt();
        }
        sales_total_amount.setText("" + tmp_total_amount);
        sales_detail_layout_buy_total_amount.setText("" + tmp_total_amount);
        sales_detail_layout_buy_total_qty.setText("" + tmp_qty);


    }
}

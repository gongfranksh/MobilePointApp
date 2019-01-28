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

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.common.MobilePointApplication;
import personal.wl.mobilepointapp.entity.pos.BranchEmployee;
import personal.wl.mobilepointapp.entity.pos.PayMent;
import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.entity.pos.SaleDaily;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.BranchEmployeeSelectActivity;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.PaymentSelectActivity;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.SkuSelectActivity;
import personal.wl.mobilepointapp.ui.adapter.MPASaleOrderListAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.ToastUtil;

import static personal.wl.mobilepointapp.common.AppConstant.PAYMMENT_NEED_PAY_CODE;
import static personal.wl.mobilepointapp.common.AppConstant.SKU_SELECT_RESULT_EXTRA_CODE;


public class SaleOrderFragment extends BaseFragment implements View.OnClickListener {

    private static List<SaleDaily> ShouldPay = new ArrayList<>();
    private ImageView skuscan;
    private ImageView memscan;
    private ImageView payment_img;
    private ImageView operator_img;

    private RecyclerView productorderlistecyclerView;
    private Intent intent;


    private List<SaleDaily> saleDailyList = new ArrayList<>();
    private SaleDaily onesales;
    private MPASaleOrderListAdapter mpaSaleOrderListAdapter;
    private List<Product> NeedProduct = new ArrayList<>();
    private List<PayMent> AlreadyPaylist = new ArrayList<>();


    private BranchEmployee curr_operator;


    private TextView sales_total_amount;
    private TextView sales_total_payment;
    private TextView sales_operator;

    private TextView sales_detail_layout_buy_total_amount;
    private TextView sales_detail_layout_buy_total_qty;


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case AppConstant.SKU_SELECT_RESULT_CODE:
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
                break;

            case AppConstant.PAYMMENT_NEED_PAY_CODE:
                if (data != null) {
                    AlreadyPaylist = (List<PayMent>) data.getSerializableExtra(AppConstant.PAYMMENT_SELECT_RESULT_EXTRA_CODE);

                    double tmp_total_payment = 0.00;
                    for (int i = 0; i < AlreadyPaylist.size(); i++) {
                        tmp_total_payment += AlreadyPaylist.get(i).getPayMoney();
                    }

                    sales_total_payment.setText("" + tmp_total_payment);
                }
                break;
            case AppConstant.OPERATOR_NEED_CODE:
                if (data != null) {
                    curr_operator = (BranchEmployee) data.getSerializableExtra(AppConstant.OPERATOR_SELECT_RESULT_EXTRA_CODE);

                    sales_operator.setText(curr_operator.getEmpName());
                }
                break;



            default:
                break;
        }


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_salesorder, null);
        skuscan = view.findViewById(R.id.sales_order_img_sku);
        memscan = view.findViewById(R.id.sales_order_img_memeber);
        payment_img = view.findViewById(R.id.sales_order_img_payments);
        operator_img = view.findViewById(R.id.sales_order_img_operator);
        sales_operator=view.findViewById(R.id.sales_order_operator);


        sales_detail_layout_buy_total_amount = view.findViewById(R.id.detail_layout_buy_amount);
        sales_detail_layout_buy_total_qty = view.findViewById(R.id.detail_layout_buy_qty);
        sales_total_amount = view.findViewById(R.id.sales_order_Total_Amount);

        sales_total_payment = view.findViewById(R.id.sales_order_payments);

        productorderlistecyclerView = view.findViewById(R.id.sale_order_product_lists);
        productorderlistecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mpaSaleOrderListAdapter = new MPASaleOrderListAdapter(R.layout.item_databing_saleorder_list, saleDailyList);
        mpaSaleOrderListAdapter.openLoadAnimation();
        productorderlistecyclerView.setAdapter(mpaSaleOrderListAdapter);

        skuscan.setOnClickListener(this);
        memscan.setOnClickListener(this);
        payment_img.setOnClickListener(this);
        operator_img.setOnClickListener(this);

        getlasttransctaion();
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sales_order_img_sku:
                ToastUtil.show(getActivity(), "san sku");
                intent = new Intent(getActivity(), SkuSelectActivity.class);
                startActivityForResult(intent, AppConstant.SKU_SELECT_RESULT_CODE);
                break;

            case R.id.sales_order_img_payments:
                ToastUtil.show(getActivity(), "PayMent ");
                intent = new Intent(getActivity(), PaymentSelectActivity.class);
                startActivityForResult(intent, PAYMMENT_NEED_PAY_CODE);
                break;

            case R.id.sales_order_img_operator:
                ToastUtil.show(getActivity(), "Operator ");
                intent = new Intent(getActivity(), BranchEmployeeSelectActivity.class);
                startActivityForResult(intent, AppConstant.OPERATOR_NEED_CODE);

                break;
            case R.id.sales_order_img_memeber:
                ToastUtil.show(getActivity(), "san member");
                break;
        }


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void getlasttransctaion() {
        double tmp_shouldpay = 0.0;
        try {
            ShouldPay = MobilePointApplication.loginInfo.getCurrentTranscation();
            if (ShouldPay != null && ShouldPay.size() != 0) {
                for (int i = 0; i < ShouldPay.size(); i++) {
                    saleDailyList.add(ShouldPay.get(i));

                }
                mpaSaleOrderListAdapter.notifyDataSetChanged();
                ReflashValue();
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }


        try {
            AlreadyPaylist = MobilePointApplication.loginInfo.getCurrentPayment();

            if (AlreadyPaylist != null && AlreadyPaylist.size() != 0) {

                double tmp_alreadpay = 0.00;
                for (int i = 0; i < AlreadyPaylist.size(); i++) {

                    tmp_alreadpay += AlreadyPaylist.get(i).getPayMoney();
                }

                sales_total_payment.setText("" + tmp_alreadpay);
            }
        } catch (JSONException e) {
            e.printStackTrace();
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

package personal.wl.mobilepointapp.ui.fragment.Sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.common.MobilePointApplication;
import personal.wl.mobilepointapp.entity.pos.Branch;
import personal.wl.mobilepointapp.entity.pos.BranchEmployee;
import personal.wl.mobilepointapp.entity.pos.Member;
import personal.wl.mobilepointapp.entity.pos.PayMent;
import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.entity.pos.SaleDaily;
import personal.wl.mobilepointapp.preference.CurrentUser.MPALoginInfo;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.BranchEmployeeSelectActivity;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.MemberSelectActivity;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.PaymentSelectActivity;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.SkuSelectActivity;
import personal.wl.mobilepointapp.ui.adapter.MPASaleOrderListAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.ToastUtil;
import personal.wl.mobilepointapp.webservice.CallWebservices;
import personal.wl.mobilepointapp.webservice.WebServiceInterface;
import personal.wl.mobilepointapp.webservice.WebServicePara;

import static personal.wl.mobilepointapp.common.AppConstant.PAYMMENT_NEED_PAY_CODE;
import static personal.wl.mobilepointapp.common.AppConstant.SKU_SELECT_RESULT_EXTRA_CODE;


public class SaleOrderFragment extends BaseFragment implements WebServiceInterface,View.OnClickListener {

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


    private Branch current_branch;


    private BranchEmployee curr_operator;
    private Member member;


    private TextView sales_total_amount;
    private TextView sales_total_payment;
    private TextView sales_operator;
    private TextView sales_member;

    private TextView sales_branch;

    private TextView sales_detail_layout_buy_total_amount;
    private TextView sales_detail_layout_buy_total_qty;


    private Button sale_order_submit;


    private CallWebservices callWebservices;
    private WebServicePara parain;
    private List<WebServicePara> paraList = new ArrayList<>();


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
                    MPALoginInfo.getInstance().setCurrentTranscation(saleDailyList);
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
            case AppConstant.MEMBER_NEED_CODE:
                if (data != null) {
                    member = (Member) data.getSerializableExtra(AppConstant.MEMBER_SELECT_RESULT_EXTRA_CODE);
                    if (member != null) {
                        sales_member.setText(member.getCardid());
                        MPALoginInfo.getInstance().setCurrentMember(member);
                    }
                }

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
        sales_operator = view.findViewById(R.id.sales_order_operator);
        sales_member = view.findViewById(R.id.sales_order_member_value);
        sale_order_submit = view.findViewById(R.id.sale_order_submit_btn);

        sales_branch = view.findViewById(R.id.sales_order_branch_selected);

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
        sale_order_submit.setOnClickListener(this);

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
                intent = new Intent(getActivity(), MemberSelectActivity.class);
                startActivityForResult(intent, AppConstant.MEMBER_NEED_CODE);
                ToastUtil.show(getActivity(), "san member");
                break;

            case  R.id.sale_order_submit_btn:
                ToastUtil.show(getActivity(), "sale order submit");
                saleordersubmit();
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

        try {
            curr_operator = MobilePointApplication.loginInfo.getCurrentOperator();
            if (curr_operator != null) {
                sales_operator.setText(curr_operator.getEmpName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            current_branch = MobilePointApplication.loginInfo.getCurrentBranch();
            if (current_branch != null) {
                sales_branch.setText(current_branch.getBraname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            member = MobilePointApplication.loginInfo.getCurrentMember();
            if (member != null) {

                sales_member.setText(member.getCardid());
            }
        } catch (Exception e) {
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

    private void saleordersubmit(){
        Gson gson = new Gson();
        String str_curr_branch = gson.toJson(current_branch);
        JsonObject json_curr_branch = new JsonObject();


        String str_curr_operator = gson.toJson(curr_operator);
        String str_list_saledaily = gson.toJson(saleDailyList);
        String str_list_payment = gson.toJson(AlreadyPaylist);
        String str_member=gson.toJson(member);
        JsonObject sales = new JsonObject();
        sales.addProperty("branch",str_curr_branch);
        sales.addProperty("operator",str_curr_operator);
        sales.addProperty("saledaily",str_list_saledaily);
        sales.addProperty("payment",str_list_payment);
        sales.addProperty("member",str_member);
        MPALoginInfo.getInstance().setLastTranscation(sales.toString());

        parain = new WebServicePara();
        paraList = new ArrayList<>();
        callWebservices = null;

        parain.setPara_name(AppConstant.PARA_TRANSCATION);
        parain.setPara_value(sales.toString());
        paraList.add(parain);
        parain = new WebServicePara();

        callWebservices = new CallWebservices(this, AppConstant.Method_SUBMIT_POS_ORDER, paraList);
        callWebservices.execute();
    }

    @Override
    public void onRecevicedResult(JSONArray jsonArray) {
//        Log.i("saleorder", "onRecevicedResult: " + jsonArray.toString());


    }
}

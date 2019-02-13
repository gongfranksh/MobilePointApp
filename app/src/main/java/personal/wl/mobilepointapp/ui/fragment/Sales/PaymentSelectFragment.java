package personal.wl.mobilepointapp.ui.fragment.Sales;

import android.content.Intent;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.common.MobilePointApplication;
import personal.wl.mobilepointapp.entity.pos.PayMent;
import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.entity.pos.SaleDaily;
import personal.wl.mobilepointapp.entity.pos.SalePayMode;
import personal.wl.mobilepointapp.preference.CurrentUser.MPALoginInfo;
import personal.wl.mobilepointapp.preference.SystemSettingConstant;
import personal.wl.mobilepointapp.ui.adapter.MPAPaymentListAdapter;
import personal.wl.mobilepointapp.ui.adapter.MPASkuListAdapter;
import personal.wl.mobilepointapp.ui.adapter.PayMoneyChange;
import personal.wl.mobilepointapp.ui.adapter.PayMoneyWatcher;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.ToastUtil;
import personal.wl.mobilepointapp.webservice.CallWebservices;
import personal.wl.mobilepointapp.webservice.WebServiceInterface;
import personal.wl.mobilepointapp.webservice.WebServicePara;

import static personal.wl.mobilepointapp.common.AppConstant.Method_GET_BRANCH_PAYMENNT_;
import static personal.wl.mobilepointapp.common.AppConstant.Method_QUERY_PRODUCT_BY_BARCODE;
import static personal.wl.mobilepointapp.common.AppConstant.PARA_BARCODE;
import static personal.wl.mobilepointapp.common.AppConstant.PARA_BRANCHCODE;
import static personal.wl.mobilepointapp.common.AppConstant.SKU_SELECT_RESULT_CODE;
import static personal.wl.mobilepointapp.common.AppConstant.SKU_SELECT_RESULT_EXTRA_CODE;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.PAGE_SIZE;


public class PaymentSelectFragment extends BaseFragment implements View.OnClickListener,
        WebServiceInterface


{


    private static List<SaleDaily> ShouldPay = new ArrayList<>();
    private List<PayMent> NeedPayMent = new ArrayList<>();
    private List<PayMent> AlreadyPayMent = new ArrayList<>();
    private RecyclerView paymentRecyclerView;
    private int mNextRequestPage = 1;
    private MPAPaymentListAdapter adapter;
    private List<PayMent> payMentList = new ArrayList<>();
    private List<SalePayMode> payModeslist = new ArrayList<>();

    private WebServicePara parain;
    private List<WebServicePara> paraList = new ArrayList<>();


    private CallWebservices callWebservices;
    private TextView total_payment;
    private TextView should_payment;
    private TextView different_payment;
    private Button payment_submit;


    private Double tmp_should_pay = 0.0;
    private Double tmp_already_pay = 0.0;
    private Double tmp_different_pay = 0.0;


    private String TAG = "PaymentSelectFragment";

    private PayMoneyWatcher payMoneyWatcher = new PayMoneyWatcher() {
        @Override
        public void update(Observable o, Object arg) {
            super.update(o, arg);
            ReflushTotal();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_select, null);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ToastUtil.show(getActivity(), "sku=>fragment=>onresmue");

        getfunctions();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConstant.PAYMMENT_SELECT_RESULT_CODE) {
            NeedPayMent = (List<PayMent>) data.getSerializableExtra(AppConstant.PAYMMENT_SELECT_RESULT_EXTRA_CODE);

            for (int i = 0; i < NeedPayMent.size(); i++) {
                PayMent onepayment = new PayMent();
                onepayment.setPayMentName(NeedPayMent.get(i).getPayMentName());
                onepayment.setPayMoney(0.00);
                onepayment.setCadType(NeedPayMent.get(i).getCadType());
                onepayment.setPayMentId(NeedPayMent.get(i).getPayMentId());
                onepayment.setPayModeId(NeedPayMent.get(i).getPayModeId());
                payMentList.add(onepayment);
            }
            adapter.notifyDataSetChanged();
            getlasttransctaion();
        }
    }


    public void getfunctions() {

        parain = new WebServicePara();
        paraList = new ArrayList<>();
        callWebservices = null;

        parain.setPara_name(PARA_BRANCHCODE);
        parain.setPara_value("01002");
        paraList.add(parain);
        parain = new WebServicePara();

        callWebservices = new CallWebservices(this, Method_GET_BRANCH_PAYMENNT_, paraList);
        callWebservices.execute();
    }

    public void initView(View view) {

        paymentRecyclerView = view.findViewById(R.id.paymentlists);
        paymentRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MPAPaymentListAdapter(getActivity(), payMentList);
        total_payment = view.findViewById(R.id.detail_layout_payment_total);
        should_payment = view.findViewById(R.id.detail_layout_need_pay);
        payment_submit = view.findViewById(R.id.sku_detail_layout_submit_btn);

        different_payment=view.findViewById(R.id.detail_layout_different_pay);

        payment_submit.setOnClickListener(this);
        paymentRecyclerView.setAdapter(adapter);

//        getlasttransctaion();
    }


    private void getlasttransctaion() {
        try {
            ShouldPay = MobilePointApplication.loginInfo.getCurrentTranscation();
            if (ShouldPay != null && ShouldPay.size() != 0) {

                for (int i = 0; i < ShouldPay.size(); i++) {
                    tmp_should_pay += ShouldPay.get(i).getSaleAmt();
                }
            }
            should_payment.setText("" + tmp_should_pay);

            AlreadyPayMent = MobilePointApplication.loginInfo.getCurrentPayment();

            if (AlreadyPayMent != null && AlreadyPayMent.size() != 0) {
                payMentList.clear();
                payMentList.addAll(AlreadyPayMent);
                adapter.notifyDataSetChanged();
                ReflushTotal();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sku_detail_layout_submit_btn:
                if (tmp_should_pay >= tmp_already_pay) {
                    Intent callintent = new Intent();
                    MPALoginInfo.getInstance().setCurrentPayMent(payMentList);
                    callintent.putExtra(AppConstant.PAYMMENT_SELECT_RESULT_EXTRA_CODE, (Serializable) payMentList);
                    getActivity().setResult(AppConstant.PAYMMENT_NEED_PAY_CODE, callintent);
                    getActivity().finish();
                } else {
                    ToastUtil.show(getActivity(), "实际收款大于应收金额");
                }
                break;
        }
    }


    @Override
    public void onRecevicedResult(JSONArray jsonArray) {
        Log.i("apps", "onRecevicedResult: " + jsonArray.toString());
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject rec = jsonArray.getJSONObject(i);
                PayMent tmp_payment = new PayMent();
                tmp_payment.setPayMentName(rec.getString("PayMentName"));
                tmp_payment.setPayMoney(0.00);
                tmp_payment.setPayMentId(rec.getLong("id"));
                tmp_payment.setPayModeId(rec.getString("paymodeid"));
                tmp_payment.setCadType("none");

                payMentList.add(tmp_payment);
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        adapter.notifyDataSetChanged();
        PayMoneyChange.getInstance().addObserver(payMoneyWatcher);
        getlasttransctaion();
    }


    private void ReflushTotal() {

        tmp_already_pay=0.00;
        for (int i = 0; i < payMentList.size(); i++) {
            tmp_already_pay += payMentList.get(i).getPayMoney();
        }
        total_payment.setText("" + tmp_already_pay);
        tmp_different_pay=tmp_should_pay-tmp_already_pay;
        different_payment.setText(""+tmp_different_pay);
    }
}


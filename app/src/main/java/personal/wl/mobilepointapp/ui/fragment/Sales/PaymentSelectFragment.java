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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.entity.pos.PayMent;
import personal.wl.mobilepointapp.entity.pos.Product;
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


    private List<PayMent> NeedPayMent = new ArrayList<>();
    private RecyclerView paymentRecyclerView;
    private int mNextRequestPage = 1;
    private MPAPaymentListAdapter adapter;
    private List<PayMent> payMentList = new ArrayList<>();
//    private ObservableArrayList<PayMent> payMentList = new ObservableArrayList<>();


    private WebServicePara parain;
    private List<WebServicePara> paraList = new ArrayList<>();
    private CallWebservices callWebservices;
    private TextView total_payment;


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
    public void onDestroy() {
        super.onDestroy();
        ToastUtil.show(getActivity(), "sku=>fragment=>onDestroy");
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

//                onepayment.addOnPropertyChangedCallback(new android.databinding.Observable.OnPropertyChangedCallback() {
//                    @Override
//                    public void onPropertyChanged(android.databinding.Observable sender, int propertyId) {
//                        Log.i(TAG, "onPropertyChanged: 回来了");
//                    }
//                });
                payMentList.add(onepayment);
            }
            adapter.notifyDataSetChanged();

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
        paymentRecyclerView.setAdapter(adapter);

//        adapter.setOnItemClickListener(this);
//        adapter.setOnItemChildClickListener(this);
//        adapter.openLoadAnimation();
//        getfunctions();

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
            case R.id.sku_detail_layout_selected_btn:
                Intent callintent = new Intent();
                callintent.putExtra(SKU_SELECT_RESULT_EXTRA_CODE, (Serializable) payMentList);
                getActivity().setResult(SKU_SELECT_RESULT_CODE, callintent);
                getActivity().finish();

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
                tmp_payment.setPayModeId("111");
                tmp_payment.setCadType("none");

                payMentList.add(tmp_payment);
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        adapter.notifyDataSetChanged();
        PayMoneyChange.getInstance().addObserver(payMoneyWatcher);
    }


    public void ReflushTotal() {
        double tmp_total_amt = 0.00;
        for (int i = 0; i < payMentList.size(); i++) {
            tmp_total_amt += payMentList.get(i).getPayMoney();
        }

        total_payment.setText("" + tmp_total_amt);

    }

}


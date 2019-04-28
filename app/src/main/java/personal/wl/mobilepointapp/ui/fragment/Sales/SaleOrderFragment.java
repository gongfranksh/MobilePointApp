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
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.common.MobilePointApplication;
import personal.wl.mobilepointapp.common.PosPayMentConstant;
import personal.wl.mobilepointapp.entity.pos.Branch;
import personal.wl.mobilepointapp.entity.pos.BranchEmployee;
import personal.wl.mobilepointapp.entity.pos.Member;
import personal.wl.mobilepointapp.entity.pos.PayMent;
import personal.wl.mobilepointapp.entity.pos.PosMachine;
import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.entity.pos.SaleDaily;
import personal.wl.mobilepointapp.entity.pos.SalePayMode;
import personal.wl.mobilepointapp.preference.CurrentUser.MPALoginInfo;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.BranchEmployeeSelectActivity;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.MemberSelectActivity;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.PaymentSelectActivity;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.SkuSelectActivity;
import personal.wl.mobilepointapp.ui.adapter.MPASaleOrderListAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.MPADeviceUtils;
import personal.wl.mobilepointapp.utils.ToastUtil;
import personal.wl.mobilepointapp.webservice.CallWebservices;
import personal.wl.mobilepointapp.webservice.WebServiceInterface;
import personal.wl.mobilepointapp.webservice.WebServicePara;

import static personal.wl.mobilepointapp.common.AppConstant.PAYMMENT_NEED_PAY_CODE;
import static personal.wl.mobilepointapp.common.AppConstant.SKU_SELECT_RESULT_EXTRA_CODE;


public class SaleOrderFragment extends BaseFragment implements WebServiceInterface, View.OnClickListener {

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

    private List<SalePayMode> payModeList;


    private Branch current_branch;
    private BranchEmployee curr_operator;
    private PosMachine curr_posmachine;
    private Member member;


    private TextView sales_total_amount;
    private TextView sales_total_payment;
    private TextView sales_operator;
    private TextView sales_member;
    private TextView sales_posmachine;

    private TextView sales_branch;

    private TextView sales_detail_layout_buy_total_amount;
    private TextView sales_detail_layout_buy_total_qty;


    private Button sale_order_submit;
    private double tmp_alreadpay = 0.00;

    private CallWebservices callWebservices;
    private WebServicePara parain;
    private List<WebServicePara> paraList = new ArrayList<>();


    private String saleid_return = null;


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
        sales_posmachine = view.findViewById(R.id.sales_order_posmachine_selected);

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

            case R.id.sale_order_submit_btn:
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
            curr_posmachine = MobilePointApplication.loginInfo.getCurrentPosMachine();
            if (curr_posmachine != null) {
                sales_posmachine.setText(curr_posmachine.getPosno());
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

    private void saleordersubmit() {
        //合并成为交易资料
        String tmp_transid = MPADeviceUtils.GetTransInnerID();
        String tmp_deviceid = MPADeviceUtils.getUniqueId(getActivity());

        payModeList = new ArrayList<>();

        double c1 = 0.00, c2 = 0.00, c3 = 0.00, c4 = 0.00, c5 = 0.00, c6 = 0.00, c7 = 0.00, c8 = 0.00;
        for (int i = 0; i < AlreadyPaylist.size(); i++) {
            if (AlreadyPaylist.get(i).getPayMoney() != 0) {
                SalePayMode salePayMode = new SalePayMode();
                salePayMode.setBraid(current_branch.getBraid());
                salePayMode.setSaleDate(new Date(System.currentTimeMillis()));
                salePayMode.setPayModeId(AlreadyPaylist.get(i).getPayModeId());
                salePayMode.setOrderInnerId(tmp_transid);
                salePayMode.setDeviceId(tmp_deviceid);
                if (curr_operator != null) {
                    salePayMode.setSalerId(curr_operator.getEmpid());
                } else {
                    break;
                }

                salePayMode.setPayMoney(AlreadyPaylist.get(i).getPayMoney());
                payModeList.add(salePayMode);

                switch ((int) AlreadyPaylist.get(i).getPayMentId().longValue()) {
                    case PosPayMentConstant.PAYMENT_CASH_CODE:
                        c1 = AlreadyPaylist.get(i).getPayMoney();
                        break;
                    case PosPayMentConstant.PAYMENT_BANK_CODE:
                        c2 = AlreadyPaylist.get(i).getPayMoney();
                        break;
                    case PosPayMentConstant.PAYMENT_GIFT_CODE:
                        c4 = AlreadyPaylist.get(i).getPayMoney();
                        break;
                    case PosPayMentConstant.PAYMENT_COUPON_CODE:
                        c6 = AlreadyPaylist.get(i).getPayMoney();
                        break;
                    case PosPayMentConstant.PAYMENT_WEIXIN_CODE:
                        c7 = AlreadyPaylist.get(i).getPayMoney();
                        break;
                    case PosPayMentConstant.PAYMENT_ALIPAY_CODE:
                        c8 = AlreadyPaylist.get(i).getPayMoney();
                        break;
                }

            }
        }

        for (int i = 0; i < saleDailyList.size(); i++) {
            Double tmp_amt = 0.00;
            saleDailyList.get(i).setBraid(current_branch.getBraid());
            saleDailyList.get(i).setPosNo(curr_posmachine.getPosno());
            saleDailyList.get(i).setSaleMan(curr_operator.getEmpid());
            saleDailyList.get(i).setSalerId(curr_operator.getEmpid());
            if (member != null) {
                saleDailyList.get(i).setMemCardNo(member.getCardid());
            }
            saleDailyList.get(i).setOrderInnerId(tmp_transid);
            saleDailyList.get(i).setDeviceId(tmp_deviceid);

            saleDailyList.get(i).setCash1(0.00);
            saleDailyList.get(i).setCash2(0.00);
            saleDailyList.get(i).setCash3(0.00);
            saleDailyList.get(i).setCash4(0.00);
            saleDailyList.get(i).setCash5(0.00);
            saleDailyList.get(i).setCash6(0.00);
            saleDailyList.get(i).setCash7(0.00);
            saleDailyList.get(i).setCash8(0.00);

            if (tmp_alreadpay != 0.00) {
                double rate = saleDailyList.get(i).getSaleAmt() / tmp_alreadpay;
                DecimalFormat df = new DecimalFormat("######0.00");
                if (c1 != 0.00) {
                    if (i != saleDailyList.size() - 1) {
                        saleDailyList.get(i).setCash1(c1 * rate);
                    } else {
                        tmp_amt = 0.00;
                        for (int j = 0; j < saleDailyList.size(); j++) {
                            if (saleDailyList.get(j).getCash1() != null) {
                                tmp_amt += saleDailyList.get(j).getCash1();
                            }
                        }
                        saleDailyList.get(i).setCash1(c1 - tmp_amt);
                    }
                }

                if (c2 != 0.00) {
                    if (i != saleDailyList.size() - 1) {
                        saleDailyList.get(i).setCash2(c2 * rate);
                    } else {
                        tmp_amt = 0.00;
                        for (int j = 0; j < saleDailyList.size(); j++) {
                            if (saleDailyList.get(j).getCash2() != null) {
                                tmp_amt += saleDailyList.get(j).getCash2();
                            }
                        }

                        saleDailyList.get(i).setCash2(c2 - tmp_amt);
                    }
                }


                if (c3 != 0.00) {
                    if (i != saleDailyList.size() - 1) {
                        saleDailyList.get(i).setCash3(c3 * rate);
                    } else {
                        tmp_amt = 0.00;
                        for (int j = 0; j < saleDailyList.size(); j++) {
                            if (saleDailyList.get(j).getCash3() != null) {
                                tmp_amt += saleDailyList.get(j).getCash3();
                            }
                        }
                        saleDailyList.get(i).setCash3(c3 - tmp_amt);
                    }
                }
                if (c4 != 0.00) {
                    if (i != saleDailyList.size() - 1) {
                        saleDailyList.get(i).setCash4(c4 * rate);
                    } else {
                        tmp_amt = 0.00;
                        for (int j = 0; j < saleDailyList.size(); j++) {
                            if (saleDailyList.get(j).getCash4() != null) {
                                tmp_amt += saleDailyList.get(j).getCash4();
                            }
                        }
                        saleDailyList.get(i).setCash4(c4 - tmp_amt);
                    }
                }
                if (c5 != 0.00) {
                    if (i != saleDailyList.size() - 1) {
                        saleDailyList.get(i).setCash5(c5 * rate);
                    } else {
                        tmp_amt = 0.00;
                        for (int j = 0; j < saleDailyList.size(); j++) {
                            if (saleDailyList.get(j).getCash5() != null) {
                                tmp_amt += saleDailyList.get(j).getCash5();
                            }
                        }
                        saleDailyList.get(i).setCash5(c5 - tmp_amt);
                    }
                }
                if (c6 != 0.00) {
                    if (i != saleDailyList.size() - 1) {
                        saleDailyList.get(i).setCash6(c6 * rate);
                    } else {
                        tmp_amt = 0.00;
                        for (int j = 0; j < saleDailyList.size(); j++) {
                            if (saleDailyList.get(j).getCash6() != null) {
                                tmp_amt += saleDailyList.get(j).getCash6();
                            }
                        }
                        saleDailyList.get(i).setCash6(c6 - tmp_amt);
                    }
                }
                if (c7 != 0.00) {
                    if (i != saleDailyList.size() - 1) {
                        saleDailyList.get(i).setCash7(c7 * rate);
                    } else {
                        tmp_amt = 0.00;
                        for (int j = 0; j < saleDailyList.size(); j++) {
                            if (saleDailyList.get(j).getCash7() != null) {
                                tmp_amt += saleDailyList.get(j).getCash7();
                            }
                        }
                        saleDailyList.get(i).setCash7(c7 - tmp_amt);
                    }
                }

                if (c8 != 0.00) {
                    if (i != saleDailyList.size() - 1) {
                        saleDailyList.get(i).setCash8(c8 * rate);
                    } else {
                        tmp_amt = 0.00;
                        for (int j = 0; j < saleDailyList.size(); j++) {
                            if (saleDailyList.get(j).getCash8() != null) {
                                tmp_amt += saleDailyList.get(j).getCash8();
                            }
                        }
                        saleDailyList.get(i).setCash8(c8 - tmp_amt);
                    }
                }


            }


        }


        //组装本次交易数据
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
        String str_curr_branch = gson.toJson(current_branch);
        JsonObject json_curr_branch = new JsonObject();
        String str_curr_operator = gson.toJson(curr_operator);
        String str_list_saledaily = gson.toJson(saleDailyList);
        String str_list_payment = gson.toJson(payModeList);
        String str_member = gson.toJson(member);
        String str_posmachine = gson.toJson(curr_posmachine);
        JsonObject sales = new JsonObject();
        sales.addProperty("branch", str_curr_branch);
        sales.addProperty("operator", str_curr_operator);
        sales.addProperty("saledaily", str_list_saledaily);
        sales.addProperty("payment", str_list_payment);
        sales.addProperty("posmachine", str_posmachine);
        sales.addProperty("member", str_member);


        //提报本机设备ID
        sales.addProperty("device_uuid", tmp_deviceid);
        //提报本次交易ID
        sales.addProperty("trans_uuid", tmp_transid);

        //缓存到本地
        MPALoginInfo.getInstance().setLastTranscation(sales.toString());
        //准备提交中间层
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

        if (jsonArray != null) {
            try {
                JSONObject result = jsonArray.getJSONObject(0);
                String str_result = result.getString("pos_order_submit_state");
                if (str_result.equals("ok")) {
                    saleid_return = result.getString("pos_order_return_saleid");
                    ToastUtil.show(getActivity(), "交易号：" + saleid_return);
                    resetsaleid();
                    MPALoginInfo.getInstance().ClearCurrentTranscationCache();
                    flushtranscation();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.i("saleorder", "onRecevicedResult: " + jsonArray.toString());


    }


    public void flushtranscation() {
        saleDailyList.clear();
        payModeList.clear();
        mpaSaleOrderListAdapter.notifyDataSetChanged();
        sales_operator.setText("");
        sales_total_payment.setText("0.0");
        ReflashValue();
    }

    public void resetsaleid() {
        //更新数组内的交易流水号码
        if (saleid_return != null) {
            for (int i = 0; i < saleDailyList.size(); i++) {
                saleDailyList.get(i).setSaleId(saleid_return);
            }

            for (int i = 0; i < payModeList.size(); i++) {
                payModeList.get(i).setSaleId(saleid_return);

            }

        }
    }
}

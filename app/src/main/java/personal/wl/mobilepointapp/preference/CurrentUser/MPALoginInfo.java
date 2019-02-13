package personal.wl.mobilepointapp.preference.CurrentUser;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.JsonArray;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import personal.wl.mobilepointapp.auth.ldap.User;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.entity.pos.Branch;
import personal.wl.mobilepointapp.entity.pos.BranchEmployee;
import personal.wl.mobilepointapp.entity.pos.Member;
import personal.wl.mobilepointapp.entity.pos.PayMent;
import personal.wl.mobilepointapp.entity.pos.PosMachine;
import personal.wl.mobilepointapp.entity.pos.SaleDaily;
import personal.wl.mobilepointapp.utils.MPADateUtils;

import static personal.wl.mobilepointapp.common.AppConstant.CURRENT_TRANSACATIONS;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CURRENT_ACCOUNT;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CURRENT_LOGIN;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CURRENT_LOGIN_DATE;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CURRENT_NEED_TRANS;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.DEFAULT_CURRENT_ACCOUNT;

public class MPALoginInfo {

    private static final MPALoginInfo instance = new MPALoginInfo();


    private SharedPreferences sPre;
    private Context context;
    private Gson gon;


    public static MPALoginInfo getInstance() {
        return instance;
    }


    public User getUser() {
        String struser = sPre.getString(CURRENT_ACCOUNT, null);
        user = gon.fromJson(struser, User.class);
        Date logdate = MPADateUtils.StrToDate(sPre.getString(CURRENT_LOGIN_DATE, "2000-01-01 01:01:01"));
        Date currentdate = new Date(System.currentTimeMillis());
        long diff = MPADateUtils.DateDiff(logdate, currentdate);
        //超过三天重新登陆
        if (diff >= 3) {
            return null;
        }
        return user;
    }

    public void setUser(User user) {
        sPre.edit().putString(CURRENT_ACCOUNT, gon.toJson(user)).commit();
        sPre.edit().putString(CURRENT_LOGIN_DATE, MPADateUtils.DateToStr(new Date(System.currentTimeMillis()))).commit();

    }

    public void setLastTranscation(String needTrans) {
        sPre.edit().putString(CURRENT_NEED_TRANS, needTrans).commit();

    }


    public void setCurrentTranscation(List<SaleDaily> saleDailyList) {
        sPre.edit().putString(CURRENT_TRANSACATIONS, gon.toJson(saleDailyList)).commit();
    }

    public List<SaleDaily> getCurrentTranscation() throws JSONException {
        JSONArray saledailyarray_json = null;
        SaleDaily saleDaily;
        List<SaleDaily> result_saledaily = new ArrayList<>();
        String strlistsaledaliy = sPre.getString(CURRENT_TRANSACATIONS, null);

        if (strlistsaledaliy != null && strlistsaledaliy.length() != 0
                && !strlistsaledaliy.equals("null")
                ) {

            try {
                saledailyarray_json = new JSONArray(strlistsaledaliy);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            for (int i = 0; i < saledailyarray_json.length(); i++) {
                JSONObject myjObject = saledailyarray_json.getJSONObject(i);
                saleDaily = gon.fromJson(String.valueOf(myjObject), SaleDaily.class);

                result_saledaily.add(saleDaily);

            }
        }
        return result_saledaily;
    }

    public void logout() {
        User u = null;
        this.setUser(u);

    }

    public  void ClearCurrentTranscationCache(){
        this.setCurrentMember(null);
        this.setCurrentOperator(null);
        this.setCurrentPayMent(null);
        this.setCurrentTranscation(null);
        this.setLastTranscation(null);
    }

    public void setCurrentBranch(Branch branch) {
        sPre.edit().putString(AppConstant.CURRENT_BRANCH, gon.toJson(branch)).commit();
    }

    public Branch getCurrentBranch() {
        String strbranch = sPre.getString(AppConstant.CURRENT_BRANCH, null);
        Branch branch = gon.fromJson(strbranch, Branch.class);
        return branch;
    }

    public void setCurrentMember(Member member){
        sPre.edit().putString(AppConstant.CURRENT_MEMBER, gon.toJson(member)).commit();
    }



    public void setCurrentPosMachine(PosMachine posMachine){
        sPre.edit().putString(AppConstant.CURRENT_POSMACHINE, gon.toJson(posMachine)).commit();
    }

    public PosMachine getCurrentPosMachine() {
        String strposmachine = sPre.getString(AppConstant.CURRENT_POSMACHINE, null);
        PosMachine posMachine = gon.fromJson(strposmachine, PosMachine.class);
        return posMachine;
    }



    public Member getCurrentMember() {
        String strbranch = sPre.getString(AppConstant.CURRENT_MEMBER, null);
        Member member = gon.fromJson(strbranch, Member.class);
        return member;
    }





    public void setCurrentOperator(BranchEmployee operator) {

        sPre.edit().putString(AppConstant.CURRENT_OPERATOR, gon.toJson(operator)).commit();
    }

    public BranchEmployee getCurrentOperator() {
        String stroperator = sPre.getString(AppConstant.CURRENT_OPERATOR, null);
        BranchEmployee operator = gon.fromJson(stroperator, BranchEmployee.class);
        return operator;
    }

    public void setCurrentPayMent(List<PayMent> payments) {
        sPre.edit().putString(AppConstant.CURRENT_PAYMENT, gon.toJson(payments)).commit();
    }

    public List<PayMent> getCurrentPayment() throws JSONException {
        JSONArray paymentarray_json = null;
        PayMent payment;
        List<PayMent> result_Paments = new ArrayList<>();
        String strlistpayment = sPre.getString(AppConstant.CURRENT_PAYMENT, null);

        if (strlistpayment != null && strlistpayment.length() != 0 && !strlistpayment.equals("null")) {
            try {
                paymentarray_json = new JSONArray(strlistpayment);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < paymentarray_json.length(); i++) {
                JSONObject myjObject = paymentarray_json.getJSONObject(i);
                payment = gon.fromJson(String.valueOf(myjObject), PayMent.class);

                result_Paments.add(payment);

            }
        }
        return result_Paments;
    }


    private User user;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        gon = new Gson();
        this.sPre = sPre;
        sPre = context.getSharedPreferences(
                CURRENT_LOGIN, 0);
    }


    private MPALoginInfo() {

    }
}

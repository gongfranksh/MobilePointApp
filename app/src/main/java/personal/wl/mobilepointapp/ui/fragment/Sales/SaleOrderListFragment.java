package personal.wl.mobilepointapp.ui.fragment.Sales;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.dao.CityDbHelper;
import personal.wl.mobilepointapp.entity.pos.BranchEmployee;
import personal.wl.mobilepointapp.listener.OnItemClickListener;
import personal.wl.mobilepointapp.preference.CurrentUser.MPALoginInfo;
import personal.wl.mobilepointapp.ui.adapter.MPAOperatorListAdapter;
import personal.wl.mobilepointapp.ui.adapter.MPAOperatorResultAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.ui.widget.SidebarView;
import personal.wl.mobilepointapp.utils.MPAStringUtils;
import personal.wl.mobilepointapp.webservice.CallWebservices;
import personal.wl.mobilepointapp.webservice.WebServiceInterface;
import personal.wl.mobilepointapp.webservice.WebServicePara;

/**
 * Created by weiliang on 2019/1/29.
 */

public class SaleOrderListFragment extends BaseFragment implements WebServiceInterface,  TextWatcher, OnItemClickListener {

    private static final String TAG = SaleOrderListFragment.class.getSimpleName();

    private CallWebservices callWebservices;
    private WebServicePara parain;
    private List<WebServicePara> paraList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saleorderlist, container, false);
        initView(view);
        initData();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initData() {
        getfunctions();

    }

    private void initView(View view) {
    }

    public void getfunctions() {
        parain = new WebServicePara();
        paraList = new ArrayList<>();
        callWebservices = null;
        parain.setPara_name(AppConstant.PARA_BRANCHCODE);
        parain.setPara_value("01002");
        paraList.add(parain);
        parain = new WebServicePara();
        callWebservices = new CallWebservices(this, AppConstant.Method_GET_BRANCH_EMPLOYEE_, paraList);
        callWebservices.execute();
    }

    @Override
    public void onRecevicedResult(JSONArray jsonArray) {

    }








    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }




    @Override
    public void onItemClick(View view, int postion) {


    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }




}


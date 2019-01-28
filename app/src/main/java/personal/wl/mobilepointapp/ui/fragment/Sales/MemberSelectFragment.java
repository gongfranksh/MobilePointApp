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
import personal.wl.mobilepointapp.entity.pos.Member;
import personal.wl.mobilepointapp.listener.OnItemClickListener;
import personal.wl.mobilepointapp.preference.CurrentUser.MPALoginInfo;
import personal.wl.mobilepointapp.ui.adapter.MPAMemberListAdapter;
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

public class MemberSelectFragment extends BaseFragment implements WebServiceInterface, TextWatcher, OnItemClickListener {

    private static final String TAG = MemberSelectFragment.class.getSimpleName();

    private EditText mEtSearch;
    private TextView mTvShow;
    private RecyclerView mRvMemberList;

    private TextView mTvNoResult;
    private RelativeLayout mContentLayout;
    private LinearLayoutManager mLinearLayoutManager;


    private List<Member> memberList = new ArrayList<>();


    private MPAMemberListAdapter mpaMemberListAdapter;


    private CallWebservices callWebservices;
    private WebServicePara parain;
    private List<WebServicePara> paraList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void initView(View view) {
        mTvShow = view.findViewById(R.id.member_tv_show);
        mEtSearch = view.findViewById(R.id.et_member_search);
        mRvMemberList = view.findViewById(R.id.member_rv_list);


        mTvNoResult = view.findViewById(R.id.member_tv_no_result);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRvMemberList.setLayoutManager(mLinearLayoutManager);
        mContentLayout = view.findViewById(R.id.member_fra_content_layout);

//        mEtSearch.setText("13052043303");

        mEtSearch.addTextChangedListener(this);

    }


    public void getfunctions(String mobile) {

        parain = new WebServicePara();
        paraList = new ArrayList<>();
        callWebservices = null;

        parain.setPara_name(AppConstant.PARA_MOBILE);
        parain.setPara_value(mobile);
        paraList.add(parain);
        parain = new WebServicePara();

        callWebservices = new CallWebservices(this, AppConstant.Method_GET_MEMBER_BY_MOBILE_, paraList);
        callWebservices.execute();
    }

    @Override
    public void onRecevicedResult(JSONArray jsonArray) {
        try {

            if (jsonArray.length()==0){
                mRvMemberList.setVisibility(View.GONE);
                mTvNoResult.setVisibility(View.VISIBLE);
                return;
            }


            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject rec = jsonArray.getJSONObject(i);
                Member tmp_member = new Member();
                tmp_member.setCardid(rec.getString("bncid"));
                tmp_member.setMobile(rec.getString("mobile"));
                tmp_member.setNickName(rec.getString("nickname"));
                memberList.add(tmp_member);
            }


            mRvMemberList.setVisibility(View.VISIBLE);
            mTvNoResult.setVisibility(View.GONE);
            mpaMemberListAdapter = new MPAMemberListAdapter(getActivity(), memberList);
            mRvMemberList.setAdapter(mpaMemberListAdapter);
            mpaMemberListAdapter.setmClickListener(this);
            mpaMemberListAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {


        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString() == null || "".equals(s.toString().trim()) || s.length() != 11) {
            memberList.clear();
            mRvMemberList.setVisibility(View.VISIBLE);
            mTvNoResult.setVisibility(View.GONE);
        } else {
            mRvMemberList.setVisibility(View.GONE);
            getfunctions(s.toString());
        }


    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    @Override
    public void onItemClick(View view, int postion) {



//        MPALoginInfo.getInstance().setCurrentOperator(branchEmployeeList.get(postion));
        returnMember(memberList.get(postion));

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }


    private void returnMember(Member member) {
        Intent callintent = new Intent();
//        MPALoginInfo.getInstance().setCurrentPayMent(operator);
        callintent.putExtra(AppConstant.MEMBER_SELECT_RESULT_EXTRA_CODE, (Serializable) member);
        getActivity().setResult(AppConstant.MEMBER_NEED_CODE, callintent);
        getActivity().finish();

    }

}


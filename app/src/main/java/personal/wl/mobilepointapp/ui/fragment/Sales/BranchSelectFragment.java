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
import personal.wl.mobilepointapp.entity.pos.Branch;
import personal.wl.mobilepointapp.entity.pos.BranchEmployee;
import personal.wl.mobilepointapp.listener.OnItemClickListener;
import personal.wl.mobilepointapp.preference.CurrentUser.MPALoginInfo;
import personal.wl.mobilepointapp.ui.adapter.MPABranchListAdapter;
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

public class BranchSelectFragment extends BaseFragment implements WebServiceInterface, SidebarView.OnSlidingListener, TextWatcher, OnItemClickListener {

    private static final String TAG = BranchSelectFragment.class.getSimpleName();

    private SidebarView mSidebar;
    private EditText mEtSearch;
    private TextView mTvShow;
    private RecyclerView mRvOperatorList;
    private RecyclerView mRvSearchResult;
    private TextView mTvNoResult;
    private RelativeLayout mContentLayout;
    private LinearLayoutManager mLinearLayoutManager;

    private List<Branch> branches = new ArrayList<>();
    private List<Branch> allbranches = new ArrayList<>();
    private List<Branch> searchbranch = new ArrayList<>();



    private MPABranchListAdapter mpaBranchListAdapter;


    private CallWebservices callWebservices;
    private WebServicePara parain;
    private List<WebServicePara> paraList = new ArrayList<>();


    private Button bt_test;


    private CityDbHelper mCityDbHelper;
    private boolean isMove = false;
    private int mIndex = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_branch, container, false);
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
        mTvShow = view.findViewById(R.id.branch_tv_show);

        mEtSearch = view.findViewById(R.id.et_branch_search);
        mRvOperatorList = view.findViewById(R.id.branch_rv_list);
        mSidebar = view.findViewById(R.id.branch_sidebar);
        mRvSearchResult = view.findViewById(R.id.branch_rv_search_result);
        mTvNoResult = view.findViewById(R.id.branch_tv_no_result);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRvOperatorList.setLayoutManager(mLinearLayoutManager);
        mContentLayout = view.findViewById(R.id.branch_fra_content_layout);


        mSidebar.setShowText(mTvShow);
        mSidebar.setOnSlidingListener(this);
        mEtSearch.addTextChangedListener(this);

    }

    public void getfunctions() {

        parain = new WebServicePara();
        paraList = new ArrayList<>();
        callWebservices = null;

        parain.setPara_name(AppConstant.PARA_BRANCHCODE);
        parain.setPara_value("01002");
        paraList.add(parain);
        parain = new WebServicePara();

        callWebservices = new CallWebservices(this, AppConstant.Method_GET_BRANCH, paraList);
        callWebservices.execute();
    }

    @Override
    public void onRecevicedResult(JSONArray jsonArray) {
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject rec = jsonArray.getJSONObject(i);
                Branch tmp_branch = new Branch();
                tmp_branch.setBraid(rec.getString("BraId"));
                tmp_branch.setBraname(rec.getString("BraName"));
                tmp_branch.setBrasname(rec.getString("BraSName"));
                tmp_branch.setPinYin(MPAStringUtils.getPingYin(rec.getString("BraName")));
                branches.add(tmp_branch);

            }

            mpaBranchListAdapter = new MPABranchListAdapter(getActivity(), branches);
            mRvOperatorList.setAdapter(mpaBranchListAdapter);
            mpaBranchListAdapter.setmClickListener(this);
            mpaBranchListAdapter.notifyDataSetChanged();

//            mpaBranchListAdapter = new MPAOperatorResultAdapter(getActivity(), searchBranchEmployeeData);
//
//            mRvSearchResult.setAdapter(mpaOperatorResultAdapter);
//            mpaOperatorResultAdapter.notifyDataSetChanged();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {


        }

    }

    @Override
    public void onSliding(String selectedStr) {

        mTvShow.setText(selectedStr);
        if (mpaBranchListAdapter.alphaIndexer.get(selectedStr) != null) {
            int position = mpaBranchListAdapter.alphaIndexer.get(selectedStr);
            move(position);
        }

    }

    /**
     * 滑动到指定位置
     *
     * @param n
     */
    private void move(int n) {
        mIndex = n;
        mRvOperatorList.stopScroll();
        moveToPosition(n);
    }


    /**
     * RecycleView滑动到指定位置
     *
     * @param n
     */
    private void moveToPosition(int n) {

        int firstItem = mLinearLayoutManager.findFirstVisibleItemPosition();
        int lastItem = mLinearLayoutManager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            //当要置顶的项在当前显示的第一个项的前面时
            mRvOperatorList.scrollToPosition(n);
        } else if (n <= lastItem) {
            //当要置顶的项已经在屏幕上显示时
            int top = mRvOperatorList.getChildAt(n - firstItem).getTop();
            mRvOperatorList.scrollBy(0, top);
        } else {
            //当要置顶的项在当前显示的最后一项的后面时
            mRvOperatorList.scrollToPosition(n);
            isMove = true;
        }

    }

    Comparator<BranchEmployee> mComparator = new Comparator<BranchEmployee>() {
        @Override
        public int compare(BranchEmployee o1, BranchEmployee o2) {
            String str1 = o1.getPinyin().substring(0, 1);
            String str2 = o2.getPinyin().substring(0, 1);
            return str1.compareTo(str2);
        }
    };


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString() == null || "".equals(s.toString().trim())) {

            searchResetData(s.toString());
            branches.clear();
            branches.addAll(allbranches);
//            mSidebar.setVisibility(View.VISIBLE);
            mRvOperatorList.setVisibility(View.VISIBLE);
//            mRvSearchResult.setVisibility(View.GONE);
            mTvNoResult.setVisibility(View.GONE);
        } else {
            mSidebar.setVisibility(View.GONE);
            mRvOperatorList.setVisibility(View.GONE);
            searchResetData(s.toString());
//            getResultCityList(s.toString());
            if (searchbranch.size() <= 0) {
                mRvSearchResult.setVisibility(View.GONE);
                mTvNoResult.setVisibility(View.VISIBLE);
            } else {
                //用于解决The specified child already has a parent. You must call removeView() on the child's parent first.
                mContentLayout.removeView(mRvSearchResult);
//                        mContentLayout.addView(mRvSearchResult);
//                mRvSearchResult.setVisibility(View.VISIBLE);
                mTvNoResult.setVisibility(View.GONE);
                mRvOperatorList.setVisibility(View.VISIBLE);
                branches.clear();
                branches.addAll(searchbranch);
                mpaBranchListAdapter.notifyDataSetChanged();

            }
        }


    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 查询结果列表
     *
     * @param keyword
     */
    private void getResultCityList(String keyword) {


    }

    /**
     * 搜索数据
     *
     * @param s 搜索字符
     */
    public void searchResetData(String s) {
        searchbranch.clear();
        //如果为null，直接使用全部数据
        if (s.equals("")) {
            searchbranch.addAll(branches);
        } else {
            //否则，匹配相应的数据
            for (int i = 0; i < allbranches.size(); i++) {
                if (allbranches.get(i).getBrasname().indexOf(s) >= 0) {//这里可拓展自己想要的，甚至可以拆分搜索汉字来匹配
                    searchbranch.add(allbranches.get(i));
                }
            }
        }

        //刷新数据
//        mpaOperatorListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int postion) {

        String tmp_name = branches.get(postion).getBrasname();
//        ToastUtil.show(getActivity(), "" + tmp_name);


        returnBranch(branches.get(postion));

    }

    @Override
    public void onItemLongClick(View view, int postion) {

    }


    private void returnBranch(Branch branch) {
        Intent callintent = new Intent();
        MPALoginInfo.getInstance().setCurrentBranch(branch);
        callintent.putExtra(AppConstant.BRANCH_SELECT_RESULT_EXTRA_CODE, (Serializable) branch);
        getActivity().setResult(AppConstant.BRANCH_SELECT_CODE, callintent);
        getActivity().finish();

    }

}


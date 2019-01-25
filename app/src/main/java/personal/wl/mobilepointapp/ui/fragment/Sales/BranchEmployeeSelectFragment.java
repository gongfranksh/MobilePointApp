package personal.wl.mobilepointapp.ui.fragment.Sales;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.dao.CityDbHelper;
import personal.wl.mobilepointapp.entity.pos.BranchEmployee;
import personal.wl.mobilepointapp.ui.adapter.MPAOperatorListAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.ui.widget.SidebarView;
import personal.wl.mobilepointapp.utils.MPAStringUtils;
import personal.wl.mobilepointapp.webservice.CallWebservices;
import personal.wl.mobilepointapp.webservice.WebServiceInterface;
import personal.wl.mobilepointapp.webservice.WebServicePara;

/**
 * Created by weiliang on 2019/1/29.
 */

public class BranchEmployeeSelectFragment extends BaseFragment implements WebServiceInterface,SidebarView.OnSlidingListener {

    private static final String TAG = BranchEmployeeSelectFragment.class.getSimpleName();

    private SidebarView mSidebar;
    private EditText mEtSearch;
    private TextView mTvShow;
    private RecyclerView mRvOperatorList;
    private RecyclerView mRvSearchResult;
    private TextView mTvNoResult;
    private RelativeLayout mContentLayout;
    private LinearLayoutManager mLinearLayoutManager;
    private List<BranchEmployee> branchEmployeeList= new ArrayList<>();

    private MPAOperatorListAdapter mpaOperatorListAdapter;


    private CallWebservices callWebservices;
    private WebServicePara parain;
    private List<WebServicePara> paraList = new ArrayList<>();


    private CityDbHelper mCityDbHelper;
    private boolean isMove = false;
    private int mIndex = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_branchemployee, container, false);
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
        mTvShow = view.findViewById(R.id.operator_tv_show);
        mRvOperatorList = view.findViewById(R.id.operator_rv_city_list);
        mSidebar=view.findViewById(R.id.operator_sidebar);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());

        mRvOperatorList.setLayoutManager(mLinearLayoutManager);

        mSidebar.setShowText(mTvShow);
        mSidebar.setOnSlidingListener(this);

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
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject rec = jsonArray.getJSONObject(i);
                BranchEmployee tmp_operator = new BranchEmployee();
                tmp_operator.setBraid(rec.getString("braid"));
                tmp_operator.setEmpid(rec.getString("empid"));
                tmp_operator.setEmpName(rec.getString("empname"));
                tmp_operator.setDomain(rec.getString("domain"));
                tmp_operator.setDiscount(rec.getDouble("discount"));
                tmp_operator.setPassword(rec.getString("password"));
                tmp_operator.setPinyin(MPAStringUtils.getPingYin(rec.getString("empname")));
                branchEmployeeList.add(tmp_operator);
            }

            mpaOperatorListAdapter = new MPAOperatorListAdapter(getActivity(), branchEmployeeList);
            mRvOperatorList.setAdapter(mpaOperatorListAdapter);
            mpaOperatorListAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {


        }

    }

    @Override
    public void onSliding(String selectedStr) {

        mTvShow.setText(selectedStr);
        if(mpaOperatorListAdapter.alphaIndexer.get(selectedStr)!=null){
            int position = mpaOperatorListAdapter.alphaIndexer.get(selectedStr);
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
}


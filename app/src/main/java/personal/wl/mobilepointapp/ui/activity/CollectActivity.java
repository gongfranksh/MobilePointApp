package personal.wl.mobilepointapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.exception.BmobException;
import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.common.BmobManager;
import personal.wl.mobilepointapp.listener.BmobQueryCallback;
import personal.wl.mobilepointapp.model.BaseModel;
import personal.wl.mobilepointapp.model.FavorModel;
import personal.wl.mobilepointapp.model.User;
import personal.wl.mobilepointapp.ui.adapter.FavorListAdapter;
import personal.wl.mobilepointapp.ui.base.BaseActivity;
import personal.wl.mobilepointapp.ui.fragment.HomeFragment;

public class CollectActivity extends BaseActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ImageView mTitleBarIvBack;
    private ListView mListView;

    private FavorListAdapter mAdapter;
    private List<FavorModel> mDataList = new ArrayList<>();
    private TextView mErrorTv;
    private LinearLayout mErrorLayout;
    private TextView mTitleBarTvManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        initData();
        initView();
    }

    private void initData() {
        BmobManager.getInstance(new BmobQueryCallback() {
            @Override
            public void onQuerySuccess(List<? extends BaseModel> dataList) {
                mDataList.clear();
                List<FavorModel> list = (List<FavorModel>) dataList;
                if (list == null || list.size()==0) {
                    mListView.setVisibility(View.GONE);
                    mErrorLayout.setVisibility(View.VISIBLE);
                } else {
                    mDataList.addAll(list);
                    mAdapter.notifyDataSetChanged();
                    mListView.setVisibility(View.VISIBLE);
                    mErrorLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onQueryFailure(BmobException e) {
                mListView.setVisibility(View.GONE);
                mErrorLayout.setVisibility(View.VISIBLE);
            }
        }).queryFavorData(AppConstant.KEY_USER_ID, User.getCurrentUser(User.class).getObjectId());
    }

    private void initView() {
        mTitleBarIvBack = (ImageView) findViewById(R.id.collect_titleBar_iv_back);
        mTitleBarTvManager = (TextView) findViewById(R.id.collect_titleBar_tv_manager);
        mListView = (ListView) findViewById(R.id.collect_listView);
        mErrorTv = (TextView) findViewById(R.id.collect_error_tv);
        mErrorLayout = (LinearLayout) findViewById(R.id.collect_error_layout);
        mTitleBarIvBack.setOnClickListener(this);
        mTitleBarTvManager.setOnClickListener(this);

        mAdapter = new FavorListAdapter(this, mDataList);
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        bundle.putString(HomeFragment.GOODS_ID, mDataList.get(i).getGoodsId());
        bundle.putString(HomeFragment.GOODS_SEVEN_REFUND, mDataList.get(i).getSevenRefund());
        bundle.putInt(HomeFragment.GOODS_TIME_REFUND, mDataList.get(i).getTimeRefund());
        bundle.putInt(HomeFragment.GOODS_BOUGHT, mDataList.get(i).getBought());
        openActivity(DetailActivity.class,bundle);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.collect_titleBar_iv_back:
                finish();
                break;
            case R.id.collect_titleBar_tv_manager:

                break;
        }
    }
}

package personal.wl.mobilepointapp.ui.fragment.Sales;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.listener.RequestCallBack;
import personal.wl.mobilepointapp.preference.SystemSettingConstant;
import personal.wl.mobilepointapp.ui.adapter.MPASkuListAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.ToastUtil;

import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CLICK_ITEM_VIEW;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.PAGE_SIZE;


public class SkuSelectFragment extends BaseFragment implements View.OnClickListener,BaseQuickAdapter.OnItemClickListener,BaseQuickAdapter.OnItemChildClickListener,BaseQuickAdapter.RequestLoadMoreListener {

    private ImageView skuscan;
    private TextView skuvalue;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mskuRecyclerView;
    private int mNextRequestPage = 1;
    private MPASkuListAdapter adapter;
    private List<Product> data = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sku_select, null);
        initView(view);
        initAdapter();
        initRefreshLayout();
        mSwipeRefreshLayout.setRefreshing(true);
        return view;
    }



    private void initAdapter() {

//        Product p =null;
//
//        for (int i = 0; i <100 ; i++) {
//            p = new Product();
//        p.setTYPE(CLICK_ITEM_VIEW);
//        p.setProName("hello================="+i);
//        p.setProSName("hello"+i);
//        p.setNormalPrice((long) 11.11+i);
//        data.add(p);
//
//        }

        mskuRecyclerView.setAdapter(adapter);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SystemSettingConstant.SCAN_QR_REQUEST) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    ToastUtil.show(getActivity(), "解析结果:" + result);
                    skuvalue.setText(result);
                }
            }
        }
    }

    public void initView(View view) {
        skuscan = view.findViewById(R.id.sku_titleBar_scan_img);
        skuvalue = view.findViewById(R.id.sku_text_input_barcode);
        mskuRecyclerView = view.findViewById(R.id.skulists);
        mskuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mSwipeRefreshLayout = view.findViewById(R.id.skuswipeLayout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));
        adapter = new MPASkuListAdapter(R.layout.item_databing_sku_list,data);
        adapter.openLoadAnimation();
        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
        adapter.setOnLoadMoreListener(this);

        skuscan.setOnClickListener(this);
        mSwipeRefreshLayout.setRefreshing(true);
        refresh();
    }

    private void initRefreshLayout() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }


    private void setData(boolean isRefresh, List data) {
        mNextRequestPage++;
        final int size = data == null ? 0 : data.size();
        if (isRefresh) {
            adapter.setNewData(data);
        } else {
            if (size > 0) {
                adapter.addData(data);
            }
        }
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            adapter.loadMoreEnd(isRefresh);

            ToastUtil.show(getActivity(),"no more data");
        } else {
            adapter.loadMoreComplete();
        }
    }

    private void refresh() {
        mNextRequestPage = 1;
        adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
        mSwipeRefreshLayout.setRefreshing(false);
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<Product> data) {
                setData(true, data);
                adapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void fail(Exception e) {
                ToastUtil.show(getActivity(),"error ddd");
                adapter.setEnableLoadMore(true);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }).start();
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

        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Log.d("Be Click", "skuselectfragment==>onItemClick: ");
        ToastUtil.show(getActivity(), "skuselectfragment==>onItemClick" + position);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        Log.d("Be Click", "skuselectfragment==>onItemChildClick: ");
        ToastUtil.show(getActivity(), "skuselectfragment==>onItemChildClick" + position);

    }

    @Override
    public void onLoadMoreRequested() {
        loadMore();
    }


    private void loadMore() {
        new Request(mNextRequestPage, new RequestCallBack() {
            @Override
            public void success(List<Product> data) {
                /**
                 * fix https://github.com/CymChad/BaseRecyclerViewAdapterHelper/issues/2400
                 */
                boolean isRefresh =mNextRequestPage ==1;
                setData(isRefresh, data);
            }

            @Override
            public void fail(Exception e) {
                adapter.loadMoreFail();
                ToastUtil.show(getActivity(),"eroror");
            }
        }).start();
    }
}

class Request extends Thread {
    private static final int PAGE_SIZE = 6;
    private int mPage;
    private RequestCallBack mCallBack;
    private Handler mHandler;

    private static boolean mFirstPageNoMore;
    private static boolean mFirstError = true;

    private List<Product> getSampleData(int len){
        List<Product> tt= new ArrayList<>();
        Product p =null;
        for (int i = 0; i <len ; i++) {
            p = new Product();
            p.setTYPE(CLICK_ITEM_VIEW);
            p.setProName("hello================="+i);
            p.setProSName("hello"+i);
            p.setNormalPrice((long) 11.11+i);
            tt.add(p);
        }
        return tt;

    }

    public Request(int page, RequestCallBack callBack) {
        mPage = page;
        mCallBack = callBack;
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void run() {
        try {Thread.sleep(500);} catch (InterruptedException e) {}

        if (mPage == 2 && mFirstError) {
            mFirstError = false;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallBack.fail(new RuntimeException("fail"));
                }
            });
        } else {
            int size = PAGE_SIZE;
            if (mPage == 1) {
                if (mFirstPageNoMore) {
                    size = 1;
                }
                mFirstPageNoMore = !mFirstPageNoMore;
                if (!mFirstError) {
                    mFirstError = true;
                }
            } else if (mPage == 4) {
                size = 1;
            }

            final int dataSize = size;
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mCallBack.success(getSampleData(dataSize));
                }
            });
        }
    }
}
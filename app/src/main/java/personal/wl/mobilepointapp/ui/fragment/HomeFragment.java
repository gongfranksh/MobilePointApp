package personal.wl.mobilepointapp.ui.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;

import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.Response;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.common.AppConstant;
import personal.wl.mobilepointapp.entity.FilmInfo;
import personal.wl.mobilepointapp.entity.GoodsInfo;
import personal.wl.mobilepointapp.entity.HomeGridInfo;
import personal.wl.mobilepointapp.listener.ViewPagerListener;
import personal.wl.mobilepointapp.model.User;
import personal.wl.mobilepointapp.network.CallServer;
import personal.wl.mobilepointapp.network.HttpListener;
import personal.wl.mobilepointapp.preference.CurrentUser.MPALoginInfo;
import personal.wl.mobilepointapp.ui.activity.CityActivity;
import personal.wl.mobilepointapp.ui.activity.DetailActivity;
import personal.wl.mobilepointapp.ui.activity.MessageActivity;
import personal.wl.mobilepointapp.ui.adapter.BannerPagerAdapter;
import personal.wl.mobilepointapp.ui.adapter.GoodsListAdapter;
import personal.wl.mobilepointapp.ui.adapter.GridAdapter;
import personal.wl.mobilepointapp.ui.adapter.ViewPageAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.ui.widget.Indicator;
import personal.wl.mobilepointapp.utils.ToastUtil;

import static android.icu.lang.UScript.getCode;
import static personal.wl.mobilepointapp.common.MobilePointApplication.loginInfo;

/**
 * Created by asus on 2016/8/27.
 */
public class HomeFragment extends BaseFragment implements HttpListener<String> {

    private static final int GOOD_REQUEST = 0x01;
    private static final int FILM_REQUEST = 0x02;
    private static final int SCAN_QR_REQUEST = 103;
    public static final String GOODS_ID = "goodsId";
    public static final String GOODS_SEVEN_REFUND = "sevenRefund";
    public static final String GOODS_TIME_REFUND = "timeRefund";
    public static final String GOODS_BOUGHT = "bought";
    private static final int CITY_REQUEST_CODE = 4000;

    private static final String TAG = "HomeFragment";

    private int[] imgRes = new int[]{R.drawable.banner01, R.drawable.banner02, R.drawable.banner03};
    private Handler mHandler = new Handler();
    //广告轮播
    private ViewPager bannerPager;
    private Indicator bannerIndicator;
    private View mView;

    private List<HomeGridInfo> pageOneData = new ArrayList<>();
    private List<HomeGridInfo> pageTwoData = new ArrayList<>();
    private PullToRefreshListView mRefreshListView;
    private List<View> mViewList = new ArrayList<>();

    private List<GoodsInfo.ResultBean.GoodlistBean> mGoodlist = new ArrayList<>();
    private List<FilmInfo.ResultBean> mFilmList = new ArrayList<>();
    private LinearLayout mFilmLayout;
    private ListView mListView;
    private GoodsListAdapter mGoodsListAdapter;

    //是否正在刷新
    private boolean isRefreshing = false;
    private TextView mCityName;
    private TextView tv_input_barcode;


//    private MPALoginInfo loginInfo;

    private personal.wl.mobilepointapp.auth.ldap.User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_home, null);
            initData();
            initViews(mView);
            //自动滚动播放关闭
//            antoScroll();

        }
        return mView;
    }

    private void initData() {
        String[] gridTitles = getResources().getStringArray(R.array.home_bar_labels);
        TypedArray typedArray = getResources().obtainTypedArray(R.array.home_bar_icon);
        for (int i = 0; i < gridTitles.length; i++) {
            if (i < 8) {
                pageOneData.add(new HomeGridInfo(typedArray.getResourceId(i, 0), gridTitles[i]));
            } else {
                pageTwoData.add(new HomeGridInfo(typedArray.getResourceId(i, 0), gridTitles[i]));
            }
        }

        Request<String> goodRequest = NoHttp.createStringRequest(AppConstant.RECOMMEND_URL, RequestMethod.GET);
        CallServer.getInstance().add(getActivity(), GOOD_REQUEST, goodRequest, this, true, true);

        Request<String> filmRequest = NoHttp.createStringRequest(AppConstant.HOT_FILM_URL, RequestMethod.GET);
        CallServer.getInstance().add(getActivity(), FILM_REQUEST, filmRequest, this, true, true);
    }

    private void antoScroll() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentItem = bannerPager.getCurrentItem();
                bannerPager.setCurrentItem(currentItem + 1, true);
                mHandler.postDelayed(this, 2000);
            }
        }, 2000);
    }

    private void initViews(View view) {

//        loginInfo = MPALoginInfo.getInstance();
//        loginInfo.setContext(getActivity());
        user=loginInfo.getUser();

        //titleBar
        View titleView = view.findViewById(R.id.home_titlebar);
        initTitlebar(titleView);
        mRefreshListView = (PullToRefreshListView) view.findViewById(R.id.home_pull_to_refresh_listView);
        tv_input_barcode = (TextView) view.findViewById(R.id.text_input_barcode);


        //header头部
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.home_head_page, null);
        //banner
//        View bannerView = headView.findViewById(R.id.home_head_include_banner);
//        bannerPager = (ViewPager) bannerView.findViewById(R.id.home_banner_pager);
//        bannerIndicator = (Indicator) bannerView.findViewById(R.id.home_banner_indicator);
//        bannerPager.setAdapter(new BannerPagerAdapter(getChildFragmentManager(), imgRes));
//        bannerPager.addOnPageChangeListener(new ViewPagerListener(bannerIndicator));

        ViewPager headPager = (ViewPager) headView.findViewById(R.id.home_head_pager);
        Indicator headIndicator = (Indicator) headView.findViewById(R.id.home_head_indicator);
//        //第一页
        View pageOne = LayoutInflater.from(getActivity()).inflate(R.layout.home_gridview, null);
        GridView gridView1 = (GridView) pageOne.findViewById(R.id.home_gridView);
        gridView1.setAdapter(new GridAdapter(getActivity(), pageOneData));
        gridView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToastUtil.show(getActivity(), "page one" + String.valueOf(i));

            }
        });
        //第二页
        View pageTwo = LayoutInflater.from(getActivity()).inflate(R.layout.home_gridview, null);
        GridView gridView2 = (GridView) pageTwo.findViewById(R.id.home_gridView);
        gridView2.setAdapter(new GridAdapter(getActivity(), pageTwoData));
        gridView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ToastUtil.show(getActivity(), "page two" + String.valueOf(i));

            }
        });
        mViewList.add(pageOne);
        mViewList.add(pageTwo);
        headPager.setAdapter(new ViewPageAdapter(mViewList));
        headPager.addOnPageChangeListener(new ViewPagerListener(headIndicator));

        //热门电影
        View filmView = headView.findViewById(R.id.home_head_include_film);
        mFilmLayout = (LinearLayout) filmView.findViewById(R.id.home_film_ll);

//        mRefreshListView.addHeaderView(bannerView);
        mListView = mRefreshListView.getRefreshableView();
        mListView.addHeaderView(headView);
        mListView.setHeaderDividersEnabled(false);
        int headerViewsCount = mListView.getHeaderViewsCount();
        mGoodsListAdapter = new GoodsListAdapter(getActivity(), mGoodlist, headerViewsCount);
        mRefreshListView.setAdapter(mGoodsListAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Bundle bundle = new Bundle();
                bundle.putString(GOODS_ID, mGoodlist.get(i - 2).getGoods_id());
                bundle.putString(GOODS_SEVEN_REFUND, mGoodlist.get(i - 2).getSeven_refund());
                bundle.putInt(GOODS_TIME_REFUND, mGoodlist.get(i - 2).getTime_refund());
                bundle.putInt(GOODS_BOUGHT, mGoodlist.get(i - 2).getBought());
                openActivity(DetailActivity.class, bundle);
            }
        });

        //下拉刷新
        mRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                isRefreshing = true;
                Request<String> goodRequest = NoHttp.createStringRequest(AppConstant.RECOMMEND_URL, RequestMethod.GET);
                CallServer.getInstance().add(getActivity(), GOOD_REQUEST, goodRequest, HomeFragment.this, true, true);

                Request<String> filmRequest = NoHttp.createStringRequest(AppConstant.HOT_FILM_URL, RequestMethod.GET);
                CallServer.getInstance().add(getActivity(), FILM_REQUEST, filmRequest, HomeFragment.this, true, true);
            }
        });
    }

    private void initTitlebar(View view) {
        LinearLayout cityLayout = (LinearLayout) view.findViewById(R.id.titleBar_location_lay);
        mCityName = (TextView) view.findViewById(R.id.titleBar_city_name);
        cityLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CityActivity.class);
                intent.putExtra(AppConstant.KEY_CITY, mCityName.getText().toString());
                startActivityForResult(intent, CITY_REQUEST_CODE);
            }
        });

        ImageView scanQR = (ImageView) view.findViewById(R.id.titleBar_scan_img);
        scanQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "scanQR.setOnClickListener-->");

                choseHeadImageFromCameraCapture();
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, SCAN_QR_REQUEST);
            }
        });

        ImageView messageBox = (ImageView) view.findViewById(R.id.titleBar_msg_iv);
        messageBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=loginInfo.getUser();
                if (user != null) {
                    openActivity(MessageActivity.class);
                } else {
                    ToastUtil.show(getActivity(), R.string.me_nologin_not_login);
                }
            }
        });
    }


    //--add by weiliang-gong
    private void choseHeadImageFromCameraCapture() {

        String[] permissions = new String[]{Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.VIBRATE,
                Manifest.permission.INTERNET};
        int mRequestCode = 100;
        List<String> mPermissionList = new ArrayList<>();
        mPermissionList.clear();//清空已经允许的没有通过的权限
        //逐个判断是否还有未通过的权限
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(getActivity(), permissions[i]) !=
                    PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限到mPermissionList中
            }
        }
        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(getActivity(), permissions, mRequestCode);
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SCAN_QR_REQUEST) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    ToastUtil.show(getActivity(), "解析结果:" + result);
                    //add by weiliang
                    tv_input_barcode.setText(result);

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.show(getActivity(), "解析二维码失败");
                }
            }
        } else if (requestCode == CITY_REQUEST_CODE && resultCode == CityActivity.CITY_RESULT_CODE) {
            if (data != null) {
                String cityname = data.getStringExtra(AppConstant.KEY_CITY);
                if (cityname != null) {
                    mCityName.setText(cityname);
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSucceed(int what, Response<String> response) {
        String jsonstr;
        JSONArray jsobj;
        if (isRefreshing) {
            mRefreshListView.onRefreshComplete();
            isRefreshing = false;
        }
        switch (what) {
            case GOOD_REQUEST:
                Gson gson = new Gson();
                jsonstr = response.get();
                List<GoodsInfo.ResultBean.GoodlistBean> goodlistBeen;
                goodlistBeen = gson.fromJson(jsonstr, new TypeToken<List<GoodsInfo.ResultBean>>(){}.getType());

//                JSONArray jsobj = new JSONArray(goodlist);
                try {
                    jsobj = new JSONArray(jsonstr);
                    Log.i(TAG,jsobj.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }


//                GoodsInfo goodsInfo = gson.fromJson(response.get(), GoodsInfo.class);
//                List<GoodsInfo.ResultBean.GoodlistBean> goodlistBeen = goodsInfo.getResult().getGoodlist();

                mGoodlist.clear();
                mGoodlist.addAll(goodlistBeen);
                mGoodsListAdapter.notifyDataSetChanged();
                break;
            case FILM_REQUEST:
                Gson filmGson = new Gson();
                jsonstr = response.get();
                List<FilmInfo.ResultBean> filmList = null;
                try {
                    jsobj = new JSONArray(jsonstr);
                    Log.i(TAG,jsobj.toString());
                    filmList = filmGson.fromJson(jsonstr, new TypeToken<List<FilmInfo.ResultBean>>(){}.getType());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                FilmInfo filmInfo = filmGson.fromJson(response.get(), FilmInfo.class);
//                List<FilmInfo.ResultBean> filmList = filmInfo.getResult();
                mFilmList.clear();
                mFilmList.addAll(filmList);
                mFilmLayout.removeAllViews();

                for (int i = 0; i < mFilmList.size(); i++) {
                    View filmItemView = LayoutInflater.from(getActivity()).inflate(R.layout.item_film, null);
                    SimpleDraweeView filmIcon = (SimpleDraweeView) filmItemView.findViewById(R.id.home_film_icon);
                    TextView filmTitle = (TextView) filmItemView.findViewById(R.id.home_film_title);
                    TextView filmGrade = (TextView) filmItemView.findViewById(R.id.home_film_grade);
                    filmIcon.setImageURI(Uri.parse(mFilmList.get(i).getPosterUrl()));
                    filmTitle.setText(mFilmList.get(i).getFilmName());
                    filmGrade.setText(mFilmList.get(i).getGrade() + "分");
                    mFilmLayout.addView(filmItemView);
                }
        }
    }

    @Override
    public void onFailed(int what, Response<String> response) {
        if (isRefreshing) {
            mRefreshListView.onRefreshComplete();
            isRefreshing = false;
            ToastUtil.show(getActivity(), "刷新失败");
        }
    }

    class NetWorkRequestTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }


}

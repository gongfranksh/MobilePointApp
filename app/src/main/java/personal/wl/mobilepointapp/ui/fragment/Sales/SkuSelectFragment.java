package personal.wl.mobilepointapp.ui.fragment.Sales;

import android.content.Intent;
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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.preference.SystemSettingConstant;
import personal.wl.mobilepointapp.ui.activity.SalesOrder.SkuSelectActivity;
import personal.wl.mobilepointapp.ui.adapter.MPASkuListAdapter;
import personal.wl.mobilepointapp.ui.base.BaseFragment;
import personal.wl.mobilepointapp.utils.ToastUtil;
import personal.wl.mobilepointapp.webservice.CallWebservices;
import personal.wl.mobilepointapp.webservice.WebServiceInterface;
import personal.wl.mobilepointapp.webservice.WebServicePara;

import static personal.wl.mobilepointapp.common.AppConstant.Method_QUERY_PRODUCT_BY_BARCODE;
import static personal.wl.mobilepointapp.common.AppConstant.PARA_BARCODE;
import static personal.wl.mobilepointapp.common.AppConstant.PARA_BRANCHCODE;
import static personal.wl.mobilepointapp.common.AppConstant.SKU_SELECT_RESULT_CODE;
import static personal.wl.mobilepointapp.common.AppConstant.SKU_SELECT_RESULT_EXTRA_CODE;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.PAGE_SIZE;


public class SkuSelectFragment extends BaseFragment implements View.OnClickListener, BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.OnItemChildClickListener, WebServiceInterface,
        TextWatcher

{

    private ImageView skuscan;
    private EditText skuvalue;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mskuRecyclerView;
    private int mNextRequestPage = 1;
    private MPASkuListAdapter adapter;
    private List<Product> data = new ArrayList<>();
    private WebServicePara parain;
    private List<WebServicePara> paraList = new ArrayList<>();
    private CallWebservices callWebservices;
    private Button sku_select_btn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sku_select, null);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ToastUtil.show(getActivity(), "sku=>fragment=>onresmue");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ToastUtil.show(getActivity(), "sku=>fragment=>onDestroy");
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
                    getfunctions(result);
                }
            }
        }
    }


    public void getfunctions(String scanresult) {

        parain = new WebServicePara();
        paraList = new ArrayList<>();
        callWebservices = null;

        parain.setPara_name(PARA_BRANCHCODE);
        parain.setPara_value("01002");
        paraList.add(parain);
        parain = new WebServicePara();
        parain.setPara_name(PARA_BARCODE);
        parain.setPara_value(scanresult);
        paraList.add(parain);
        callWebservices = new CallWebservices(this, Method_QUERY_PRODUCT_BY_BARCODE, paraList);
        callWebservices.execute();
    }

    public void initView(View view) {


        skuscan = view.findViewById(R.id.sku_titleBar_scan_img);
        skuvalue = view.findViewById(R.id.sku_text_input_barcode);
        sku_select_btn = view.findViewById(R.id.sku_detail_layout_selected_btn);


        skuvalue.addTextChangedListener(this);

        mskuRecyclerView = view.findViewById(R.id.skulists);
        mskuRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

//        mSwipeRefreshLayout = view.findViewById(R.id.skuswipeLayout);
//        mSwipeRefreshLayout.setColorSchemeColors(Color.rgb(47, 223, 189));

        adapter = new MPASkuListAdapter(R.layout.item_databing_sku_list, data);
        adapter.openLoadAnimation();

        adapter.setOnItemClickListener(this);
        adapter.setOnItemChildClickListener(this);
//        adapter.setOnLoadMoreListener(this);
        skuscan.setOnClickListener(this);
        sku_select_btn.setOnClickListener(this);
        mskuRecyclerView.setAdapter(adapter);
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

            ToastUtil.show(getActivity(), "no more data");
        } else {
            adapter.loadMoreComplete();
        }
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
                callintent.putExtra(SKU_SELECT_RESULT_EXTRA_CODE, (Serializable) data);
                getActivity().setResult(SKU_SELECT_RESULT_CODE, callintent);
                getActivity().finish();

                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtil.show(getActivity(), "skuselectfragment==>onItemClick" + position);
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        switch (view.getId()) {
            case R.id.iv_num_add:
                data.get(position).setSaleQty(data.get(position).getSaleQty() + 1);
                break;
            case R.id.iv_num_reduce:
                if (data.get(position).getSaleQty() != 0) {
                    data.get(position).setSaleQty(data.get(position).getSaleQty() - 1);
                } else {
                    data.get(position).setSaleQty(0.00);
                    adapter.remove(position);
                }
                break;
            default:
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//        skuvalue.setText("");
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onRecevicedResult(JSONArray jsonArray) {
        Log.i("apps", "onRecevicedResult: " + jsonArray.toString());
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject rec = jsonArray.getJSONObject(i);
                Product tmp_product = new Product();
                tmp_product = tmp_product.getfromjson(rec);
                data.add(tmp_product);
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
        adapter.notifyDataSetChanged();
    }


}


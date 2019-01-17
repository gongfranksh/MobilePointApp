package personal.wl.mobilepointapp.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yolanda.nohttp.Logger;

import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.utils.ToastUtil;

import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CLICK_ITEM_VIEW;

public class MPASkuListAdapter extends BaseMultiItemQuickAdapter<Product, BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {

    private TextView proname;
    private TextView sales;

    public MPASkuListAdapter(List<Product> data) {
        super(data);
//        for (int i = 0; i < data.size(); i++) {

        addItemType(CLICK_ITEM_VIEW, R.layout.item_sku_list);
//        }

    }

    @Override
    protected void convert(BaseViewHolder helper, Product item) {
        switch (helper.getItemViewType()){
            case CLICK_ITEM_VIEW:
                helper.addOnClickListener(R.id.sku_good_bt1);
                proname= helper.getView(R.id.item_sku_title);
                proname.setText(item.getProName());
                break;
        }

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtil.show(mContext,"itemclick点击了"+position);

    }
}

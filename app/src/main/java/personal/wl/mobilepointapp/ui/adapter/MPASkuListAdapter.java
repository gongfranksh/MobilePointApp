package personal.wl.mobilepointapp.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
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

import personal.wl.mobilepointapp.BR;
import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.base.BaseDataBindingAdapter;
import personal.wl.mobilepointapp.entity.ProductPresenter;
import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.utils.ToastUtil;

import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CLICK_ITEM_VIEW;

public class MPASkuListAdapter extends BaseQuickAdapter<Product, MPASkuListAdapter.ProductViewHolder> implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener {


    private TextView proname;
    private TextView sales;
    private ProductPresenter productPresenter;

    public MPASkuListAdapter(int layoutResId, List<Product> data) {
        super(layoutResId, data);
        productPresenter = new ProductPresenter();

    }

    @Override
    protected void convert(ProductViewHolder helper, Product item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.product,item);
        binding.setVariable(BR.productpresenter,productPresenter);

        helper.addOnClickListener(R.id.iv_num_add).addOnClickListener(R.id.iv_num_reduce);
        binding.executePendingBindings();
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtil.show(mContext,"onItemChildClick"+position);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        ToastUtil.show(mContext,"itemclick点击了"+position);

    }



    public static class ProductViewHolder extends BaseViewHolder{

        public ProductViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding(){

            return (ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }
}

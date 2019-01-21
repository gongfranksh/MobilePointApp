package personal.wl.mobilepointapp.ui.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import personal.wl.mobilepointapp.BR;
import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.entity.pos.SaleDaily;

public class MPASaleOrderListAdapter extends BaseQuickAdapter<SaleDaily, MPASaleOrderListAdapter.SaleOrderViewHolder>
        implements BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener

{

    public MPASaleOrderListAdapter(int layoutResId, @Nullable List<SaleDaily> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(SaleOrderViewHolder helper, SaleDaily item) {
        ViewDataBinding binding = helper.getBinding();
        binding.setVariable(BR.saleorder,item);
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
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

    }


    public static class SaleOrderViewHolder extends BaseViewHolder {
        public SaleOrderViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {

            return (android.databinding.ViewDataBinding) itemView.getTag(R.id.BaseQuickAdapter_databinding_support);
        }


    }
}

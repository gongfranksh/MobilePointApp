package personal.wl.mobilepointapp.ui.adapter;

import android.util.Log;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.entity.MySection;
import personal.wl.mobilepointapp.entity.Video;
import personal.wl.mobilepointapp.entity.menufunction.MPAFunction;
import personal.wl.mobilepointapp.entity.menufunction.MPASection;

import static personal.wl.mobilepointapp.common.MobilePointApplication.appContext;
import static personal.wl.mobilepointapp.utils.MPAResourceUtils.getImageResid;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MPAFunctionAdapter extends BaseSectionQuickAdapter<MPASection, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param sectionHeadResId The section head layout id for each item
     * @param layoutResId      The layout resource id of each item.
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public MPAFunctionAdapter(int layoutResId, int sectionHeadResId, List data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, final MPASection item) {
        helper.setText(R.id.header, item.header);
        helper.setVisible(R.id.more, item.isMore());
        helper.addOnClickListener(R.id.more);
    }


    @Override
    protected void convert(BaseViewHolder helper, MPASection item) {
        MPAFunction function = (MPAFunction) item.t;
        helper.setText(R.id.tv,function.getFunctionName());
        Log.i(TAG, "convert:" + function.getFunctionId().toString());

        if (function.getIconName().toString().isEmpty()) {
            helper.setImageResource(R.id.iv, R.drawable.ic_not_build);
        } else {
            helper.setImageResource(R.id.iv, getImageResid(function.getIconName().toString()));
        }
    }

}

package personal.wl.mobilepointapp.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import personal.wl.mobilepointapp.BR;
import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.entity.pos.BranchEmployee;

import static personal.wl.mobilepointapp.utils.MPAStringUtils.getAlpha;

public class MPAOperatorResultAdapter extends RecyclerView.Adapter<MPAOperatorResultAdapter.OperatorResultViewHolder>

{
    private Context context;
    private List<BranchEmployee> operatorlists;


    public MPAOperatorResultAdapter(Context context, List<BranchEmployee> data) {
        this.context = context;
        this.operatorlists = data;
   }


    @NonNull
    @Override
    public OperatorResultViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(viewGroup.getContext()), R.layout.item_result_operator, viewGroup, false);
        OperatorResultViewHolder holder = new OperatorResultViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OperatorResultViewHolder operatorResultViewHolder, int i) {
        operatorResultViewHolder.getBinding().setVariable(BR.SearchOperator, operatorlists.get(i));
        operatorResultViewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        if (operatorlists != null) {
            return operatorlists.size();
        } else {
            return 0;
        }
    }


  public  class OperatorResultViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;


        public OperatorResultViewHolder(View view) {
            super(view);

        }

        public ViewDataBinding getBinding() {

            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }


    }

}

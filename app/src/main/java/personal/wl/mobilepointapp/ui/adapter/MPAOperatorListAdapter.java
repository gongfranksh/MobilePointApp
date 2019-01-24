package personal.wl.mobilepointapp.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import personal.wl.mobilepointapp.BR;
import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.entity.pos.BranchEmployee;
import personal.wl.mobilepointapp.entity.pos.PayMent;
import personal.wl.mobilepointapp.utils.MPAJudgeNumberWatcher;
import personal.wl.mobilepointapp.utils.MPAStringUtils;

import static personal.wl.mobilepointapp.utils.MPAStringUtils.getAlpha;

public class MPAOperatorListAdapter extends RecyclerView.Adapter<MPAOperatorListAdapter.OperatorViewHolder>

{
    private TextView proname;
    private TextView sales;
    private Context context;
    private List<BranchEmployee> branchEmployeeList;
    public HashMap<String,Integer> alphaIndexer;
    private String[] sections;


    public MPAOperatorListAdapter(Context context, List<BranchEmployee> data) {

        this.context = context;
        this.branchEmployeeList = data;

        alphaIndexer = new HashMap<>();
        sections = new String[data.size()];

        for (int i = 0; i < data.size(); i++) {

            String currentStr = getAlpha(data.get(i).getPinyin());

            String previewStr = (i - 1) >= 0 ? getAlpha(data.get(i - 1).getPinyin()) : " ";
            if (!previewStr.equals(currentStr)) {
                String name = getAlpha(data.get(i).getPinyin());
                alphaIndexer.put(name, i);
                sections[i] = name;

            }
        }
    }

    @NonNull
    @Override
    public OperatorViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(viewGroup.getContext()), R.layout.item_databinding_all_operator, viewGroup, false);
        OperatorViewHolder holder = new OperatorViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull OperatorViewHolder operatorViewHolder, final int i) {

        operatorViewHolder.getBinding().setVariable(BR.operator, branchEmployeeList.get(i));
        operatorViewHolder.getBinding().executePendingBindings();
        String currentStr = getAlpha(branchEmployeeList.get(i).getPinyin());
        String previewStr = i-1 >= 0 ?
                getAlpha(branchEmployeeList.get(i-1).getPinyin()) : "";
//        if (!previewStr.equals(currentStr)) {
//            operatorViewHolder.alphaLayout.setVisibility(View.VISIBLE);
//            operatorViewHolder.tvAlpha.setText(currentStr);
//        } else {
//            operatorViewHolder.alphaLayout.setVisibility(View.GONE);
//        }
    }


    @Override
    public int getItemCount() {
        if (branchEmployeeList != null) {
            return branchEmployeeList.size();
        } else {
            return 0;
        }
    }


    public  class OperatorViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;
        private EditText et_paymoney;

        public LinearLayout alphaLayout;
        public TextView tvAlpha;

        public OperatorViewHolder(View view) {
            super(view);
            alphaLayout=view.findViewById(R.id.item_all_operator_alpha_layout);
            tvAlpha = (TextView) itemView.findViewById(R.id.item_all_city_tv_alpha);
        }

        public ViewDataBinding getBinding() {

            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }


    }

}

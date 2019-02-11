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
import personal.wl.mobilepointapp.entity.pos.Branch;
import personal.wl.mobilepointapp.entity.pos.BranchEmployee;
import personal.wl.mobilepointapp.listener.OnItemClickListener;

import static personal.wl.mobilepointapp.utils.MPAStringUtils.getAlpha;

public class MPABranchListAdapter extends RecyclerView.Adapter<MPABranchListAdapter.BranchViewHolder>

{
    private TextView proname;
    private TextView sales;
    private Context context;
    private List<Branch> branches;
    public HashMap<String,Integer> alphaIndexer;
    private String[] sections;

    public void setmClickListener(OnItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    private OnItemClickListener mClickListener;

    public MPABranchListAdapter(Context context, List<Branch> data) {

        this.context = context;
        this.branches = data;

        alphaIndexer = new HashMap<>();
        sections = new String[data.size()];

        for (int i = 0; i < data.size(); i++) {

            String currentStr = getAlpha(data.get(i).getPinYin());

            String previewStr = (i - 1) >= 0 ? getAlpha(data.get(i - 1).getPinYin()) : " ";
            if (!previewStr.equals(currentStr)) {
                String name = getAlpha(data.get(i).getPinYin());
                alphaIndexer.put(name, i);
                sections[i] = name;

            }
        }

        Log.i("dd", "MPAOperatorListAdapter: "+sections.toString());

    }

    @NonNull
    @Override
    public BranchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(viewGroup.getContext()), R.layout.item_databinding_branch, viewGroup, false);
        BranchViewHolder holder = new BranchViewHolder(binding.getRoot(),mClickListener);
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull BranchViewHolder branchViewHolder, final int i) {

        branchViewHolder.getBinding().setVariable(BR.branch, branches.get(i));
        branchViewHolder.getBinding().executePendingBindings();
        String currentStr = getAlpha(branches.get(i).getPinYin());
        String previewStr = i-1 >= 0 ?
                getAlpha(branches.get(i-1).getPinYin()) : "";



        if (!previewStr.equals(currentStr)) {
            branchViewHolder.alphaLayout.setVisibility(View.VISIBLE);
            branchViewHolder.tvAlpha.setText(currentStr);
        } else {
            branchViewHolder.alphaLayout.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        if (branches != null) {
            return branches.size();
        } else {
            return 0;
        }
    }


    public  class BranchViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        private ViewDataBinding binding;
        public LinearLayout alphaLayout;
        public TextView tvAlpha;
        private OnItemClickListener mClickListener;

        public BranchViewHolder(View view,OnItemClickListener listener) {
            super(view);
            alphaLayout=view.findViewById(R.id.item_all_branch_alpha_layout);
            tvAlpha =  view.findViewById(R.id.item_all_branch_tv_alpha);
            mClickListener=listener;
            view.setOnClickListener(this);

        }

        public ViewDataBinding getBinding() {

            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }



        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(v,getPosition());
        }
    }

}

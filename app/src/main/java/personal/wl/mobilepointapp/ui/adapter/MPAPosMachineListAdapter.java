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
import personal.wl.mobilepointapp.entity.pos.PosMachine;
import personal.wl.mobilepointapp.listener.OnItemClickListener;

import static personal.wl.mobilepointapp.utils.MPAStringUtils.getAlpha;

public class MPAPosMachineListAdapter extends RecyclerView.Adapter<MPAPosMachineListAdapter.PosMachineViewHolder>

{
    private TextView proname;
    private TextView sales;
    private Context context;
    private List<PosMachine> posMachines;
    public HashMap<String, Integer> alphaIndexer;
    private String[] sections;

    public void setmClickListener(OnItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    private OnItemClickListener mClickListener;

    public MPAPosMachineListAdapter(Context context, List<PosMachine> data) {

        this.context = context;
        this.posMachines = data;

    }

    @NonNull
    @Override
    public PosMachineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(viewGroup.getContext()), R.layout.item_databinding_posmachine, viewGroup, false);
        PosMachineViewHolder holder = new PosMachineViewHolder(binding.getRoot(), mClickListener);
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PosMachineViewHolder branchViewHolder, final int i) {

        branchViewHolder.getBinding().setVariable(BR.posmachine, posMachines.get(i));
        branchViewHolder.getBinding().executePendingBindings();
    }


    @Override
    public int getItemCount() {
        if (posMachines != null) {
            return posMachines.size();
        } else {
            return 0;
        }
    }


    public class PosMachineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ViewDataBinding binding;
        public LinearLayout alphaLayout;
        public TextView tvAlpha;
        private OnItemClickListener mClickListener;

        public PosMachineViewHolder(View view, OnItemClickListener listener) {
            super(view);
//            alphaLayout = view.findViewById(R.id.item_all_branch_alpha_layout);
//            tvAlpha = view.findViewById(R.id.item_all_branch_tv_alpha);
            mClickListener = listener;
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
            mClickListener.onItemClick(v, getPosition());
        }
    }

}

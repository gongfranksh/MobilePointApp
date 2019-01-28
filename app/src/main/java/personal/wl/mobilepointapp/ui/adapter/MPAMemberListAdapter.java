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
import personal.wl.mobilepointapp.entity.pos.Member;
import personal.wl.mobilepointapp.listener.OnItemClickListener;

import static personal.wl.mobilepointapp.utils.MPAStringUtils.getAlpha;

public class MPAMemberListAdapter extends RecyclerView.Adapter<MPAMemberListAdapter.MemberViewHolder>

{
    private Context context;
    private List<Member> memberList;

    public void setmClickListener(OnItemClickListener mClickListener) {
        this.mClickListener = mClickListener;
    }

    private OnItemClickListener mClickListener;

    public MPAMemberListAdapter(Context context, List<Member> data) {

        this.context = context;
        this.memberList = data;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(viewGroup.getContext()), R.layout.item_databinding_member, viewGroup, false);
        MemberViewHolder holder = new MemberViewHolder(binding.getRoot(),mClickListener);
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder memberViewHolder, final int i) {
        memberViewHolder.getBinding().setVariable(BR.member, memberList.get(i));
        memberViewHolder.getBinding().executePendingBindings();
    }


    @Override
    public int getItemCount() {
        if (memberList != null) {
            return memberList.size();
        } else {
            return 0;
        }
    }


    public  class MemberViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        private ViewDataBinding binding;
        private OnItemClickListener mClickListener;

        public MemberViewHolder(View view,OnItemClickListener listener) {
            super(view);
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

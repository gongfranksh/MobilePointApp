package personal.wl.mobilepointapp.ui.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.lang.reflect.GenericArrayType;
import java.util.List;

import personal.wl.mobilepointapp.BR;
import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.entity.pos.PayMent;

import personal.wl.mobilepointapp.utils.MPAGenericTextWatch;
import personal.wl.mobilepointapp.utils.MPAJudgeNumberWatcher;
import personal.wl.mobilepointapp.utils.ToastUtil;

public class MPAPaymentListAdapter extends RecyclerView.Adapter<MPAPaymentListAdapter.PaymentViewHolder>

{
    private TextView proname;
    private TextView sales;
    private Context context;
    private List<PayMent> payMentList;

    public MPAPaymentListAdapter(Context context, List<PayMent> data) {

        this.context = context;
        this.payMentList = data;
    }


    @NonNull
    @Override
    public PaymentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(viewGroup.getContext()), R.layout.item_databing_payment_list, viewGroup, false);
        PaymentViewHolder holder = new PaymentViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PaymentViewHolder paymentViewHolder, final int i) {

        paymentViewHolder.getBinding().setVariable(BR.payment, payMentList.get(i));
        paymentViewHolder.getBinding().executePendingBindings();

    }


    @Override
    public int getItemCount() {
        if (payMentList != null) {
            return payMentList.size();
        } else {
            return 0;
        }
    }


    public class PaymentViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;
        private EditText et_paymoney;

        public PaymentViewHolder(View view) {
            super(view);

            et_paymoney = view.findViewById(R.id.item_payment_amount);
            et_paymoney.addTextChangedListener(new MPAJudgeNumberWatcher(et_paymoney));
            et_paymoney.setGravity(Gravity.RIGHT );

//            et_paymoney.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                et_paymoney.setTextDirection(View.TEXT_DIRECTION_FIRST_STRONG_RTL);
//            }
//
        }

        public ViewDataBinding getBinding() {

            return binding;
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }


    }



}

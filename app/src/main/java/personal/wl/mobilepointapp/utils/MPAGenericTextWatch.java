package personal.wl.mobilepointapp.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import personal.wl.mobilepointapp.R;
import personal.wl.mobilepointapp.ui.adapter.PayMoneyChange;

public class MPAGenericTextWatch implements TextWatcher {

    private View view;

    public MPAGenericTextWatch(View view) {
        this.view = view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        switch (view.getId()) {
            case R.id.item_payment_amount:
                PayMoneyChange.getInstance().notifyDataChange(s.toString());
                break;

        }

    }
}
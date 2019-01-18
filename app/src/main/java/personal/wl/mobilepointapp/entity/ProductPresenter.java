package personal.wl.mobilepointapp.entity;

import android.view.View;

import personal.wl.mobilepointapp.entity.pos.Product;
import personal.wl.mobilepointapp.utils.ToastUtil;

public class ProductPresenter {
    public void buy(View view, Product item){

        ToastUtil.show(view.getContext(),item.getProName());

    }
}

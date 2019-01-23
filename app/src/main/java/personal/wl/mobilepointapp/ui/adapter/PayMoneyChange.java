package personal.wl.mobilepointapp.ui.adapter;

import android.widget.EditText;

import java.util.Observable;

import personal.wl.mobilepointapp.entity.pos.PayMent;


public class PayMoneyChange extends Observable {
    private static PayMoneyChange instance = null;

    public static PayMoneyChange getInstance() {
        if (null == instance) {
            instance = new PayMoneyChange();
        }
        return instance;
    } //通知观察者数据改变
    public void notifyDataChange(PayMent payMent) {
        //被观察者怎么通知观察者数据有改变了呢？？这里的两个方法是关键。
        setChanged();
        notifyObservers(payMent);
    }
    public void notifyDataChange(String total) {
        //被观察者怎么通知观察者数据有改变了呢？？这里的两个方法是关键。
        setChanged();
        notifyObservers(total);
    }
}

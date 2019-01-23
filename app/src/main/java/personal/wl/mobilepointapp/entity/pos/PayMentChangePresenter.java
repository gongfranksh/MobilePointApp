package personal.wl.mobilepointapp.entity.pos;

import android.text.Editable;
import android.util.Log;
import android.view.View;


public class PayMentChangePresenter {

    private static  String TAG="PayMentChangePresenter";
    public void InputMoney(View view, PayMent  payMent){
        Log.i(TAG, "InputMoney: ");
    }
    public void Change(Editable s){
        Log.i(TAG, "Change: "+s.toString());
    }

}

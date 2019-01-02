package personal.wl.mobilepointapp.listener;



import java.util.List;

import cn.bmob.v3.exception.BmobException;
import personal.wl.mobilepointapp.model.BaseModel;

/**
 * Created by asus on 2016/9/11.
 */
public abstract class BmobSignUpCallback implements IBmobListener {
    @Override
    public void onMsgSendSuccess() {

    }

    @Override
    public void onMsgSendFailure() {

    }

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure() {

    }

    @Override
    public void onQuerySuccess(List<? extends BaseModel> dataList) {

    }

    @Override
    public void onQueryFailure(BmobException e) {

    }
}

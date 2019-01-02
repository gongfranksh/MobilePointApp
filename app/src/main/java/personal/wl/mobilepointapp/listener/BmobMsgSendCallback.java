package personal.wl.mobilepointapp.listener;


import java.util.List;

import cn.bmob.v3.exception.BmobException;
import personal.wl.mobilepointapp.model.BaseModel;
import personal.wl.mobilepointapp.model.User;

/**
 * Created by asus on 2016/9/10.
 */
public abstract class BmobMsgSendCallback implements IBmobListener {

    @Override
    public void onLoginSuccess() {

    }

    @Override
    public void onLoginFailure() {

    }

    @Override
    public void onSignUpSuccess(User user) {

    }

    @Override
    public void onSignUpFailure(BmobException e) {

    }

    @Override
    public void onQuerySuccess(List<? extends BaseModel> dataList) {

    }

    @Override
    public void onQueryFailure(BmobException e) {

    }
}

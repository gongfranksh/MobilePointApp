package personal.wl.mobilepointapp.preference.CurrentUser;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Date;

import personal.wl.mobilepointapp.auth.ldap.User;

import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CURRENT_ACCOUNT;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CURRENT_LOGIN;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.DEFAULT_CURRENT_ACCOUNT;

public class MPALoginInfo {
    private SharedPreferences sPre;
    private Context context;
    private Gson gon;

    public User getUser() {
        String struser = sPre.getString(CURRENT_ACCOUNT, null);
        user=gon.fromJson(struser,User.class);
        return user;
    }

    public void setUser(User user) {
        sPre.edit().putString(CURRENT_ACCOUNT,  gon.toJson(user)).commit();

    }

    private User user;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }


    public MPALoginInfo(Context context) {
        this.context = context;
        gon = new Gson();
        this.sPre = sPre;
        sPre = context.getSharedPreferences(
                CURRENT_LOGIN, 0);
    }
}

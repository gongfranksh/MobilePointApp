package personal.wl.mobilepointapp.preference.CurrentUser;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Date;

import personal.wl.mobilepointapp.auth.ldap.User;
import personal.wl.mobilepointapp.utils.MPADateUtils;

import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CURRENT_ACCOUNT;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CURRENT_LOGIN;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.CURRENT_LOGIN_DATE;
import static personal.wl.mobilepointapp.preference.SystemSettingConstant.DEFAULT_CURRENT_ACCOUNT;

public class MPALoginInfo {

    private static final MPALoginInfo instance = new MPALoginInfo();

    private SharedPreferences sPre;
    private Context context;
    private Gson gon;


    public static MPALoginInfo getInstance() {
        return instance;
    }


    public User getUser() {
        String struser = sPre.getString(CURRENT_ACCOUNT, null);
        user = gon.fromJson(struser, User.class);
        Date logdate = MPADateUtils.StrToDate(sPre.getString(CURRENT_LOGIN_DATE, "2000-01-01 01:01:01"));
        Date currentdate = new Date(System.currentTimeMillis());
        long diff = MPADateUtils.DateDiff(logdate, currentdate);
        //超过三天重新登陆
        if (diff >= 3) {
            return null;
        }
        return user;
    }

    public void setUser(User user) {
        sPre.edit().putString(CURRENT_ACCOUNT, gon.toJson(user)).commit();
        sPre.edit().putString(CURRENT_LOGIN_DATE, MPADateUtils.DateToStr(new Date(System.currentTimeMillis()))).commit();

    }


    public void logout() {
        User u = null;
        this.setUser(u);

    }

    private User user;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
        gon = new Gson();
        this.sPre = sPre;
        sPre = context.getSharedPreferences(
                CURRENT_LOGIN, 0);
    }


    private MPALoginInfo() {

    }
}

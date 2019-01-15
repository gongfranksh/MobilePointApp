package personal.wl.mobilepointapp.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static personal.wl.mobilepointapp.common.MobilePointApplication.appContext;

public class MPAResourceUtils {

    public static int getImageResid(String ImageName) {
        int imgid;
        List<String> lists =  getSourceName(ImageName);
        String imagetype = lists.get(0).toString();
        String imagefile = lists.get(1).toString();
        imgid = appContext.getResources().getIdentifier(imagefile,imagetype,appContext.getPackageName());
        return imgid;
    }


    public static List<String> getSourceName(String txt) {
//        String txt="@mipmop/icon_home_18";
        String re1 = "(@)";    // Any Single Character 1
        String re2 = "((?:[a-z][a-z0-9_]*))";    // Variable Name 1
        String re3 = "(\\/)";    // Any Single Character 2
        String re4 = "((?:[a-z][a-z0-9_]*))";    // Variable Name 2
        List<String> list = new ArrayList<String>();

        Pattern p = Pattern.compile(re1 + re2 + re3 + re4, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher m = p.matcher(txt);
        if (m.find()) {
            String c1 = m.group(1);
            String var1 = m.group(2);
            String c2 = m.group(3);
            String var2 = m.group(4);
            list.add(m.group(2));
            list.add(m.group(4));
        }
        return list;
    }


}

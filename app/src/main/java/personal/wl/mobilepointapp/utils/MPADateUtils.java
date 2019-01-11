package personal.wl.mobilepointapp.utils;

import android.net.ParseException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MPADateUtils {


    public static String DateToStr(Date date) {

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String str = format.format(date);
            return str;
         }


    public static Date StrToDate(String str) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                 date = format.parse(str);
                } catch (ParseException e) {
                 e.printStackTrace();
                } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        return date;
         }

    public static long  DateDiff(Date day1,Date day2) {
        long daysBetween=(day2.getTime()-day1.getTime()+1000000)/(60*60*24*1000);
        return daysBetween;
    }



}

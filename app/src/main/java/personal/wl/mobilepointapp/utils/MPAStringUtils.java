package personal.wl.mobilepointapp.utils;

import android.util.Log;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.regex.Pattern;

public class MPAStringUtils {

    public static String getAlpha(String str) {
        if (str == null) {
            return "$";
        }
        if (str.trim().length() == 0) {
            return "$";
        }
        char c = str.trim().substring(0, 1).charAt(0);
        // 正则表达式，判断首字母是否是英文字母
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(c + "").matches()) {
            return (c + "").toUpperCase();
        } else if (str.equals(0 + "")) {
            return "$";
        } else if (str.equals(1 + "")) {
            return "#";
        } else if (str.equals(2 + "")) {
            return "*";
        }
        return "$";
    }

    //全拼
    public static String getPingYin(String src) {
        char[] t1 = null;
        t1 = src.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        String t4 = "";
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    t4 += t2[0];
                } else {
                    t4 += java.lang.Character.toString(t1[i]);
                }
            }
            return t4;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return t4;
    }

    /**
     * 得到中文首字母
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    /**
     * 将字符串转移为ASCII码
     *
     * @param cnStr
     * @return
     */
    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i]&0xff));
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }


    //定义一个处理字符串的方法
    public static String getMoneyString(String money){
        String overMoney = "";//结果
        String[] pointBoth = money.split("\\.");//分隔点前点后
        String beginOne = pointBoth[0].substring(pointBoth[0].length()-1);//前一位
        String endOne = pointBoth[1].substring(0, 1);//后一位
        //小数点前一位前面的字符串，小数点后一位后面
        String beginPoint = pointBoth[0].substring(0,pointBoth[0].length()-1);
        String endPoint = pointBoth[1].substring(1);
//        Log.e("Sun", pointBoth[0]+"==="+pointBoth[1] + "====" + beginOne + "=======" + endOne+"===>"+beginPoint+"=="+endPoint );
        //根据输入输出拼点
        if (pointBoth[1].length()>2){//说明输入，小数点要往右移
            overMoney=  pointBoth[0]+endOne+"."+endPoint;//拼接实现右移动
        }else if (
                pointBoth[1].length()<2){//说明回退,小数点左移
            overMoney = beginPoint+"."+beginOne+pointBoth[1];//拼接实现左移
        }else {
            overMoney = money;
        }
        //去除点前面的0 或者补 0
        String overLeft = overMoney.substring(0,overMoney.indexOf("."));//得到前面的字符串
//        Log.e("Sun","左邊:"+overLeft+"===去零前"+overMoney);
        if (overLeft ==null || overLeft == ""||overLeft.length()<1){//如果没有就补零
            overMoney = "0"+overMoney;
        }else if(overLeft.length() > 1 && "0".equals(overLeft.subSequence(0, 1))){//如果前面有俩个零
            overMoney = overMoney.substring(1);//去第一个0
        }
//        Log.e("Sun","結果:"+overMoney);
        return overMoney;
    }


}

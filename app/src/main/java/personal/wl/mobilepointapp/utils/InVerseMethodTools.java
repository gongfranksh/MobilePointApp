package personal.wl.mobilepointapp.utils;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.databinding.InverseBindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.text.Spanned;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;



public class InVerseMethodTools {
    @BindingAdapter("android:text")
    public static void setText(TextView view, double value) {
        if (view.getText() != null
                ) {
            view.setText(Double.toString(value));
        }

    }

    @InverseBindingAdapter(attribute = "android:text")
    public static Double getText(TextView view) {
        if (!view.getText().toString().isEmpty()) {
            String s = view.getText().toString().trim();
            s = s.replaceAll("\r|\n|\t", "");
            return Double.parseDouble(s);
        } else return 1.00;
    }



//    @BindingAdapter("android:text")
//    public static void setText(TextView view, CharSequence text) {
//        final CharSequence oldText = view.getText();
//        if (text == oldText || (text == null && oldText.length() == 0)) {
//            return;
//        }
//        if (text instanceof Spanned) {
//            if (text.equals(oldText)) {
//                return; // No change in the spans, so don't set anything.
//            }
//        } else {
//            if (text == oldText) {
//                return; // No content changes, so don't set anything.
//            }
//        }
//        view.setText(text);
//    }

    public static String DateToString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ", Locale.CHINA);
        return sdf.format(date);
    }

}

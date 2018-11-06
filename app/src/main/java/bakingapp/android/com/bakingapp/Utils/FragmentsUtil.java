package bakingapp.android.com.bakingapp.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;


public class FragmentsUtil {
    public  static  void addFragment(FragmentActivity activity , int layout, Fragment fragment, boolean addToBackStack){
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(layout,fragment);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }
    public static  void  replaceFragment(FragmentActivity activity , int layout, Fragment fragment, boolean addToBackStack){
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(layout,fragment);
        if(addToBackStack){
            transaction.addToBackStack(null);
        }
        transaction.commit();

    }
    public static  void  removeFragment(FragmentActivity activity , Fragment fragment){
        FragmentManager manager = activity.getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();

    }

    public  static void setSpannableString(String text , int color1 , int color2, TextView tv){
        SpannableString spannableString = new SpannableString(text);
        spannableString.setSpan(new ForegroundColorSpan(color1) , 0 , text.indexOf(" "), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(new ForegroundColorSpan(color2) , text.indexOf(" ") , text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(spannableString);
    }
}

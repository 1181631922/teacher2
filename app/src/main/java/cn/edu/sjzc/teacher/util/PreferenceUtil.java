package cn.edu.sjzc.teacher.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by 亚风 on 2015/05/16/0016.
 */
public class PreferenceUtil {
    public static void savePreference(Context context, String key, String value) {
        SharedPreferences preference = context.getSharedPreferences("All", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preference.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPreference(Context context, String key) {
        SharedPreferences preference = context.getSharedPreferences("All", Context.MODE_PRIVATE);
        return preference.getString(key, "");
    }
}

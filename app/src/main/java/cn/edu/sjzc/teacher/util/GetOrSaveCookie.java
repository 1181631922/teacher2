package cn.edu.sjzc.teacher.util;

import android.preference.PreferenceManager;


public class GetOrSaveCookie {

	/**
	 * 保存数据到sp
	 * 
	 * @param key
	 * @param value
	 */
	public static void savePreference(String key, String value) {
		PreferenceManager.getDefaultSharedPreferences(AppUtil.MyCookie).edit()
				.putString(key, value).commit();
	}

	/**
	 * �?
	 * 
	 * @param mContext
	 * @param key
	 * @return
	 */
	public static String getPreference(String key) {
		return PreferenceManager.getDefaultSharedPreferences(AppUtil.MyCookie)
				.getString(key, "");
	}
}

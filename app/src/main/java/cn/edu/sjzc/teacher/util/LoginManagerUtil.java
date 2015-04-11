package cn.edu.sjzc.teacher.util;

import android.text.TextUtils;
import android.util.Log;

import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;


public class LoginManagerUtil {

	private void getCookie(DefaultHttpClient httpClient) {
		
		
		 List<Cookie> cookies = httpClient.getCookieStore().getCookies();
		 
		 
		 StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cookies.size(); i++) {
			 Cookie cookie = cookies.get(i);
			 String cookieName = cookie.getName();
			 String cookieValue = cookie.getValue();
			if (!TextUtils.isEmpty(cookieName)
					&& !TextUtils.isEmpty(cookieValue)) {
				sb.append(cookieName + "=");
				sb.append(cookieValue + ";");
			}
		}
		Log.e("cookie", sb.toString());
		GetOrSaveCookie.savePreference("cookie", sb.toString());
	}
	
}

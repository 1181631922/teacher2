package cn.edu.sjzc.teacher.uiFragment;

import android.support.v4.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseFragment extends Fragment {

	private int index;
    public static String aBaseUrl="http://172.28.214.3:8080/st/";
    public static String baseUrl = aBaseUrl+"person!";

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

    public static String getMilliToDate(String time){
        Date date = new Date(Long.valueOf(time));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }
}

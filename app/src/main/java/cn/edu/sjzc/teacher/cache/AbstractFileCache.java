package cn.edu.sjzc.teacher.cache;

import java.io.File;
import android.content.Context;
import android.util.Log;

import cn.edu.sjzc.teacher.util.FileHelperUtil;

public abstract class AbstractFileCache {

	private String dirString;

	public AbstractFileCache(Context context) {

		dirString = getCacheDir();
		boolean ret = FileHelperUtil.createDirectory(dirString);
	}

	public File getFile(String url) {
		File f = new File(getSavePath(url));
		return f;
	}

	public abstract String getSavePath(String url);

	public abstract String getCacheDir();

	public void clear() {
		FileHelperUtil.deleteDirectory(dirString);
	}

}

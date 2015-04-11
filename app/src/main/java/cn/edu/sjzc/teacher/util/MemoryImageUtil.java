package cn.edu.sjzc.teacher.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

public class MemoryImageUtil {
	// 获取当前应用程序�?��配的�?��内存
	private final int maxMemory = (int) Runtime.getRuntime().maxMemory();
	// 只用五分之一用来做图片缓�?
	private final int cacheSize = maxMemory / 5;

	private LruCache<String, Bitmap> mLruCache = new LruCache<String, Bitmap>(
			cacheSize) {

		//重写sizeof（）方法
		@Override
		protected int sizeOf(String key, Bitmap bitmap) {
			// TODO Auto-generated method stub
			//这里用多少kb来计�?
			return bitmap.getRowBytes()*bitmap.getHeight()/1024;
		}

	};
}

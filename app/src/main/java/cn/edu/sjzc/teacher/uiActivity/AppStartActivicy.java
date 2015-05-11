package cn.edu.sjzc.teacher.uiActivity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.view.Window;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.uiFragment.MainTabActivity;

public class AppStartActivicy extends BaseActivity {
    private Thread thread;
    private final static String ALBUM_PATH = Environment.getExternalStorageDirectory()
            + "/fanyafeng/";
    private String mFileName;
    private String mSaveMessage;
    private Bitmap mBitmap;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE); //ȥ��������
        setContentView(R.layout.activity_appstart); // �󶨲����ļ�

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AppStartActivicy.this, MainTabActivity.class);
                startActivity(intent);
                AppStartActivicy.this.finish();
            }
        }, 1000);


    }

    @Override
    protected void onStart() {
        super.onStart();
//        CheckNetworkState();
    }

    public void CheckNetworkState() {
        boolean flag = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo.State mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                .getState();
        NetworkInfo.State wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                .getState();
        // 如果3G、wifi�?G等网络状态是连接的，则�?出，否则显示提示信息进入网络设置界面
        if (mobile == NetworkInfo.State.CONNECTED || mobile == NetworkInfo.State.CONNECTING || wifi == NetworkInfo.State.CONNECTED || wifi == NetworkInfo.State.CONNECTING) {
            new Thread(connectNet).start();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    new Thread(saveFileRunnable).start();
                }
            }, 5000);
            return;
        }

        showTips();
    }

    private void showTips() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("没有可用网络");
        builder.setMessage("当前网络不可用，是否设置网络�?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 如果没有网络连接，则进入网络设置界面
                startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                AppStartActivicy.this.finish();
            }
        });
        builder.create();
        builder.show();
    }

    public byte[] getImage(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        InputStream inStream = conn.getInputStream();
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return readStream(inStream);
        }
        return null;
    }

    public InputStream getImageStream(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5 * 1000);
        conn.setRequestMethod("GET");
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
            return conn.getInputStream();
        }
        return null;
    }

    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }


    public void saveFile(Bitmap bm, String fileName) throws IOException {
        File dirFile = new File(ALBUM_PATH);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File myCaptureFile = new File(ALBUM_PATH + fileName);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(myCaptureFile));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

    private Runnable saveFileRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                saveFile(mBitmap, mFileName);
                mSaveMessage = "图片保存成功！";
            } catch (IOException e) {
                mSaveMessage = "图片保存失败！";
                e.printStackTrace();
            }
//            messageHandler.sendMessage(messageHandler.obtainMessage());
        }
    };

    private Runnable connectNet = new Runnable() {
        @Override
        public void run() {
            try {
                String filePath = "http://a3.qpic.cn/psb?/V12cYG6y4OfMYd/dK6ZILGMFxATylOpn2TRhrNUK8.QkLM4M.0TSzBSuJ0!/m/dB4AAAAAAAAAnull&bo=TgG.AQAAAAAFB9Q!&rf=photolist&t=5";
                mFileName = "fanyafeng.png";

                // 以下是取得图片的两种方法
                // ////////////// 方法1：取得的是byte数组, 从byte数组生成bitmap
                byte[] data = getImage(filePath);
                if (data != null) {
                    mBitmap = BitmapFactory.decodeByteArray(data, 0,
                            data.length);// bitmap
                } else {
                    Toast.makeText(AppStartActivicy.this, "Image error!", 1)
                            .show();
                }
                // ******** 方法2：取得的是InputStream，直接从InputStream生成bitmap
                mBitmap = BitmapFactory.decodeStream(getImageStream(filePath));
                // 发送消息，通知handler在主线程中更新UI
            } catch (Exception e) {
//                Toast.makeText(AppStartActivicy.this, "无法链接网络！", 1).show();
                e.printStackTrace();
            }
        }
    };


}

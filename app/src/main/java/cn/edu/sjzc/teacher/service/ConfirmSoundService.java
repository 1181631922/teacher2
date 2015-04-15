package cn.edu.sjzc.teacher.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import cn.edu.sjzc.teacher.R;

/**
 * Created by fanyafeng on 2015/4/15/0015.
 */
public class ConfirmSoundService extends Service {
    /**
     * ConfirmPlayer 定义一个媒体播放对象
     * ConfirmPlayerBinder 用于绑定服务，但在此注意解绑前必须先要进行绑定
     */
    private MediaPlayer ConfirmPlayer;
    private MyBinder ConfirmPlayerBinder = new MyBinder();

    @Override
    public void onCreate() {
        ConfirmPlayer = MediaPlayer.create(getApplicationContext(), R.raw.confirm);

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        ConfirmPlayer.stop();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return ConfirmPlayerBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    public class MyBinder extends Binder {
        ConfirmSoundService getService() {
            return ConfirmSoundService.this;
        }
    }
}

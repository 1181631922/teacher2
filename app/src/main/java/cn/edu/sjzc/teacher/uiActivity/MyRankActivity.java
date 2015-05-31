package cn.edu.sjzc.teacher.uiActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.adapter.ScheduleAdapter;
import cn.edu.sjzc.teacher.app.UserApplication;
import cn.edu.sjzc.teacher.util.PostUtil;

public class MyRankActivity extends BaseActivity {
    private TextView advice_teacher_rank, advice_teacher_score;
    private ImageButton advice_rank_back;
    private ProgressBar course_rank_progress;
    private String number, rank, teacher_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_rank);
        initView();
        initData();
        course_rank_progress.setVisibility(View.VISIBLE);
        Thread loadThread = new Thread(new LoadThread());
        loadThread.start();
    }

    private void initView() {
        advice_teacher_rank = (TextView) findViewById(R.id.advice_teacher_rank);
        advice_teacher_score = (TextView) findViewById(R.id.advice_teacher_score);
        advice_rank_back = (ImageButton) findViewById(R.id.advice_rank_back);
        advice_rank_back.setOnClickListener(this);
        course_rank_progress = (ProgressBar) findViewById(R.id.course_rank_progress);
    }

    private void initData() {
        SharedPreferences userdata = this.getSharedPreferences(UserApplication.USER_DATA, 0);
        number = userdata.getString(UserApplication.USER_DATA_NUMBER, "");
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    updateUI();
                    break;
            }
        }
    };

    private void updateUI() {
        course_rank_progress.setVisibility(View.GONE);
        advice_teacher_rank.setText(rank);
        advice_teacher_score.setText(teacher_result);
    }
    class LoadThread implements Runnable {
        @Override
        public void run() {
            changePassword();
        }
    }
    private void changePassword() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("number", number);
        try {
            String backMsg = PostUtil.postData(aBaseUrl + "result!showResultAndroid", map);
            Log.d("-------changepassword-----------", backMsg);
            try {
                JSONObject jsonObject = new JSONObject(backMsg);
                rank = jsonObject.getString("rank");
                teacher_result = jsonObject.getString("teacher_result");
                Message message2 = Message.obtain();
                message2.what = 1;
                handler.sendMessage(message2);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.advice_rank_back:
                finish();
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_rank, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

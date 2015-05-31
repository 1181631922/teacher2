package cn.edu.sjzc.teacher.uiActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.app.UserApplication;
import cn.edu.sjzc.teacher.dialog.ConfirmDialog;
import cn.edu.sjzc.teacher.util.PostUtil;

public class AdviceTeacherActivity extends BaseActivity {
    private ImageButton advice_teacher_back;
    private TextView advice_teacher_name, advice_teacher_evaluation;
    private Button advice_teacher_cancle, advice_teacher_submit;
    private String student_name;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_teacher);
        initView();
        initData();
    }

    private void initView() {
        advice_teacher_back = (ImageButton) findViewById(R.id.advice_teacher_back);
        advice_teacher_back.setOnClickListener(this);
        advice_teacher_name = (TextView) findViewById(R.id.advice_teacher_name);
        advice_teacher_evaluation = (TextView) findViewById(R.id.advice_teacher_evaluation);
    }

    private void initData() {
        Intent intent = this.getIntent();
        student_name = intent.getStringExtra("student_name");
        message = intent.getStringExtra("message");
        advice_teacher_name.setText(student_name);
        advice_teacher_evaluation.setText(message);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.advice_teacher_back:
                finish();
                break;
        }
    }


    public void ConfirmExit() {
        ConfirmDialog confirmDialog = new ConfirmDialog(this, R.style.mystyle, R.layout.dialog_confirm);
        confirmDialog.show();
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

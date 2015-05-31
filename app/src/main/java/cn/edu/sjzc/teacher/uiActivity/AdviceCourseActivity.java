package cn.edu.sjzc.teacher.uiActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import cn.edu.sjzc.teacher.R;

public class AdviceCourseActivity extends BaseActivity {
    private Button button1;
    private TextView advice_one_title;
    private RadioGroup radiogroup_one;
    private RadioButton advice_onegroup, advice_twogroup, advice_threegroup, advice_fourgroup, advice_fivegroup;
    private LinearLayout layout_one, layout_two;
    private String teacher_name, coursename, score, courseId;
    private ImageButton advice_course_back;
    private TextView advice_course_name, advice_course_evaluation, advice_course_score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_course);
        initView();
        initData();
    }

    private void initView() {
        Intent intent = this.getIntent();
        teacher_name = intent.getStringExtra("teacher_name");
        coursename = intent.getStringExtra("coursename");
        score = intent.getStringExtra("score");

        advice_course_name = (TextView) findViewById(R.id.advice_course_name);
        advice_course_evaluation = (TextView) findViewById(R.id.advice_course_evaluation);
        advice_course_score = (TextView) findViewById(R.id.advice_course_score);
        advice_course_back = (ImageButton) findViewById(R.id.advice_course_back);
        advice_course_back.setOnClickListener(this);
    }

    private void initData() {
        advice_course_name.setText(teacher_name);
        advice_course_evaluation.setText(coursename);
        advice_course_score.setText(score);
    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.advice_course_back:
                finish();
                break;
        }
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

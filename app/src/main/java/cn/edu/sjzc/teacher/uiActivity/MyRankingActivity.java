package cn.edu.sjzc.teacher.uiActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.adapter.MyRankingAdapter;
import cn.edu.sjzc.teacher.adapter.TeacherRankingAdapter;
import cn.edu.sjzc.teacher.bean.MyRankingBean;
import cn.edu.sjzc.teacher.bean.TeacherRankingBean;
import cn.edu.sjzc.teacher.view.HorizontalListView;

public class MyRankingActivity extends BaseActivity implements View.OnClickListener {
    Context context;
    private HorizontalListView mListView;
    private MyRankingAdapter myRankingAdapter;
    private List<MyRankingBean> data;
    private String knowledgename[] = {"课程1", "课程2", "课程3", "课程4",
            "课程5", "课程6", "课程7", "课程8", "课程9", "课程10"};
    private int pencent[] = {13, 56, 78, 35, 57, 78, 45, 26, 44, 100};
    private TextView ranking_title, ranking_detail;
    private ImageButton rankging_back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_teacher_ranking);
        context = MyRankingActivity.this;
        viewInit();
        initView();
    }

    private void initView() {
        this.ranking_title = (TextView) MyRankingActivity.this.findViewById(R.id.ranking_title);
        this.ranking_detail = (TextView) MyRankingActivity.this.findViewById(R.id.ranking_detail);
        this.rankging_back = (ImageButton) MyRankingActivity.this.findViewById(R.id.rankging_back);
        this.rankging_back.setOnClickListener(this);
    }

    public void viewInit() {
        mListView = (HorizontalListView) findViewById(R.id.listview);
        myRankingAdapter = new MyRankingAdapter(context);
        data = new ArrayList<MyRankingBean>();

        for (int i = 0; i < knowledgename.length; i++) {
            MyRankingBean myRankingBean = new MyRankingBean();
            myRankingBean.setKnowledgename(knowledgename[i]);
            myRankingBean.setPencent(pencent[i]);

            data.add(myRankingBean);

        }
        myRankingAdapter.setData(data);
        mListView.setAdapter(myRankingAdapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rankging_back:
                finish();
                break;
        }
    }
}

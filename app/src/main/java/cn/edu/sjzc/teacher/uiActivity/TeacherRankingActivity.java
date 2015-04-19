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
import cn.edu.sjzc.teacher.adapter.TeacherRankingAdapter;
import cn.edu.sjzc.teacher.bean.TeacherRankingBean;
import cn.edu.sjzc.teacher.view.HorizontalListView;

public class TeacherRankingActivity extends BaseActivity implements View.OnClickListener{
	Context context;
	private HorizontalListView mListView;
	private TeacherRankingAdapter teacherRankingAdapter;
	private List<TeacherRankingBean> data;
	private String knowledgename[] = { "教师1", "教师2", "教师3", "教师4",
			"教师5", "教师6", "教师7", "教师8", "教师9" ,"教师10"};
	private int pencent[] = { 13, 56, 78, 35, 57, 78, 45, 26, 44 ,100};
    private TextView ranking_title, ranking_detail;
    private ImageButton rankging_back;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		setContentView(R.layout.activity_teacher_ranking);
		context = TeacherRankingActivity.this;
		viewInit();
        initView();
	}

    private void initView() {
        this.ranking_title = (TextView) TeacherRankingActivity.this.findViewById(R.id.ranking_title);
        this.ranking_detail = (TextView) TeacherRankingActivity.this.findViewById(R.id.ranking_detail);
        this.rankging_back = (ImageButton) TeacherRankingActivity.this.findViewById(R.id.rankging_back);
        this.rankging_back.setOnClickListener(this);
    }


    public void viewInit() {
		mListView = (HorizontalListView) findViewById(R.id.listview);
		teacherRankingAdapter = new TeacherRankingAdapter(context);
		data = new ArrayList<TeacherRankingBean>();

		for (int i = 0; i < knowledgename.length; i++) {
			TeacherRankingBean teacherRankingBean = new TeacherRankingBean();
			teacherRankingBean.setKnowledgename(knowledgename[i]);
			teacherRankingBean.setPencent(pencent[i]);
		
			data.add(teacherRankingBean);

		}
		teacherRankingAdapter.setData(data);
		mListView.setAdapter(teacherRankingAdapter);

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

package cn.edu.sjzc.teacher.uiActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.adapter.TeacherRankingAdapter;
import cn.edu.sjzc.teacher.bean.TeacherRankingBean;
import cn.edu.sjzc.teacher.view.HorizontalListView;

public class TeacherRankingActivity extends BaseActivity {
	Context context;
	private HorizontalListView mListView;
	private TeacherRankingAdapter teacherRankingAdapter;
	private List<TeacherRankingBean> data;
	private String knowledgename[] = { "��ʦ1", "��ʦ2", "��ʦ3", "��ʦ14",
			"��ʦ15", "��ʦ16", "��ʦ7", "��ʦ18", "��ʦ19" ,"��ʦ10"};
	private int pencent[] = { 13, 56, 78, 35, 57, 78, 45, 26, 44 ,69};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		setContentView(R.layout.activity_teacher_ranking);
		context = TeacherRankingActivity.this;
		viewInit();

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

}

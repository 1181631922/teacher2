package cn.edu.sjzc.teacher.uiActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.adapter.MyRankingAdapter;
import cn.edu.sjzc.teacher.adapter.TeacherRankingAdapter;
import cn.edu.sjzc.teacher.bean.MyRankingBean;
import cn.edu.sjzc.teacher.bean.TeacherRankingBean;
import cn.edu.sjzc.teacher.view.HorizontalListView;

public class MyRankingActivity extends BaseActivity {
	Context context;
	private HorizontalListView mListView;
	private MyRankingAdapter myRankingAdapter;
	private List<MyRankingBean> data;
	private String knowledgename[] = { "��ʦ1", "��ʦ2", "��ʦ3", "��ʦ14",
			"��ʦ15", "��ʦ16", "��ʦ7", "��ʦ18", "��ʦ19" ,"��ʦ10"};
	private int pencent[] = { 13, 56, 78, 35, 57, 78, 45, 26, 44 ,100};


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		setContentView(R.layout.activity_teacher_ranking);
		context = MyRankingActivity.this;
		viewInit();

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

}

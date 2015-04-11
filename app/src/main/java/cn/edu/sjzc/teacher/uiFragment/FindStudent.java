package cn.edu.sjzc.teacher.uiFragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.adapter.StudentAdapter;
import cn.edu.sjzc.teacher.bean.StudentUserBean;
import cn.edu.sjzc.teacher.uiActivity.AdvStudentInfoActivity;
import cn.edu.sjzc.teacher.util.PinyinUtils;
import cn.edu.sjzc.teacher.view.StudentSideBarView;
import cn.edu.sjzc.teacher.view.StudentSideBarView.OnTouchingLetterChangedListener;

public class FindStudent extends BaseFragment implements
		OnTouchingLetterChangedListener {
	
	public static BaseFragment newInstance(int index) {
		BaseFragment fragment = new FindStudent();
		Bundle args = new Bundle();
		args.putInt("index", index);
		fragment.setArguments(args);
		fragment.setIndex(index);
		return fragment;
	}

	private ListView lvShow;
	private List<StudentUserBean> studentUserBeans;
	private TextView overlay;
	private StudentSideBarView myView;
	private StudentAdapter adapter;
	private static List<Map<String, Object>> studentList = new ArrayList<Map<String, Object>>();
	private String sname, sphone;

	private OverlayThread overlayThread = new OverlayThread();

	// public String[] userinfoArray;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_findteacher, container,
				false);
		return root;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		init();

	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onTouchingLetterChanged(String s) {
		// TODO Auto-generated method stub
		Log.i("coder", "s:" + s);

		overlay.setText(s);
		overlay.setVisibility(View.VISIBLE);
		handler.removeCallbacks(overlayThread);
		handler.postDelayed(overlayThread, 1000);
		if (alphaIndexer(s) > 0) {
			int position = alphaIndexer(s);
			Log.i("coder", "position:" + position);
			lvShow.setSelection(position);

		}
	}

	private class OverlayThread implements Runnable {

		public void run() {
			overlay.setVisibility(View.GONE);
		}

	}

	public void init() {
		lvShow = (ListView) getActivity().findViewById(R.id.lvShow);
		myView = (StudentSideBarView) getActivity().findViewById(R.id.myView);

		overlay = (TextView) getActivity().findViewById(R.id.tvLetter);

		lvShow.setTextFilterEnabled(true);
		overlay.setVisibility(View.INVISIBLE);

		getUserInfos();

		Log.i("coder", "studentUserBeans.size" + studentUserBeans.size());
		adapter = new StudentAdapter(getActivity(), studentUserBeans);

		lvShow.setAdapter(adapter);

		myView.setOnTouchingLetterChangedListener(this);

		lvShow.setOnItemClickListener(new studentInfoOnItemClickListener());
	}

	public void getUserInfos() {
		StudentUserBean[] userinfoArray = new StudentUserBean[] {
                new StudentUserBean("韩冰", "13303116239",PinyinUtils.getAlpha("韩冰")),
                new StudentUserBean("张海春", "18765432345",PinyinUtils.getAlpha("张海春")),
                new StudentUserBean("及徐冰", "18765432345",PinyinUtils.getAlpha("及徐冰")),
                new StudentUserBean("邻里中", "18765432345",PinyinUtils.getAlpha("邻里中")),
                new StudentUserBean("宋红卫", "18765432345",PinyinUtils.getAlpha("宋红卫")),
                new StudentUserBean("何东滨", "18765432345",PinyinUtils.getAlpha("何东滨")),
                new StudentUserBean("刘絮凝", "18765432345",PinyinUtils.getAlpha("刘絮凝")),
                new StudentUserBean("王丽娜", "18765432345",PinyinUtils.getAlpha("王丽娜")),
                new StudentUserBean("长子李", "18765432345",PinyinUtils.getAlpha("长子李")),
                new StudentUserBean("孙警务", "18765432345",PinyinUtils.getAlpha("孙警务")),
                new StudentUserBean("张军", "18765432345",PinyinUtils.getAlpha("张军")),
                new StudentUserBean("张兴华", "18765432345",PinyinUtils.getAlpha("张兴华")),
                new StudentUserBean("赵颖号", "18765432345",PinyinUtils.getAlpha("赵颖号")),
                new StudentUserBean("刘志国", "18765432345",PinyinUtils.getAlpha("刘志国")),
                new StudentUserBean("段理应", "18765432345",PinyinUtils.getAlpha("段理应")),
                new StudentUserBean("尽情用", "18765432345",PinyinUtils.getAlpha("尽情用")),
                new StudentUserBean("利亚", "18765432345",PinyinUtils.getAlpha("利亚")),
                new StudentUserBean("问这", "18765432345",PinyinUtils.getAlpha("问这")),
                new StudentUserBean("董倩", "18765432345",PinyinUtils.getAlpha("董倩")),
                new StudentUserBean("张舒梅", "18765432345",PinyinUtils.getAlpha("张舒梅")),
                new StudentUserBean("与帖中", "18765432345",PinyinUtils.getAlpha("与帖中")),
                new StudentUserBean("梦军营", "18765432345",PinyinUtils.getAlpha("梦军营")),
                new StudentUserBean("梨园", "18765432345",PinyinUtils.getAlpha("梨园")),
                new StudentUserBean("硫化", "18765432345",PinyinUtils.getAlpha("硫化")) };

		for (int i = 0; i < userinfoArray.length; i++) {

			StudentUserBean su = new StudentUserBean(sname, sphone,null);
			String st = userinfoArray[i].getUserName();
			String sp = userinfoArray[i].getPhoneNum();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("s_name", st);
			map.put("s_phone", sp);
			studentList.add(map);
		}

		Arrays.sort(userinfoArray);

		studentUserBeans = Arrays.asList(userinfoArray);
	}

	protected class studentInfoOnItemClickListener implements
            OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub

			Intent it_student_info = new Intent(getActivity(),
					AdvStudentInfoActivity.class);

			for (int i = 0; i <= position; i++) {
				if (position == i) {

					Map map = (Map) studentList.get(i);
					String mstu = (String) map.get("s_name");
					String mpho = (String) map.get("s_phone");
					it_student_info.putExtra("student_name", mstu);
					it_student_info.putExtra("student_phone", mpho);
				}
			}

			startActivity(it_student_info);

		}

	}

	private Handler handler = new Handler() {
	};

	public int alphaIndexer(String s) {
		int position = 0;
		for (int i = 0; i < studentUserBeans.size(); i++) {

			if (studentUserBeans.get(i).getPy().startsWith(s)) {
				position = i;
				break;
			}
		}
		Log.i("coder", "i" + position + studentUserBeans.get(position));
		return position;
	}

}
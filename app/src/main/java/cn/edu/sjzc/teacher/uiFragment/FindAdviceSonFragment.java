package cn.edu.sjzc.teacher.uiFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.edu.sjzc.teacher.R;

public class FindAdviceSonFragment extends BaseFragment{
	
	public static BaseFragment newInstance(int index) {
		BaseFragment fragment = new FindAdviceSonFragment();
		Bundle args = new Bundle();
		args.putInt("index", index);
		fragment.setArguments(args);
		fragment.setIndex(index);
		return fragment;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_find_advice_son, null);
	}	
}
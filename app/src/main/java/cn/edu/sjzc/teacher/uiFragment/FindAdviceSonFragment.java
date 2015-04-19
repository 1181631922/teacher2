package cn.edu.sjzc.teacher.uiFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private View layoutView;
    private FragmentTabHost mTabHost;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
		layoutView= inflater.inflate(R.layout.fragment_advice, null);
        init(getIndex());
        return layoutView;
	}
    public void init(int i){
        if (i==0){
            TextView tv = (TextView) layoutView.findViewById(R.id.textView111);
            tv.setText( 1+ "");
        }if (i==1){
            TextView tv = (TextView) layoutView.findViewById(R.id.textView111);
            tv.setText( 2+ "");
        }
    }
}
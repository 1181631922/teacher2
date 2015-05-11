package cn.edu.sjzc.teacher.uiFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import cn.edu.sjzc.teacher.R;

public class FindAdviceFragment extends BaseFragment implements View.OnClickListener {

    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new FindAdviceFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }

    private View layoutView;
    private FragmentTabHost mTabHost;
    private ImageView redpoint;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_tabs, null);
        layoutView.findViewById(R.id.button1).setOnClickListener(this);
        layoutView.findViewById(R.id.button1).setSelected(true);
        layoutView.findViewById(R.id.button2).setOnClickListener(this);
//        this.redpoint=(ImageView)getActivity().findViewById(R.id.redpoint);
//        this.redpoint.setVisibility(View.GONE);

        if (savedInstanceState == null) {
            BaseFragment fragment = (BaseFragment) getChildFragmentManager()
                    .findFragmentByTag(0 + "");// getActivity().getSupportFragmentManager().findFragmentByTag(index+"");
            if (fragment == null) {
                init(0);
            }
        }

        return layoutView;
    }

    private void init(int index) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        ft.add(R.id.realtabcontent, FindAdviceSonFragment.newInstance(index), index + "");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    public void onClick(View v) {

        allNoSelect();

        switch (v.getId()) {
            case R.id.button1:
                placeView(0);
                break;
            case R.id.button2:
                placeView(1);
                break;
            default:
                break;
        }

        v.setSelected(true);
    }

    private void allNoSelect() {
        layoutView.findViewById(R.id.button1).setSelected(false);
        layoutView.findViewById(R.id.button2).setSelected(false);
    }

    public void placeView(int index) {
        BaseFragment fragment = (BaseFragment) getChildFragmentManager()
                .findFragmentByTag(index + "");
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        if (fragment == null) {
            switch (index) {
                case 0:
                    fragment = FindAdviceSonFragment.newInstance(index);// index=
                    return;
                case 1:
                    fragment = FindAdviceSonFragment.newInstance(index);// index=
                    break;
                default:

                    return;
            }

        }
        ft.replace(R.id.realtabcontent, fragment, index + "");
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();

    }

}
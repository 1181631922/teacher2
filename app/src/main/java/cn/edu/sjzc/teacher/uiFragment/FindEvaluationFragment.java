package cn.edu.sjzc.teacher.uiFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.uiActivity.EvealuationActivity;
import cn.edu.sjzc.teacher.uiActivity.MyRankingActivity;
import cn.edu.sjzc.teacher.uiActivity.TeacherRankingActivity;

public class FindEvaluationFragment extends BaseFragment implements OnClickListener {

    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new FindEvaluationFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }

    private Button eva_evaluation_but, eva_score_but, eva_otherteacher_but;
    private ImageView redpoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_evaluation, null);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onActivityCreated(savedInstanceState);

        init();
    }

    private void init() {
//        this.redpoint = (ImageView) getActivity().findViewById(R.id.redpoint);
//        this.redpoint.setVisibility(View.GONE);
        Button eva_evaluation_but = (Button) getActivity().findViewById(R.id.eva_evaluation_but);
        eva_evaluation_but.setOnClickListener(this);
        Button eva_score_but = (Button) getActivity().findViewById(R.id.eva_score_but);
        eva_score_but.setOnClickListener(this);
        Button eva_otherteacher_but = (Button) getActivity().findViewById(R.id.eva_otherteacher_but);
        eva_otherteacher_but.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.eva_evaluation_but:
                Intent it_evalua = new Intent(getActivity(), EvealuationActivity.class);
                startActivity(it_evalua);
                break;
            case R.id.eva_score_but:

                Intent it_score = new Intent(getActivity(),
                        MyRankingActivity.class);
                FindEvaluationFragment.this.startActivity(it_score);

                break;
            case R.id.eva_otherteacher_but:

                Intent it_otherteacher = new Intent(getActivity(),
                        TeacherRankingActivity.class);
                FindEvaluationFragment.this.startActivity(it_otherteacher);

                break;

            default:
                break;
        }

    }

}
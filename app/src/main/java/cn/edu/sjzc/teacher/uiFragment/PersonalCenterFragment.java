package cn.edu.sjzc.teacher.uiFragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.app.UserApplication;
import cn.edu.sjzc.teacher.uiActivity.AboutActivity;
import cn.edu.sjzc.teacher.uiActivity.PerChangeInfoActivity;
import cn.edu.sjzc.teacher.uiActivity.PerChangePasswordActivity;
import cn.edu.sjzc.teacher.uiActivity.PerPersonalInfoActivity;
import cn.edu.sjzc.teacher.uiActivity.PerScheduleActivity;

public class PersonalCenterFragment extends BaseFragment implements OnClickListener {
    public static BaseFragment newInstance(int index) {
        BaseFragment fragment = new PersonalCenterFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        fragment.setArguments(args);
        fragment.setIndex(index);
        return fragment;
    }

    private Button user_but, per_person_but, per_password_but,
            per_schedule_but, per_about__but;
    private ImageView redpoint;
    private TextView per_studentid_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personalcenter, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    private void initData() {
        SharedPreferences userdata = getActivity().getSharedPreferences(UserApplication.USER_DATA, 0);
        String number = userdata.getString(UserApplication.USER_DATA_NUMBER, "");
        String name = userdata.getString(UserApplication.USER_DATA_USERNAME, "");
        this.per_studentid_text.setText("老师教工号：" + number);
        this.user_but.setText(name);
    }

    public void initView() {
        this.per_studentid_text = (TextView) getActivity().findViewById(R.id.per_studentid_text);
        this.user_but = (Button) getActivity().findViewById(R.id.user_but);
        this.user_but.setOnClickListener(this);
        this.per_person_but = (Button) getActivity().findViewById(R.id.per_person_but);
        this.per_person_but.setOnClickListener(this);
        this.per_password_but = (Button) getActivity().findViewById(R.id.per_password_but);
        this.per_password_but.setOnClickListener(this);
        this.per_schedule_but = (Button) getActivity().findViewById(R.id.per_schedule_but);
        this.per_schedule_but.setOnClickListener(this);
        this.per_about__but = (Button) getActivity().findViewById(R.id.per_about__but);
        this.per_about__but.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        switch (v.getId()) {
            case R.id.user_but:
                Intent it_user = new Intent(getActivity(), PerChangeInfoActivity.class);
                PersonalCenterFragment.this.startActivity(it_user);
                break;
            case R.id.per_person_but:
                Intent it_person = new Intent(getActivity(), PerPersonalInfoActivity.class);
                PersonalCenterFragment.this.startActivity(it_person);
                break;

            case R.id.per_password_but:
                Intent it_password = new Intent(getActivity(), PerChangePasswordActivity.class);
                PersonalCenterFragment.this.startActivity(it_password);
                break;

            case R.id.per_schedule_but:
                Intent it_schedule = new Intent(getActivity(), PerScheduleActivity.class);
                PersonalCenterFragment.this.startActivity(it_schedule);
                break;
            case R.id.per_about__but:
                Intent it_about = new Intent(getActivity(), AboutActivity.class);
                startActivity(it_about);
                break;

            default:
                break;
        }

    }

}
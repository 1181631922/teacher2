package cn.edu.sjzc.teacher.uiActivity;

import android.os.Bundle;
import android.view.Window;

import cn.edu.sjzc.teacher.R;

public class SignActivity extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);// �������ڷ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
		super.setContentView(R.layout.activity_sign);

	}

}
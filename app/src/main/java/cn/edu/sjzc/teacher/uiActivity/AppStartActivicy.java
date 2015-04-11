package cn.edu.sjzc.teacher.uiActivity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.uiFragment.MainTabActivity;

public class AppStartActivicy extends BaseActivity {
	Thread thread;
	protected void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  this.requestWindowFeature(Window.FEATURE_NO_TITLE); //ȥ��������
		      setContentView(R.layout.activity_appstart); // �󶨲����ļ�
		      
		      new Handler().postDelayed(new Runnable(){
		  		@Override
		  		public void run(){
		  			Intent intent = new Intent(AppStartActivicy.this,MainTabActivity.class);
		  			startActivity(intent);			
		  			AppStartActivicy.this.finish();
		  		}
		  	}, 4000);     
		       
		      
		     }



}

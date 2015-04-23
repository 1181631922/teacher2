package cn.edu.sjzc.teacher.uiActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.dialog.HomeNewsDialog;
import cn.edu.sjzc.teacher.service.ExitSoundService;

public class RetroactionActivity extends BaseActivity implements View.OnClickListener {
    WebView wv;
    ProgressDialog pd;
    Handler handler;
    private ProgressBar retro_show_progress;
    private ImageButton retro_show_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_retroaction);
        initView();
        initData();
        loadurl(wv, "http://eqxiu.com/s/fCBe4WiT");
    }

    private void initView() {
        this.retro_show_progress = (ProgressBar) RetroactionActivity.this.findViewById(R.id.retro_show_progress);
        this.retro_show_back = (ImageButton) RetroactionActivity.this.findViewById(R.id.retro_show_back);
        this.retro_show_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retro_show_back:
                finish();
                break;

        }
    }

    public void initData() {
        // Progress 重新定义，覆盖过时的api
//        pd = new ProgressDialog(HomeNewsActivity.this);
//        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        pd.setMessage("数据载入中，请稍候！");
        // Show/Hide message
        handler = new Handler() {
            public void handleMessage(Message msg) {// ����һ��Handler�����ڴ��������߳���UI��ͨѶ
                if (!Thread.currentThread().isInterrupted()) {
                    switch (msg.what) {
                        case 0:
                            retro_show_progress.showContextMenu();
                            break;
                        case 1:
                            retro_show_progress.setVisibility(View.GONE);
                            break;
                    }
                }
                super.handleMessage(msg);
            }
        };
        // WebView
        wv = (WebView) findViewById(R.id.wv);
        wv.getSettings().setJavaScriptEnabled(true);// ����JS
        wv.setScrollBarStyle(0);// ���������Ϊ0���ǲ�����������ռ䣬��������������ҳ��
        wv.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(final WebView view,
                                                    final String url) {
                loadurl(view, url);// ������ҳ
                return true;
            }// ��д�������,��webview����

        });
        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {// �����ȸı��
                if (progress == 100) {
                    handler.sendEmptyMessage(1);// ���ȫ������,���ؽ�ȶԻ���
                }
                super.onProgressChanged(view, progress);
            }
        });
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {// ��׽���ؼ�
        if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
            wv.goBack();
            return true;
        } else if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent it_exit = new Intent(RetroactionActivity.this, ExitSoundService.class);
            startService(it_exit);
            ConfirmExit();// ���˷��ؼ��Ѿ����ܷ��أ���ִ���˳�ȷ��
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void ConfirmExit() {// �˳�ȷ��
        HomeNewsDialog dialog=new HomeNewsDialog(this, R.style.mystyle, R.layout.dialog_exit_main);
        dialog.show();
    }

    public void loadurl(final WebView view, final String url) {
        handler.sendEmptyMessage(0);
        view.loadUrl(url);// ������ҳ
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_retroaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

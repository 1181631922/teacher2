package cn.edu.sjzc.teacher.uiActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.dialog.HomeExchangeDialog;

public class HomeExchangeActivity extends BaseActivity implements View.OnClickListener{
    /**
     * Called when the activity is first created.
     */
    WebView wv;
    ProgressDialog pd;
    Handler handler;
    private ProgressBar web_show_progress;
    private ImageButton web_show_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
        setContentView(R.layout.web_show);
        initView();
        initData();// ִ�г�ʼ������
        loadurl(wv, "http://www.sjzc.edu.cn/col/1270779355718/index.html");
    }

    private void initView() {
        this.web_show_progress = (ProgressBar) HomeExchangeActivity.this.findViewById(R.id.web_show_progress);
        this.web_show_back = (ImageButton) HomeExchangeActivity.this.findViewById(R.id.web_show_back);
        this.web_show_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.web_show_back:
                finish();
                break;

        }
    }

    public void initData() {
        // Progress
//		pd = new ProgressDialog(HomeExchangeActivity.this);
//		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//		pd.setMessage("数据载入中，请稍候！");
        // Show/Hide message
        handler = new Handler() {
            public void handleMessage(Message msg) {// ����һ��Handler�����ڴ��������߳���UI��ͨѶ
                if (!Thread.currentThread().isInterrupted()) {
                    switch (msg.what) {
                        case 0:
//						pd.show();// ��ʾ��ȶԻ���
                            web_show_progress.showContextMenu();
                            break;
                        case 1:
//						pd.hide();// ���ؽ�ȶԻ��򣬲���ʹ��dismiss()��cancel(),�����ٴε���show()ʱ����ʾ�ĶԻ���СԲȦ���ᶯ��
                            web_show_progress.showContextMenu();
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
            ConfirmExit();// ���˷��ؼ��Ѿ����ܷ��أ���ִ���˳�ȷ��
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void ConfirmExit() {// �˳�ȷ��
        HomeExchangeDialog dialog=new HomeExchangeDialog(this, R.style.mystyle, R.layout.dialog_exit_main);
        dialog.show();
    }

    public void loadurl(final WebView view, final String url) {
        handler.sendEmptyMessage(0);
        view.loadUrl(url);// ������ҳ
    }


}
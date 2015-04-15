package cn.edu.sjzc.teacher.uiActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import cn.edu.sjzc.teacher.R;
import cn.edu.sjzc.teacher.service.ConfirmSoundService;
import cn.edu.sjzc.teacher.service.ExitSoundService;

public class HomeNewsActivity extends BaseActivity {
    /**
     * Called when the activity is first created.
     */
    WebView wv;
    ProgressDialog pd;
    Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// ȥ��������
        setContentView(R.layout.web_show);
        init();// ִ�г�ʼ������
        loadurl(wv, "http://www.sjzc.edu.cn/col/1270779319140/index.html");
    }

    public void init() {
        // Progress
        pd = new ProgressDialog(HomeNewsActivity.this);
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setMessage("数据载入中，请稍候！");
        // Show/Hide message
        handler = new Handler() {
            public void handleMessage(Message msg) {// ����һ��Handler�����ڴ��������߳���UI��ͨѶ
                if (!Thread.currentThread().isInterrupted()) {
                    switch (msg.what) {
                        case 0:
                            pd.show();// ��ʾ��ȶԻ���
                            break;
                        case 1:
                            pd.hide();// ���ؽ�ȶԻ��򣬲���ʹ��dismiss()��cancel(),�����ٴε���show()ʱ����ʾ�ĶԻ���СԲȦ���ᶯ��
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
            Intent it_exit = new Intent(HomeNewsActivity.this, ExitSoundService.class);
            startService(it_exit);
            ConfirmExit();// ���˷��ؼ��Ѿ����ܷ��أ���ִ���˳�ȷ��
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void ConfirmExit() {// �˳�ȷ��
        AlertDialog.Builder ad = new AlertDialog.Builder(HomeNewsActivity.this);
        ad.setTitle("退出");
        ad.setMessage("是否返回到主页?");
        ad.setPositiveButton("是", new DialogInterface.OnClickListener() {// �˳���ť
            @Override
            public void onClick(DialogInterface dialog, int i) {
                // TODO Auto-generated method stub
                HomeNewsActivity.this.finish();// �ر�activity

            }
        });
        ad.setNegativeButton("否", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                // ���˳�����ִ���κβ���
            }
        });
        ad.show();// ��ʾ�Ի���
    }

    public void loadurl(final WebView view, final String url) {
        handler.sendEmptyMessage(0);
        view.loadUrl(url);// ������ҳ
    }

}
package cn.edu.sjzc.teacher.uiActivity;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ABaseActivity extends Activity {
//    protected String aBaseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPropertyFileContent();
    }

    private void getPropertyFileContent() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = getAssets().open("fanyafeng.properties");
            properties.load(inputStream);
//            aBaseUrl = properties.getProperty("ServerUrl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

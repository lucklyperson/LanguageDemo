package demo.gradle.com.languagedemo;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;

/**
 * @author wzq
 * @date 2019/4/29 下午5:29
 * @desc
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            super.attachBaseContext(newBase);
        } else {
            //zh：中文
            super.attachBaseContext(LanguageUtil.initAppLanguage(newBase, "zh"));
        }
    }
}

package demo.gradle.com.languagedemo;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

/**
 * @author wzq
 * @date 2019/4/29 下午5:26
 * @desc
 */

public class MyApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private boolean isRunInBackground;
    private int appCount;

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(this);

        initLanguage();
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        appCount++;
        if (isRunInBackground) {
            Log.e("TAG", "从后台进入前台");
            isRunInBackground = false;
            //防止应用程序切换到后台，然后通过设置设置语言，最后再将应用程序从后台切换回前台的情况
            initLanguage();
        }
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        appCount--;
        if (appCount == 0) {
            isRunInBackground = true;
            Log.e("TAG", "进入了后台");
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    private void initLanguage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            //Application这种方式适用于8.0之前(不包括8.0)的版本
            LanguageUtil.initAppLanguage(getApplicationContext(), "zh");
        }
    }
}

package com.example.sashohadzhiev.onlygcmsetup;

import android.app.Application;
import android.util.Log;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumActivityHelper;
import com.leanplum.LeanplumPushService;
import com.leanplum.annotations.Parser;
import com.leanplum.annotations.Variable;
import com.leanplum.callbacks.VariablesChangedCallback;

/**
 * Created by sashohadzhiev on 1/23/18.
 */

public class MyApplication extends Application {

    @Variable
    public static String welcome = "Hi there!";


    @Override
    public void onCreate() {
        super.onCreate();

        Leanplum.setApplicationContext(this);

        Parser.parseVariables(this);

        LeanplumActivityHelper.enableLifecycleCallbacks(this);

        String appId = "app_EiuFqBANjPsaxxRPHiaJfae2al1IRkouWnVfL4zLwPo";
        String prodKey = "prod_js9ILd0P0VXIpYiLR6OahjV6XX6JHz0MxvuQVKL7vjo";
        String devKey = "dev_p3jfynJcrIQNccHhWsUjFBeWGT9D0pgq8ISWdt5Z9Y4";

        if (BuildConfig.DEBUG) {
            System.out.println("DEV MODE");
            Leanplum.setAppIdForDevelopmentMode(appId, devKey);
        } else {
            System.out.println("PROD MODE");
            Leanplum.setAppIdForProductionMode(appId, prodKey);
        }

        Leanplum.enableVerboseLoggingInDevelopmentMode();

        LeanplumPushService.setGcmSenderId(LeanplumPushService.LEANPLUM_SENDER_ID);


        Leanplum.start(this);

        Leanplum.addVariablesChangedAndNoDownloadsPendingHandler(new VariablesChangedCallback() {
            @Override
            public void variablesChanged() {
                Log.e("   Leanplum ", "Variables Changed And No Downloads Pending");
                Leanplum.track("random event");
            }
        });
    }


}

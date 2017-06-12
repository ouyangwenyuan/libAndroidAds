package cn.evolvingera.libchinaads;

import android.app.Application;

import cn.evolvingera.libads.adscore.AdsManager;


/**
 * Created by ouyangwenyuan on 17/6/11.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AdsManager.getInstance().init(this);
        AdsManager.getInstance().configInMobi("8d1900d7cae74f1780db6b534d6dbcda");
        AdsManager.getInstance().configAdmob("xxxxx"); //admob appkey
        AdsManager.getInstance().setAdsAvailable(true);
    }
}

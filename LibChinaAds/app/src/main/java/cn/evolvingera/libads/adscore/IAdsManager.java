package cn.evolvingera.libads.adscore;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

/**
 * Created by ouyangwenyuan on 17/6/11.
 */
public interface IAdsManager {
    public enum AdsType {
        admob, inmobi
    }

    public interface Callback {
        void onAdLoadSucceeded(final Object inMobiNative);

        void onAdLoadFailed(Object inMobiNative);
    }

    void init(Context context);

    void configInMobi(String admobkey);

    void configAdmob(String inmobkey);

    void setAdsAvailable(boolean available);

    void loadNativeAds(AdsType adsType, RelativeLayout adContainner, String adsId, Callback callback);


    void loadSplashAds(AdsType adsType, String adsId, Callback callback);

    void loadBannerAds(AdsType adsType, RelativeLayout adContainner, int width, int height, String adsId, Callback callback);
}

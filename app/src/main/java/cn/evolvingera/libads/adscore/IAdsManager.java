package cn.evolvingera.libads.adscore;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by ouyangwenyuan on 17/6/11.
 * 广告管理类
 */
public interface IAdsManager {
    /**
     * 广告类型
     */
    public enum AdsType {
        admob, inmobi
    }

    /**
     * 广告请求的回调
     */
    public interface Callback {
        void onAdLoadSucceeded(final Object inMobiNative);

        void onAdLoadFailed(Object inMobiNative);
    }

    /**
     * 在第一个activity中调用，此context 必须为activity
     *
     * @param context
     */
    void init(Context context);

    /**
     * 配置 inmobkey
     *
     * @param inmobkey
     */
    void configInMobi(String inmobkey);

    /**
     * 配置 admob
     *
     * @param admobkey
     */
    void configAdmob(String admobkey);

    /**
     * 设置广告是否可用
     *
     * @param available
     */
    void setAdsAvailable(boolean available);

    /**
     * 插入 本地 广告
     *
     * @param adsType
     * @param adContainner 广告父窗体，必须为RelativeLayout
     * @param width
     * @param height
     * @param adsId
     * @param callback
     */
    void loadNativeAds(AdsType adsType, RelativeLayout adContainner, int width, int height, String adsId, Callback callback);


    /**
     * 加载 全屏 广告
     *
     * @param adsType
     * @param adsId
     * @param callback
     */
    void loadSplashAds(AdsType adsType, String adsId, Callback callback);

    /**
     * 加载banner 广告
     *
     * @param adsType
     * @param adContainner 广告父窗体，必须为RelativeLayout
     * @param width
     * @param height
     * @param adsId
     * @param callback
     */
    void loadBannerAds(AdsType adsType, RelativeLayout adContainner, int width, int height, String adsId, Callback callback);
}

package cn.evolvingera.libads.adscore;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.NativeExpressAdView;
import com.google.android.gms.ads.formats.NativeAdView;
import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.ads.InMobiNative;
import com.inmobi.sdk.InMobiSdk;

import java.util.Map;
import java.util.Random;

import cn.evolvingera.libads.adscore.utils.DensityUtil;
import cn.evolvingera.libads.adscore.utils.MyLog;
import cn.evolvingera.libchinaads.BuildConfig;

/**
 * Created by ouyangwenyuan on 17/6/11.
 */
public class AdsManager implements IAdsManager {


    private void AdsManager() {

    }

    private static AdsManager instance;
    private static boolean adsavailable = true;
    private static String testDevice = "F0140DAD6051E537B73E5C92301D63D3";

    private Context context;

    public static AdsManager getInstance() {
        if (instance == null) {
            instance = new AdsManager();
        }
        return instance;
    }

    public static void setTestDevice(String testDevice) {
        AdsManager.testDevice = testDevice;
    }

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void configInMobi(String inmobkey) {
        InMobiSdk.init(this.context, inmobkey);
//        InMobiSdk.setLocation(new Location());
        InMobiSdk.setGender(InMobiSdk.Gender.FEMALE);
        InMobiSdk.setAgeGroup(InMobiSdk.AgeGroup.BETWEEN_21_AND_24);
        if (BuildConfig.DEBUG) {
          InMobiSdk.setLogLevel(InMobiSdk.LogLevel.DEBUG);
        }
    }

    @Override
    public void configAdmob(String admobkey) {
        MobileAds.initialize(context, admobkey);
    }

    @Override
    public void setAdsAvailable(boolean available) {
        adsavailable = available;
    }

    @Override
    public void loadNativeAds(AdsType adsType, RelativeLayout adContainner, int width, int height, String adsId, final Callback callback) {

        if (adContainner.getChildCount() > 0) {
            MyLog.i("ads had load");
            adContainner.removeAllViews();
        }

        RelativeLayout.LayoutParams bannerLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bannerLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bannerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);

        if (adsavailable) {
            switch (adsType) {
                case admob: {
                    NativeExpressAdView expressAdView = new NativeExpressAdView(context);
                    expressAdView.setAdSize(new AdSize(width, height));
                    expressAdView.setAdUnitId(adsId);
                    AdRequest request = new AdRequest.Builder().addTestDevice(testDevice)
                            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                            .build();
                    adContainner.addView(expressAdView, bannerLp);
                    expressAdView.loadAd(request);
                    MyLog.i("expressAdView.loadAd");
                }
                break;
                case inmobi:
                    InMobiNative inMobiNative = new InMobiNative(context, Long.valueOf(adsId), new InMobiNative.NativeAdListener() {
                        @Override
                        public void onAdLoadSucceeded(InMobiNative inMobiNative) {
                            if (callback != null) {
                                callback.onAdLoadSucceeded(inMobiNative);
                            }
                        }

                        @Override
                        public void onAdLoadFailed(InMobiNative inMobiNative, InMobiAdRequestStatus inMobiAdRequestStatus) {
                            if (callback != null) {
                                callback.onAdLoadSucceeded(inMobiNative);
                            }
                        }

                        @Override
                        public void onAdFullScreenDismissed(InMobiNative inMobiNative) {

                        }

                        @Override
                        public void onAdFullScreenDisplayed(InMobiNative inMobiNative) {

                        }

                        @Override
                        public void onUserWillLeaveApplication(InMobiNative inMobiNative) {

                        }

                        @Override
                        public void onAdImpressed(@NonNull InMobiNative inMobiNative) {

                        }

                        @Override
                        public void onAdClicked(@NonNull InMobiNative inMobiNative) {

                        }

                        @Override
                        public void onMediaPlaybackComplete(@NonNull InMobiNative inMobiNative) {

                        }

                    });
                    inMobiNative.load();
                    break;
            }
        }
    }

    @Override
    public void loadSplashAds(AdsType adsType, String adsId, final Callback callback) {
        if (adsavailable) {
            switch (adsType) {
                case admob: {
                    newInterstitialAd(adsId);
                }
                break;
                case inmobi:
                    InMobiInterstitial interstitialAd = new InMobiInterstitial(context, Long.valueOf(adsId), new InMobiInterstitial.InterstitialAdListener2() {
                        @Override
                        public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                            MyLog.i("ads had load onAdLoadFailed");
                            if (callback != null) {
                                callback.onAdLoadSucceeded(inMobiInterstitial);
                            }
                        }

                        @Override
                        public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
                            MyLog.i("ads had load onAdReceived");
                            if (callback != null) {
                                callback.onAdLoadSucceeded(inMobiInterstitial);
                            }
                        }

                        @Override
                        public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                            if (callback != null) {
                                callback.onAdLoadSucceeded(inMobiInterstitial);
                            }
                            inMobiInterstitial.show();
                        }

                        @Override
                        public void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

                        }

                        @Override
                        public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {

                        }

                        @Override
                        public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {

                        }

                        @Override
                        public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {

                        }

                        @Override
                        public void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

                        }

                        @Override
                        public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {

                        }

                        @Override
                        public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {

                        }
                    });
                    interstitialAd.load();
                    break;
            }
        }

    }

    @Override
    public void loadBannerAds(AdsType adsType, RelativeLayout adContainner, int width,
                              int height, String adsId, Callback callback) {

        if (adContainner.getChildCount() > 0) {
            MyLog.i("ads had load");
            adContainner.removeAllViews();
        }


        RelativeLayout.LayoutParams bannerLp = new RelativeLayout.LayoutParams(DensityUtil.dip2px(context, width), DensityUtil.dip2px(context, height));
        bannerLp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        bannerLp.addRule(RelativeLayout.CENTER_HORIZONTAL);
        if (BuildConfig.DEBUG) {
            TextView textView = new TextView(context);
            textView.setText("HAHAHa" + new Random().nextInt(100));
            textView.setTextColor(Color.RED);
            textView.setBackgroundColor(Color.GREEN);
            adContainner.addView(textView, bannerLp);
        }


        if (adsavailable) {
            switch (adsType) {
                case admob:
                    AdView adView = new AdView(context);
                    adView.setAdSize(AdSize.BANNER);
                    adView.setAdUnitId(adsId);
                    AdRequest adRequest = new AdRequest.Builder().addTestDevice(testDevice)
                            .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                            .build();
                    adContainner.addView(adView, bannerLp);
                    adView.loadAd(adRequest);
                    break;

                case inmobi:
                    InMobiBanner bannerAd = new InMobiBanner(context, Long.valueOf(adsId));
                    adContainner.addView(bannerAd, bannerLp);
                    bannerAd.load();


                    MyLog.i("load banner width = " + DensityUtil.dip2px(context, width) + ",height=" + DensityUtil.dip2px(context, height));
                    break;
            }
        }
    }

    private InterstitialAd newInterstitialAd(String adsId) {
        final InterstitialAd interstitialAd = new InterstitialAd(context);
        interstitialAd.setAdUnitId(adsId);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                showInterstitial(interstitialAd);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
//                goToNextLevel();
            }
        });
        AdRequest adRequest = new AdRequest.Builder().addTestDevice(testDevice)
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        interstitialAd.loadAd(adRequest);
        return interstitialAd;
    }

    private void showInterstitial(InterstitialAd mInterstitialAd) {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Toast.makeText(context, "Ad did not load", Toast.LENGTH_SHORT).show();
//            goToNextLevel();
        }
    }


}

package net.droidman.testjitpack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.inmobi.ads.InMobiNative;
import com.squareup.picasso.Picasso;

import cn.evolvingera.libads.adscore.AdsManager;
import cn.evolvingera.libads.adscore.IAdsManager;
import cn.evolvingera.libads.adscore.utils.MyLog;

public class MainActivity extends AppCompatActivity {

    RelativeLayout adRootView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        AdsManager.getInstance().init(this);
        AdsManager.getInstance().configAdmob("ca-app-pub-3618984151272025~2700887995");
        AdsManager.getInstance().configInMobi("8d1900d7cae74f1780db6b534d6dbcda");
        AdsManager.getInstance().setAdsAvailable(true);
        AdsManager.setTestDevice("F0140DAD6051E537B73E5C92301D63D3"); //根据log 找到测试机的 deviceid ，替换此 id

        this.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Long bannerId = Long.valueOf(1497470953569L);
                AdsManager.getInstance().loadBannerAds(IAdsManager.AdsType.inmobi, MainActivity.this.adRootView, 320, 50, bannerId + "", new IAdsManager.Callback() {
                    public void onAdLoadSucceeded(Object inMobiNative) {
                    }

                    public void onAdLoadFailed(Object inMobiNative) {
                    }
                });
            }
        });
        this.findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long bannerId = 1496480579249L;
                AdsManager.getInstance().loadSplashAds(IAdsManager.AdsType.inmobi, bannerId + "", new IAdsManager.Callback() {
                    public void onAdLoadSucceeded(Object inMobiNative) {
                    }

                    public void onAdLoadFailed(Object inMobiNative) {
                    }
                });
            }
        });
        this.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                long bannerId = 1497982236802L;
                AdsManager.getInstance().loadNativeAds(IAdsManager.AdsType.inmobi, MainActivity.this.adRootView,320,150, bannerId + "", new IAdsManager.Callback() {
                    public void onAdLoadSucceeded(Object inMobiNative) {
                        MyLog.i("success " + inMobiNative);
                        if (inMobiNative instanceof InMobiNative) {
                            InMobiNative admodel = (InMobiNative) inMobiNative;
                            MyLog.i("land url = " + admodel.getAdIconUrl());
                            ImageView imageView = new ImageView(MainActivity.this);
                            RelativeLayout.LayoutParams bannerLp = new RelativeLayout.LayoutParams(-1, -1);
                            MainActivity.this.adRootView.addView(imageView, bannerLp);
//                            admodel.getAdLandingPageUrl()
                            Picasso.with(MainActivity.this).load(admodel.getAdIconUrl()).into(imageView, new com.squareup.picasso.Callback() {
                                public void onSuccess() {
                                }

                                public void onError() {
                                }
                            });
                        }

                    }

                    public void onAdLoadFailed(Object inMobiNative) {
                        MyLog.i("fail " + inMobiNative);
                    }
                });
            }
        });
        this.findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdmobActivity.class));
            }
        });
        this.adRootView = (RelativeLayout) this.findViewById(R.id.rootview);

        long bannerId = 1496480579249L;
        AdsManager.getInstance().loadSplashAds(IAdsManager.AdsType.inmobi, bannerId + "", new IAdsManager.Callback() {
            public void onAdLoadSucceeded(Object inMobiNative) {
            }

            public void onAdLoadFailed(Object inMobiNative) {
            }
        });
    }
}

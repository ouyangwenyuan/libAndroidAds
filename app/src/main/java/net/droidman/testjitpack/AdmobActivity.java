package net.droidman.testjitpack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.inmobi.ads.InMobiNative;

import cn.evolvingera.libads.adscore.AdsManager;
import cn.evolvingera.libads.adscore.IAdsManager;
import cn.evolvingera.libads.adscore.utils.MyLog;

public class AdmobActivity extends AppCompatActivity {

    RelativeLayout adRootView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        Button btn1 = (Button) findViewById(R.id.button1);
        btn1.setText("admob_banner");
        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String bannerId = "ca-app-pub-3618984151272025/1408527591";
                AdsManager.getInstance().loadBannerAds(IAdsManager.AdsType.admob, AdmobActivity.this.adRootView, 320, 50, bannerId, new IAdsManager.Callback() {
                    public void onAdLoadSucceeded(Object inMobiNative) {
                    }

                    public void onAdLoadFailed(Object inMobiNative) {
                    }
                });
            }
        });
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setText("admob_Interstitial");
        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String bannerId = "ca-app-pub-3618984151272025/5784805199";
                AdsManager.getInstance().loadSplashAds(IAdsManager.AdsType.admob, bannerId, new IAdsManager.Callback() {
                    public void onAdLoadSucceeded(Object inMobiNative) {
                    }

                    public void onAdLoadFailed(Object inMobiNative) {
                    }
                });
            }
        });
        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setText("admob_native");
        btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String bannerId = "ca-app-pub-3618984151272025/2970939598";
                AdsManager.getInstance().loadNativeAds(IAdsManager.AdsType.admob, AdmobActivity.this.adRootView,320,150, bannerId, new IAdsManager.Callback() {
                    public void onAdLoadSucceeded(Object inMobiNative) {
                        MyLog.i("success " + inMobiNative);


                    }

                    public void onAdLoadFailed(Object inMobiNative) {
                        MyLog.i("fail " + inMobiNative);
                    }
                });
            }
        });
        Button btn4 = (Button) findViewById(R.id.button4);
        btn4.setText("back");
        btn4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
        this.adRootView = (RelativeLayout) this.findViewById(R.id.rootview);
        AdsManager.getInstance().init(this);
//        String bannerId = "ca-app-pub-3618984151272025/2970939598";
//        AdsManager.getInstance().loadSplashAds(IAdsManager.AdsType.admob, bannerId, new IAdsManager.Callback() {
//            public void onAdLoadSucceeded(Object inMobiNative) {
//            }
//
//            public void onAdLoadFailed(Object inMobiNative) {
//            }
//        });
    }
}

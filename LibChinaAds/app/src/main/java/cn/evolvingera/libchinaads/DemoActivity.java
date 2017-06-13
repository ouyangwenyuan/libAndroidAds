package cn.evolvingera.libchinaads;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.ads.InMobiNative;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import cn.evolvingera.libads.adscore.AdsManager;
import cn.evolvingera.libads.adscore.IAdsManager;
import cn.evolvingera.libads.adscore.utils.MyLog;

public class DemoActivity extends AppCompatActivity {
    InMobiInterstitial interstitialAd;
    RelativeLayout adRootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);


        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long bannerId = 1497470953569L;
                AdsManager.getInstance().loadBannerAds(IAdsManager.AdsType.inmobi, adRootView, 320, 50, bannerId + "", new IAdsManager.Callback() {
                    @Override
                    public void onAdLoadSucceeded(Object inMobiNative) {

                    }

                    @Override
                    public void onAdLoadFailed(Object inMobiNative) {

                    }
                });
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long bannerId = 1496480579249L;
                AdsManager.getInstance().loadSplashAds(IAdsManager.AdsType.inmobi, bannerId + "", new IAdsManager.Callback() {
                    @Override
                    public void onAdLoadSucceeded(Object inMobiNative) {

                    }

                    @Override
                    public void onAdLoadFailed(Object inMobiNative) {

                    }
                });
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long bannerId = 1496585484209L;
                AdsManager.getInstance().loadNativeAds(IAdsManager.AdsType.inmobi, adRootView, bannerId + "", new IAdsManager.Callback() {
                    @Override
                    public void onAdLoadSucceeded(Object inMobiNative) {
                        MyLog.i("success " + inMobiNative);
                        if (inMobiNative instanceof InMobiNative) {
                            InMobiNative admodel = (InMobiNative) inMobiNative;
                            MyLog.i("land url = " + admodel.getAdIconUrl() + ",title=" + admodel.getAdTitle() + ",custum=" + admodel.getCustomAdContent() + ",rate=" + admodel.getAdRating() + ",desc=" + admodel.getAdDescription() + ",cattext=" + admodel.getAdCtaText());
                            ImageView imageView = new ImageView(DemoActivity.this);
                            RelativeLayout.LayoutParams bannerLp = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                            adRootView.addView(imageView, bannerLp);

                            Picasso.with(DemoActivity.this).load(admodel.getAdIconUrl()).into(imageView, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {

                                }
                            });
                        }
                    }

                    @Override
                    public void onAdLoadFailed(Object inMobiNative) {
                        MyLog.i("fail " + inMobiNative);
                    }
                });
            }
        });
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        adRootView = (RelativeLayout) findViewById(R.id.rootview);

        AdsManager.getInstance().init(this);
        long bannerId = 1496480579249L;
        AdsManager.getInstance().loadSplashAds(IAdsManager.AdsType.inmobi, bannerId + "", new IAdsManager.Callback() {
            @Override
            public void onAdLoadSucceeded(Object inMobiNative) {

            }

            @Override
            public void onAdLoadFailed(Object inMobiNative) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}

package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.udacity.gradle.builditbigger.androidlib.JokeDisplayActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements EndpointsAsyncTask.MyCallback {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    InterstitialAd mInterstitialAd;

    private ProgressBar mSpinner;

    Context mContext;

    public MainActivityFragment() {
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mContext = this.getContext(); //requireContext();

        mSpinner = (ProgressBar) root.findViewById(R.id.fragment_main_progressBar);
        mSpinner.setVisibility(View.GONE);





        // ads
        AdRequest adRequest = new AdRequest.Builder().build();

        MobileAds .initialize( mContext, initializationStatus -> {});

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        mAdView.loadAd(adRequest);


        InterstitialAd.load( mContext,"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.d(TAG, "--------> " + "onAdLoaded");


                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Called when fullscreen content is dismissed.
                                Log.d(TAG, "The ad was dismissed.");

                                fetchResults();
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d(TAG, "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;
                                Log.d(TAG, "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, "--------> " + loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });












        ((Button) root.findViewById(R.id.fragment_main_button)) .setOnClickListener( (v) -> {

            if (mInterstitialAd != null) {
                mInterstitialAd.show(requireActivity());
            } else {
                Log.d(TAG, "The interstitial ad wasn't ready yet.");
                fetchResults();
            }
        });


        return root;
    }




    void fetchResults(){
        mSpinner.setVisibility(View.VISIBLE);
        (new Handler()) .postDelayed(() -> new EndpointsAsyncTask( this )
                .execute(new Pair<Context, String>( mContext, "Manfred")), 500);
    }


    @Override
    public void enterResults(String results) {
        Log.d(TAG + "enterResults()", "----> " + results);
        mSpinner.setVisibility(View.GONE);
        Intent intent = new Intent(mContext, JokeDisplayActivity.class);
        intent.putExtra("Joke", results);
        mContext.startActivity(intent);
    }
}

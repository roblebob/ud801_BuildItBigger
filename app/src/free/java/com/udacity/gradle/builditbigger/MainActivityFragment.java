package com.udacity.gradle.builditbigger;

import android.content.Context;
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


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

    InterstitialAd mInterstitialAd;


    public MainActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


        AdRequest adRequest = new AdRequest.Builder().build();

        MobileAds .initialize( requireContext(), initializationStatus -> {});

        AdView mAdView = (AdView) root.findViewById(R.id.adView);
        mAdView.loadAd(adRequest);


        InterstitialAd.load(requireContext(),"ca-app-pub-3940256099942544/1033173712", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.e(TAG, "--------> " + "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.e(TAG, "--------> " + loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });


        ((Button) root.findViewById(R.id.fragment_main_button)) .setOnClickListener( (v) -> {

            if (mInterstitialAd != null) {
                mInterstitialAd.show(requireActivity());
            } else {
                Log.e("TAG", "--------> " + "The interstitial ad wasn't ready yet.");
            }

            Handler handler = new Handler();
            handler.postDelayed(() ->
                    new EndpointsAsyncTask().execute(new Pair<Context, String>( requireContext(), "Manfred"))
                    , 2000
            );
        });


        return root;
    }
}

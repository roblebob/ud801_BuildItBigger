package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import com.udacity.gradle.builditbigger.androidlib.JokeDisplayActivity;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment  implements EndpointsAsyncTask.MyCallback {

    private static final String TAG = MainActivityFragment.class.getSimpleName();

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



        ((Button) root.findViewById(R.id.fragment_main_button)) .setOnClickListener( (v) -> {
            mSpinner.setVisibility(View.VISIBLE);
            new EndpointsAsyncTask(this).execute(new Pair<Context, String>( mContext, "Manfred"));
        });

        return root;
    }




    @Override
    public void enterResults(String results) {
        Log.e(TAG + "enterResults()", "----> " + results);
        mSpinner.setVisibility(View.GONE);
        Intent intent = new Intent(mContext, JokeDisplayActivity.class);
        intent.putExtra("Joke", results);
        mContext.startActivity(intent);
    }
}

package com.udacity.gradle.builditbigger.androidlib;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.gradle.builditbigger.androidlib.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link JokeDisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JokeDisplayFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    public JokeDisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment JokeDisplayActivityFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JokeDisplayFragment newInstance(String param1) {
        JokeDisplayFragment fragment = new JokeDisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_joke_display, container, false);

        ((TextView) root.findViewById(R.id.fragment_joke_display_textview)) .setText(mParam1);

        return root;
    }
}
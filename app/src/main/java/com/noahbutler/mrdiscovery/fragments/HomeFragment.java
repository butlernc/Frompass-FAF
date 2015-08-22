package com.noahbutler.mrdiscovery.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Space;

import com.noahbutler.mrdiscovery.R;

/**
 * Created by Noah Butler on 8/16/2015.
 */
public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_home, container, false);

        Space findButton = (Space)mainView.findViewById(R.id.find_button);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("FIND", "Go to the find list!");
            }
        });

        return mainView;
    }
}

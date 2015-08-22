package com.noahbutler.mrdiscovery.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.noahbutler.mrdiscovery.Network.Connector;
import com.noahbutler.mrdiscovery.R;

/**
 * Created by Noah Butler on 8/20/2015.
 */
public class FriendsListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View masterView = inflater.inflate(R.layout.fragment_friendslist, container, false);

        View friendListHolderView = masterView.findViewById(R.id.friend_list_holder);

        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] params = new String[1];
                Connector connector = new Connector();
                params[0] = "get_list";
                connector.execute(params);
                Log.d("RUNNING", "WE ARE RUNNING");
            }
        }).start();


        return masterView;
    }
}

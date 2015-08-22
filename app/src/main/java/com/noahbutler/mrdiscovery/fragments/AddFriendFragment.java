package com.noahbutler.mrdiscovery.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.noahbutler.mrdiscovery.Network.Connector;
import com.noahbutler.mrdiscovery.R;

import org.w3c.dom.Text;

/**
 * Created by Noah Butler on 8/17/2015.
 */
public class AddFriendFragment extends Fragment {

    private String username;
    private EditText usernameSearchInput;
    private TextView updateText;
    private Button addFriendButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View masterView = inflater.inflate(R.layout.fragment_add_friend, container, false);

        /* TODO: list of actions that need to be completed by the AddFragment

            1. setup layout
                a. create button object to add by username
                b. create button object to add by Mr.Discovery ID
                c. make button object listeners
            2. make button listeners
                a. Add by username listeners
                    i. delete text (Add by username) and replace with an input text box
                    ii. delete the (Add by Mr.Discovery ID) button and text, replace button with
                    back button
                    iii. when button is clicked, check for text in the input text
                    iv. take the text and search the data for the username.
                    v. if the user name exists send a push notification to that user, informing
                    them of the request. If they accept notify the requester that the user
                    accepted the friend request on the app.
                b. Add by Mr. Discovery ID
                    i. delete the (Add by username) button and text
                    ii. move the Mr.Discovery ID button to the bottom right had corner
                    iii. bring up the camera to snap a picture of the other person Mr. Discovery ID
                    iv. scan in Mr.Discovery ID, search database for the id
                    v. if there is a positive match, send the both users the a notification of
                    the successful add.
         */
        usernameSearchInput = (EditText)masterView.findViewById(R.id.username_search_input);
        updateText = (TextView)masterView.findViewById(R.id.update_text);

        addFriendButton = (Button)masterView.findViewById(R.id.add_friend_button);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* check to make sure the username field has text in it
                   and then send the username to the server to request an add
                   for the current user */
                search();

            }
        });

        return masterView;
    }

    /**
     * Method: Search
     * Use: check if there is text in the edit text field. If so, sends a request to add a friend to
     * the server for the current user.
     */
    private void search() {
        username =  usernameSearchInput.getText().toString();
        if(username != "") { // user did not enter text.
            updateText.setText("No username entered");
        }else{ // user entered text, send an add friend request to the server.
            addFriendByUsername(username);
        }

    }

    private void addFriendByUsername(String username) {
        String[] params = new String[5];
        params[0] = "add_friend"; //param to let the connector know what to do.
        params[1] = username; //first param of the method that is ran in the connector
        params[2] = "currentUser"; //let the server know who is requesting an add friend
        Connector connector = new Connector();
        connector.execute(params);

    }
}

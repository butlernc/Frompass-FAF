package com.noahbutler.mrdiscovery.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.noahbutler.mrdiscovery.Network.Connector;
import com.noahbutler.mrdiscovery.R;

import java.util.zip.Inflater;

/**
 * Created by Noah Butler on 8/20/2015.
 */
public class LoginFragment extends Fragment {

    private EditText usernameInput;
    private EditText passwordInput;

    private Button signUpButton;
    private Button signInButton;
    private Button listButton;

    private String username;
    private String password;
    private final String CONNECTOR_STATE = "login";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_login, container, false);

        signInButton = (Button)mainView.findViewById(R.id.signin_button);
        signUpButton = (Button)mainView.findViewById(R.id.goto_signup_button);
        listButton   = (Button)mainView.findViewById(R.id.goto_listfriends);

        usernameInput = (EditText)mainView.findViewById(R.id.username_input_login);
        passwordInput = (EditText)mainView.findViewById(R.id.password_input_login);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = usernameInput.getText().toString();
                password = passwordInput.getText().toString();

                if(username != "" && password != "") {
                    Log.e("INVALID", "You haven't entered a username and/or password you fuck!");
                }else {

                    String[] params = new String[3];
                    params[0] = CONNECTOR_STATE;
                    params[1] = username;
                    params[2] = password;
                    Connector connector = new Connector();
                    connector.execute(params);
                }
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_content_area, new SignUpFragment()).commit();
            }
        });

        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().beginTransaction().replace(R.id.main_content_area, new FriendsListFragment()).commit();
            }
        });

        return mainView;
    }

}

package com.noahbutler.mrdiscovery.Network;


import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 * Created by Noah Butler on 8/20/2015.
 */
public class GetMethod {

    public void getJSONFromUrl( String url ) {
        URL myurl = null;
        String jsoncode = "";

        try {
            myurl = new URL(url);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        try {
            URLConnection myconn =myurl.openConnection();
            InputStream in= new BufferedInputStream(myconn.getInputStream());
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            String line;
            StringBuilder sb = new StringBuilder();
            while((line=br.readLine())!=null)
            {
                sb.append(line);
                //Toast.makeText(getApplicationContext(), "i enter here",Toast.LENGTH_LONG).show();
            }
            jsoncode = sb.toString();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Log.d("JSON", "-->" + jsoncode);
        // System.out.println("json from server:\n" + jsoncode );
    }
}

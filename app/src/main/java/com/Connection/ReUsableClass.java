package com.Connection;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by default on 2/15/2018.
 */

public class ReUsableClass {

    public static String putMethod(String setRequestMethod,String postParameters,String...urls){

        Log.i("post", urls[0]);
        Log.i("postParameters", postParameters);

        String result = "";

        URL urlToRequest;

        String token;

        HttpURLConnection urlConnection = null;



        try {

            urlToRequest = new URL(urls[0]);

            if(urls.length > 1) {
                token = urls[1];
            } else {
                token = null;
            }

            urlConnection = (HttpURLConnection) urlToRequest.openConnection();

            urlConnection.setRequestProperty("Content-type", "application/json");

            urlConnection.setRequestProperty("token", token);

            urlConnection.setRequestMethod(setRequestMethod);

            urlConnection.setDoOutput(true);

            DataOutputStream dStream = new DataOutputStream(urlConnection.getOutputStream());

            dStream.writeBytes(postParameters);

            dStream.flush();

            dStream.close();

            if ( urlConnection.getResponseCode() == 200) {

                InputStream inputStream = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();

                while (data != -1) {

                    char currentCharacter = (char) data;

                    result += currentCharacter;

                    data = reader.read();
                }

                return result;

            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return  result;
    }

    public static String postMethod(String setRequestMethod,String postParameters,String...urls){

        Log.i("post", urls[0]);

        String result = "";

        URL urlToRequest;

        String token;

        String para = "{email:mausam495@gmail.com,password:mausam123}";

        HttpURLConnection urlConnection = null;

        try {

            urlToRequest = new URL(urls[0]);

            if(urls.length > 1) {
                token = urls[1];
            } else {
                token = null;
            }

            urlConnection = (HttpURLConnection) urlToRequest.openConnection();

            urlConnection.setRequestProperty("Content-type", "application/json");

            urlConnection.setRequestMethod(setRequestMethod);

            //urlConnection.setRequestProperty("token", token);

            urlConnection.setDoOutput(true);

            DataOutputStream dStream = new DataOutputStream(urlConnection.getOutputStream());

            dStream.writeBytes(para);

            dStream.flush();

            dStream.close();

            Log.i("responseCode", String.valueOf(urlConnection.getResponseCode()));

            if ( urlConnection.getResponseCode() == 200) {

                InputStream inputStream = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(inputStream);

                int data = reader.read();

                while (data != -1) {

                    char currentCharacter = (char) data;

                    result += currentCharacter;

                    data = reader.read();
                }

                return result;

            }

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();
        }

        return  result;
    }


    public static String getMethod(String setRequestMethod,String... urls) {

        String result = "";

        URL url;
        String token;

        HttpURLConnection connection = null;

        try {

            url = new URL(urls[0]);

            if(urls.length > 1) {
                token = urls[1];
            } else {
                token = null;
            }

            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod(setRequestMethod);

            connection.setRequestProperty("token", token);

            int responseCode = connection.getResponseCode();

            InputStream inputStream = connection.getInputStream();

            InputStreamReader reader = new InputStreamReader(inputStream);

            int data = reader.read();

            while (data != -1) {

                char currentCharacter = (char) data;

                result += currentCharacter;

                data = reader.read();

            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}

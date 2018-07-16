package com.example.saurabhagarwal.stockmarket.helper;



import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.saurabhagarwal.stockmarket.Activity.Predict;
import com.example.saurabhagarwal.stockmarket.Activity.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.example.saurabhagarwal.stockmarket.helper.URL_HELPER.uri;

/**
 * Created by Saurabh Agarwal on 03-01-2018.
 */

public class HttpPostAsyncTask extends AsyncTask<String, String, String> {
    private static final String TAG = "";
    public static String ans;
    // This is the JSON body of the post
    JSONObject postData = new JSONObject();

    {
        JSONObject value = new JSONObject();
        value.put("Date", Predict.date);
        value.put("Month", "1");
        value.put("Company", Predict.company);
        value.put("Open", "1");
        value.put("High", "1");
        value.put("Low", "1");
        value.put("Close", "1");
        value.put("Prev Close", Predict.close);
        value.put("Adj Close", "1");
        value.put("Volume", "1");

        JSONArray input1 = new JSONArray();
        input1.put(value);

        JSONObject Inputs = new JSONObject();
        Inputs.put("input1", input1);

        postData.put("Inputs", Inputs);

        Log.d("check", "fetchDataFromServer:" + postData);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
    }

    // This is a constructor that allows you to pass in the JSON body
    public HttpPostAsyncTask() throws Exception {

    }


    // This is a function that we are overriding from AsyncTask. It takes Strings as parameters because that is what we defined for the parameters of our async task
    @Override
    protected String doInBackground(String... params) {

        try {
            // This is getting the url from the string we passed in
            URL url = new URL(uri);

            // Create the urlConnection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();


            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            urlConnection.setRequestProperty("Content-Type", "application/json");

            urlConnection.setRequestMethod("POST");


            // OPTIONAL - Sets an authorization header
            urlConnection.setRequestProperty("Authorization", "Bearer wPwczcJRkd4LZVNG5DI0gokTVOssvqNASTgoytMHSDXkgs01oj7FCrpIsweB+efDo0rYs7Cyk6fQVFiyXxrDGw==");

            // Send the post body
            if (this.postData != null) {
                OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
                writer.write(postData.toString());
                writer.flush();
            }

            int statusCode = urlConnection.getResponseCode();

            if (statusCode == 200) {


                BufferedReader bf = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String value = bf.readLine();
                System.out.println("result is: " + value);
                Log.d("123", value);
                // From here you can convert the string to JSON with whatever JSON parser you like to use
                JSONObject object = new JSONObject(value);

                JSONObject result = (JSONObject) object.get("Results");
                JSONArray output = (JSONArray) result.get("output1");

                JSONObject array = output.getJSONObject(0);

                ans = array.getString("Scores for quantile :0.750");
                //Toast.makeText(this, "ans", Toast.LENGTH_SHORT).show();
                Log.d("json", ans);

                // After converting the string to JSON, I call my custom callback. You can follow this process too, or you can implement the onPostExecute(Result) method

            } else {
                // Status code is not 200
                // Do something to handle the error
            }

        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
            System.out.println(e);
        }
        return null;
    }


}
package com.example.nasa;

import android.text.TextUtils;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public final class QueryUtils {

        /*
         Empty constructor
         */
        public QueryUtils() {

        }

        /*
                Extracting by using all helper methods
         */
     public static  ArrayList<Apod> fetchData(String stringUrl){
         URL url = createUrl(stringUrl);
         String jsonResponse ;
         ArrayList<Apod> apods = new ArrayList<>();


         jsonResponse = makeHttpsConnection(url);

         try {
             apods = extractJsonData(jsonResponse);
         } catch (JSONException e) {
             e.printStackTrace();
         }


         return apods;
     }

    /*
     Converting string to url object
     */

        private static URL createUrl(String stringUrl) {
            URL url= null;

            try {
                url = new URL(stringUrl);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return url;
        }

        /*
            fetching string from the url given
         */
        private  static String makeHttpsConnection(URL url){
            // Initializing json response string
             String jsonResponse = "";
            if(url== null){
                return jsonResponse;
            }

            HttpsURLConnection urlConnection=null;
            InputStream iStream = null;
            try {
                urlConnection = (HttpsURLConnection) url.openConnection();
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                //setting request method type
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                //Fetch String json response from the input stream
                if(urlConnection.getResponseCode()==200){
                 iStream = urlConnection.getInputStream();
                    jsonResponse = readFromInputStream(iStream);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(urlConnection!=null){
                    urlConnection.disconnect();
                }
                if(iStream!=null){
                    try {
                        iStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

               return jsonResponse;
        }

        /**
           return Jsonstring to the makehttpconnection method
         */

        private static String readFromInputStream(InputStream iStream) throws IOException {
            StringBuilder strBuilder = new StringBuilder();
            if(iStream!=null){
                InputStreamReader inputStreamReader = new InputStreamReader(iStream,
                        Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line= reader.readLine();
                while(line!=null){
                    strBuilder.append(line);
                    line= reader.readLine();

                }
            }

            return strBuilder.toString();
        }

        private static ArrayList<Apod> extractJsonData(String jsonString) throws JSONException {
            if(TextUtils.isEmpty(jsonString)){
                return null;
            }
            ArrayList<Apod> apods = new ArrayList<>();


            //parsing the jsonString to fetch desired data
            JSONArray jsonArray = new JSONArray(jsonString);



                 for(int i=0 ;i<jsonArray.length();i++) {

                     JSONObject jsonObject = jsonArray.getJSONObject(i);
                     String title = jsonObject.getString("title");
                     String url = jsonObject.getString("url");
                     String explanation = jsonObject.getString("explanation");
                     String date = jsonObject.getString("date");

                     apods.add(new Apod(date, title, explanation, url));
                 }



            return apods;
        }

}




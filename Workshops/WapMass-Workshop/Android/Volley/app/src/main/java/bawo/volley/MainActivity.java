package bawo.volley;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private final static String URL_String = "http://magadistudio.com/complete-android-developer-course-source-files/string.html";
    private final static String JSON_ARRAY_URL = "https://hacklab2018-14718.firebaseio.com/hospitals.json";
    private final static  String JSON_OBJ_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";

    private RequestQueue queue;
    private ArrayList data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //RequestQueue queue = Volley.newRequestQueue(this);
        queue = Volley.newRequestQueue(this);
        data = new ArrayList();
        getFacilities(JSON_ARRAY_URL);


    }


    //Get Movies
    private void getFacilities(String url) {
        JsonArrayRequest facilities = new JsonArrayRequest(Request.Method.GET,
                url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0; i< 2; i++){
                    try {
                        JSONObject  facilityObject = response.getJSONObject(i);
                        data.add(facilityObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(facilities);
    }
    private void getJsonObject(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {


                    // Log.d("Object: ", response.getString("type").toString());

                    JSONObject metadata = response.getJSONObject("metadata");

                    Log.d("Metadata: ", metadata.toString());
                    Log.d("MetaInfo: ", metadata.getString("generated"));
                    Log.d("MetaInfo: ", metadata.getString("url"));
                    Log.d("MetaInfo: ", metadata.getString("title"));


                    /// jsonArray
                    JSONArray features = response.getJSONArray("features");

                    for (int i = 0; i < features.length(); i++) {

                        //Get objects
                        JSONObject propertiesObj = features.getJSONObject(i).getJSONObject("properties");

                        Log.d("Place:", propertiesObj.getString("place"));


                    }






                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);


    }


    public void getStringObject(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("My String: ", response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }


}

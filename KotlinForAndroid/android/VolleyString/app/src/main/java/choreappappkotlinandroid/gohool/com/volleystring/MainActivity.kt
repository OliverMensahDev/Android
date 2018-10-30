package choreappappkotlinandroid.gohool.com.volleystring

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    val earthquakeLink = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/1.0_hour.geojson"
    val movieLink = "http://netflixroulette.net/api/api.php?director=Quentin%20Tarantino"
    val stringLink = "https://www.magadistudio.com" +
            "/complete-android-developer-course-source-files/string.html"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        volleyRequest = Volley.newRequestQueue(this)
       // getString(stringLink)
        //getJsonArray(movieLink)
        getJsonObject(earthquakeLink)


    }

    fun getJsonObject(Url: String) {
        val jsonObject = JsonObjectRequest(Request.Method.GET, Url,
                Response.Listener {
                    response: JSONObject ->
                    try {

                        val metadataObject = response.getJSONObject("metadata")
                        Log.d("Metadata:", metadataObject.getString("title"))


                        //Get the features array
                        val featuresArray =
                                response.getJSONArray("features")
                        for (i in 0.. featuresArray.length() - 1) {
                            //Get the object within
                            val propertyObj = featuresArray.getJSONObject(i)
                                    .getJSONObject("properties")

                            Log.d("Properties Place: ", propertyObj.getString("place"))
                        }




                    }catch (e:JSONException) {e.printStackTrace()}
                },
                Response.ErrorListener {  })

        volleyRequest!!.add(jsonObject)
    }

    fun getJsonArray(Url: String){
        val jsonArray = JsonArrayRequest(Request.Method.GET, Url,
                Response.Listener {
                    response: JSONArray -> try {

                    Log.d("Response", response.toString())

                    for (i in 0..response.length() - 1) {

                        val movieObj = response.getJSONObject(i)

                        var movieName = movieObj.get("show_title")
                        Log.d("MovieName:", movieName.toString())
                    }


                    } catch (e: JSONException){ e.printStackTrace()}
                },
                Response.ErrorListener {
                    error: VolleyError? ->
                      try {
                          Log.d("Error", error.toString())

                      }catch (e: JSONException) {
                          e.printStackTrace()
                      }
                })

        volleyRequest!!.add(jsonArray)
    }

    fun getString(Url: String){

        val stringReq = StringRequest(Request.Method.GET, Url,
                Response.Listener<String> {
                    response: String? ->
                      try {
                          Log.d("Stuff", response.toString())

                      }catch (e: JSONException){
                          e.printStackTrace()
                      }

                }, Response.ErrorListener {
                   error: VolleyError? ->
                        try {
                            Log.d("Error:", error.toString())

                        }catch (e: JSONException) {
                            e.printStackTrace()
                        }



        })

        volleyRequest!!.add(stringReq)


    }


}

package volleyapp.gohool.com.volleyapplication

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
    val stringLink = "https://www.magadistudio.com" +
    "/complete-android-developer-course-source-files/string.html"

    val movieLink = "http://netflixroulette.net/api/api.php?director=Quentin%20Tarantino"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        volleyRequest = Volley.newRequestQueue(this)

        //getString(stringLink)
        //getJsonArray(movieLink)
        getJsonObject(earthquakeLink)


    }
    fun getJsonObject(Url: String) {
        val  jsonObjectReq = JsonObjectRequest(Request.Method.GET, Url,
                Response.Listener {
                    response: JSONObject ->
                    try {

                        val metadataObject = response.getJSONObject("metadata")
                        var title = metadataObject.getString("title")

//                        var type = response.getString("type")

                        //Get the features jsonarray
                        val featuresArray = response.getJSONArray("features")
                        for (i in 0..featuresArray.length() - 1) {
                            //Get the properties object
                            val propertyObj = featuresArray.getJSONObject(i).getJSONObject("properties")
                            var place = propertyObj.getString("place")


                            //Geometry object
                            val geometryObj = featuresArray.getJSONObject(i).getJSONObject("geometry")

                            val coordJsonArray = geometryObj.getJSONArray("coordinates")

                            //Loop through our coordinates array
                            //for (g in 0..coordJsonArray.length() - 1) {

                                val firstCoor = coordJsonArray.getDouble(0)

                                Log.d("Coordinate: ", firstCoor.toString())



                            //}




                            Log.d("Place", place)
                        }
//
                       //Log.d("Title:", title)

                    }catch (e: JSONException){ e.printStackTrace()}
                },
                Response.ErrorListener {
                    error: VolleyError? ->
                    try {
                        Log.d("Error", error.toString())


                    }catch (e: JSONException) {
                        e.printStackTrace()
                    }

                })

        volleyRequest!!.add(jsonObjectReq)
    }

    fun getJsonArray(Url: String) {

        val jsonArray = JsonArrayRequest(Request.Method.GET, Url,
                Response.Listener {
                    response: JSONArray ->
                    try {
                        Log.d("Response:===>>", response.toString())

                        for (i in 0..response.length() - 1) {
                            val movieObj = response.getJSONObject(i)

                            var showTitle = movieObj.getString("show_title")
                            var released = movieObj.getString("release_year")

                            Log.d("Movie Title: ", showTitle + " Year: "
                                    + released)


                        }

                    }catch (e: JSONException) {e.printStackTrace()}
                },

                Response.ErrorListener {
                    error: VolleyError? ->
                    try {
                        Log.d("Error:", error.toString())


                    }catch (e: JSONException) {e.printStackTrace()}
                })

        volleyRequest!!.add(jsonArray)
    }

    fun getString(Url: String) {

        val stringReq = StringRequest(Request.Method.GET, Url,
                Response.Listener {
                    response: String? ->
                    try {

                        Log.d("Response: ", response)


                    }catch (e: JSONException) {
                        e.printStackTrace()
                    }

                },
                Response.ErrorListener {
                    error: VolleyError? ->
                    try {
                        Log.d("Error:", error.toString())

                    }catch (e: JSONException) {e.printStackTrace()}
                })

        volleyRequest!!.add(stringReq)

    }
}

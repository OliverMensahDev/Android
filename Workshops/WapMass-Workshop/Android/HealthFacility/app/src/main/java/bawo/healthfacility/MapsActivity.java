package bawo.healthfacility;


import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import bawo.healthfacility.Model.Facility;
import bawo.healthfacility.UI.CustomInfoWindow;
import bawo.healthfacility.Util.Constants;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private RequestQueue queue;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private BitmapDescriptor[] iconColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        iconColors = new BitmapDescriptor[] {
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE),
                BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)
        };
        queue = Volley.newRequestQueue(this);
        getFacilities();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setInfoWindowAdapter(new CustomInfoWindow(getApplicationContext()));
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMarkerClickListener(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (Build.VERSION.SDK_INT < 23) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //Ask for permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


            } else {
                // we have permission!
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                        .title(""));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 8));

            }
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0]
                == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED)

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
    }


    /**
     * Get All Facilities Objects
     */
    public void getFacilities() {
        final Facility facility = new Facility();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, Constants.URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < Constants.LIMIT; i++) {
                                JSONObject facilityObject = response.getJSONObject(i);
                                Log.d("facility", facilityObject.toString());
                                facility.setDistrict(facilityObject.getString("District"));
                                facility.setFacilityName(facilityObject.getString("FacilityName"));
                                facility.setLatitude(facilityObject.getLong("Latitude"));
                                facility.setLongitude(facilityObject.getLong("Longitude"));
                                facility.setOwnership(facilityObject.getString("Ownership"));
                                facility.setRegion(facilityObject.getString("Region"));
                                facility.setTown(facilityObject.getString("Town"));
                                facility.setType(facilityObject.getString("Type"));

                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.icon(iconColors[Constants.randomInt(iconColors.length, 0)]);
                                markerOptions.title(facility.getFacilityName());
                                markerOptions.position(new LatLng(facility.getLatitude(), facility.getLongitude()));

//                                CircleOptions circleOptions = new CircleOptions();
//                                circleOptions.center(new LatLng(facility.getLatitude(), facility.getLongitude()));
//                                circleOptions.radius(30000);
//                                circleOptions.strokeWidth(2.5f);
//                                circleOptions.fillColor(Color.RED);
//
//                                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//                                mMap.addCircle(circleOptions);

                                Marker marker = mMap.addMarker(markerOptions);
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(facility.getLatitude(), facility.getLongitude()), 1));
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("err", error.getMessage());
            }
        });
        queue.add(jsonArrayRequest);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {

        getQuakeDetails(marker.getTag().toString());
    }

    private void getQuakeDetails(String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String detailsUrl = "";

                try {
                    JSONObject properties = response.getJSONObject("properties");
                    JSONObject products = properties.getJSONObject("products");
                    JSONArray geoserve = products.getJSONArray("geoserve");

                    for (int i = 0; i < geoserve.length(); i++) {
                        JSONObject geoserveObj = geoserve.getJSONObject(i);

                        JSONObject contentObj = geoserveObj.getJSONObject("contents");
                        JSONObject geoJsonObj = contentObj.getJSONObject("geoserve.json");

                        detailsUrl = geoJsonObj.getString("url");


                    }
                    Log.d("URL: ", detailsUrl);

                    getMoreDetails(detailsUrl);


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


    public void getMoreDetails(String url) {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                dialogBuilder = new AlertDialog.Builder(MapsActivity.this);
                View view = getLayoutInflater().inflate(R.layout.popup, null);

                Button dismissButton = (Button) view.findViewById(R.id.dismissPop);
                Button dismissButtonTop = (Button) view.findViewById(R.id.desmissPopTop);
                TextView popList = (TextView) view.findViewById(R.id.popList);
                WebView htmlPop = (WebView) view.findViewById(R.id.htmlWebview);

                StringBuilder stringBuilder = new StringBuilder();


                try {

                    if (response.has("tectonicSummary") && response.getString("tectonicSummary") != null) {

                        JSONObject tectonic = response.getJSONObject("tectonicSummary");

                        if (tectonic.has("text") && tectonic.getString("text") != null) {

                            String text = tectonic.getString("text");

                            htmlPop.loadDataWithBaseURL(null, text, "text/html", "UTF-8", null);

                            Log.d("HTML", text);

                        }

                    }


                    JSONArray cities = response.getJSONArray("cities");

                    for (int i = 0; i < cities.length(); i++) {
                        JSONObject citiesObj = cities.getJSONObject(i);

                        stringBuilder.append("City: " + citiesObj.getString("name")
                                + "\n" + "Distance: " + citiesObj.getString("distance")
                                + "\n" + "Population: "
                                + citiesObj.getString("population"));

                        stringBuilder.append("\n\n");

                    }

                    popList.setText(stringBuilder);

                    dismissButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dismissButtonTop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });

                    dialogBuilder.setView(view);
                    dialog = dialogBuilder.create();
                    dialog.show();




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


    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }
}


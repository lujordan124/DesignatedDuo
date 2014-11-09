package com.example.peter.designatedduo;

<<<<<<< HEAD
/**
 * Created by Eric on 11/8/2014.
 */
public class MyGPSActivity {
}
=======
import android.app.Activity;
import android.content.Context;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;


import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * Created by Eric on 11/9/2014.
 */
public class MyGPSActivity extends Activity  implements LocationListener {

    LocationServices locService;
    private GoogleMap map;
    private MapFragment mapFrag;
    private LocationRequest locRequest;
    private LocationListener locListener;
    private LocationManager locManager;
    private People me;
    LatLng currentLocation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_gps);

        mapFrag = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        map = mapFrag.getMap();

        locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location initialLocation = locManager.getLastKnownLocation(locManager.NETWORK_PROVIDER);
        LatLng initialLatLng = new LatLng(initialLocation.getLatitude(),initialLocation.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(initialLatLng, 18));
        map.addMarker(new MarkerOptions().position(initialLatLng).visible(true));
    }

    public void onLocationChanged(Location location) {
        LatLng newLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLng(newLatLng));
    }

    public People getPeople() {
        return me;
    }
}


>>>>>>> 3f6f2f6c2291e394645e3b327e9ae8cb16ab8ef5

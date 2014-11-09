package com.example.peter.designatedduo;

import android.location.Location;
import android.os.AsyncTask;

/**
 * Created by Eric on 11/9/2014.
 */
public class ASyncMe extends AsyncTask<Void, People, Void> {
    private Location currentLocation;
    private MyGPSActivity myGPS;
    private People me;

    public void AsyncMe(MyGPSActivity myGPS) {
        this.myGPS = myGPS;
        me = new People();
    }

    protected Void doInBackground(Void... arg0) {
        while(!this.isCancelled()) {
            me.setLocation(currentLocation);
            myGPS.getPeople().setLocation(currentLocation);
            publishProgress(me);
        }
        return null;
    }

}

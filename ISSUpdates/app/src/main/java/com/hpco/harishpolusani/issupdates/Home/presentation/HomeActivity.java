package com.hpco.harishpolusani.issupdates.Home.presentation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import com.hpco.harishpolusani.issupdates.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HomeActivity extends AppCompatActivity implements LocationListener {
    private Unbinder mUnbinder;
    private String issFragment = "IssFragment";
    private LocationManager locationManager;
    private boolean isGPSEnabled;
    private static final long MIN_TIME_BW_UPDATES = 6000;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
    private android.location.Location location = null;
    private HomeFragment mHomeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getLocation();
        if(location!=null) {
            mHomeFragment = (HomeFragment) getFragmentManager().findFragmentByTag(issFragment);
            if (mHomeFragment == null) {
                mHomeFragment = HomeFragment.newInstance();
                Bundle bundle = new Bundle();
                if (location != null) {
                    bundle.putString("lat", String.valueOf(location.getLatitude()));
                    bundle.putString("lon", String.valueOf(location.getLongitude()));
                    mHomeFragment.setArguments(bundle);
                    getFragmentManager().beginTransaction().add(R.id.home_container, mHomeFragment, issFragment).commit();
                } else {
                    // Handle in case the location object is null

                }
            } else {

                getFragmentManager().beginTransaction().add(R.id.home_container, mHomeFragment, issFragment).commit();
            }
        }

    }

    /**
     * get current location of the device .
     */
    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);

        } else {
            location = getCurrentLocation();
        }

    }

    /**
     * Here only GPS was used  as mentioned in the requirement that by using GPS location , we can also use network or GPS.
     * @return  Location of the current device .
     */
    private Location getCurrentLocation() {

        try {
            locationManager = (LocationManager) this
                    .getSystemService(LOCATION_SERVICE);
            if (locationManager != null) {
                isGPSEnabled = locationManager
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);
                if (!isGPSEnabled) {

                } else {


                    if (location == null) {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                             return null;
                        }
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATES,
                                MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
                            if (locationManager != null) {
                                location = locationManager
                                        .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            }
                        }

                }}
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;
    }

    /**
     *
     * @param requestCode
     * @param permissions  array of permission , which can be used to check what all permissions are granted.
     * @param grantResults  number of permission granted by the user.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {
                        getLocation();
                        mHomeFragment = (HomeFragment) getFragmentManager().findFragmentByTag(issFragment);
                        if (mHomeFragment == null) {
                            mHomeFragment = HomeFragment.newInstance();
                            Bundle bundle = new Bundle();
                            if (location != null) {
                                bundle.putString("lat", String.valueOf(location.getLatitude()));
                                bundle.putString("lon", String.valueOf(location.getLongitude()));
                                mHomeFragment.setArguments(bundle);
                                getFragmentManager().beginTransaction().add(R.id.home_container, mHomeFragment, issFragment).commit();
                            } else {
                                // Handle in case the location object is null

                            }
                        }
                    }


                } else {

                    ///implement if permission got denied

                }
                return;
            }

        }
    }
    @Override
    protected void onStop() {
        mUnbinder.unbind();
        super.onStop();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if(mHomeFragment!=null) {
            getFragmentManager().beginTransaction().remove(mHomeFragment).commit();
        }
        super.onConfigurationChanged(newConfig);
    }

    /**
     *
     * @param location api passes the location object when there is an update . Can be used to update the location.
     */
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
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
}

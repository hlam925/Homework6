package com.example.home.homework6;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by Home on 4/5/16.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, AddPinDialogFragment.AddPinListener {

    public static final String ARG_USER = "MapFragment.User";

    private MapView mMapView;

    private GoogleMap mMap;

    private String mUserName;

    private ArrayList<Pin> mPins;

    private UserSQLiteHelper mUserSQLiteHelper;

    private double mLatitude;

    private double mLongitude;


    public static MapFragment newInstance(String name) {
        MapFragment mapFragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USER, name);
        mapFragment.setArguments(args);
        return mapFragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        mUserName = this.getArguments().toString();
//
//        mUserSQLiteHelper = UserSQLiteHelper.getuInstance(getActivity().getApplicationContext());
//
//        mPins = mUserSQLiteHelper.getAllPins(mUserName);
//        for(int i=0; i < mPins.size(); i++){
//            placePin(mPins.get(i));
//        }
//
//
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        mMapView = (MapView) rootView.findViewById(R.id.fragment_map_mapview);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);

        mUserName = this.getArguments().toString();

        mUserSQLiteHelper = UserSQLiteHelper.getuInstance(getActivity().getApplicationContext());

        mPins = mUserSQLiteHelper.getAllPins(mUserName);
        for (int i = 0; i < mPins.size(); i++) {
            placePin(mPins.get(i));
        }

//        mapView.getMapAsync(this);


        return rootView;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
//                Toast.makeText(getActivity(), latLng.toString(), Toast.LENGTH_SHORT).show();
                mLatitude = latLng.latitude;
                mLongitude = latLng.longitude;
                FragmentManager fm = getActivity().getSupportFragmentManager();
                AddPinDialogFragment addPinDialogFragment = AddPinDialogFragment.newInstance();
                addPinDialogFragment.show(fm, getActivity().getString(R.string.addpindialogfragment_title_text));

            }
        });


    }


    public void placePin(Pin pin) {
        if (mMap != null) {
            LatLng latLng = new LatLng(pin.getLat(), pin.getLng());
            String title = pin.getTitle();
            String snippet = pin.getSnippet();

            Marker marker = mMap.addMarker(new MarkerOptions()
                            .position(latLng)
                            .title(title)
                            .snippet(snippet)
            );

        }
    }

    @Override
    public void onAddPinClicked(String title, String snippet) {
        Pin pin = new Pin(mLatitude, mLongitude, title, snippet, mUserName);
        mUserSQLiteHelper.insertPin(pin);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}

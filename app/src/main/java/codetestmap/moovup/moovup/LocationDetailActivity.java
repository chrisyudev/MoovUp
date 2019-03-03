package codetestmap.moovup.moovup;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orhanobut.hawk.Hawk;

public class LocationDetailActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ImageView backButton;
    private ImageView image_view;
    private TextView title;
    private Entity entity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        backButton = (ImageView) findViewById(R.id.back);
        image_view = (ImageView) findViewById(R.id.image_view);
        title = (TextView) findViewById(R.id.title);

        entity = Hawk.get("data_item");
        title.setText(entity.name +"\n" + entity.email);

        Glide.with(this).load(entity.picture).into(image_view);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationDetailActivity.this.onBackPressed();
            }
        });
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
        entity = Hawk.get("data_item");
        Log.i("yoyoyo", entity.location.latitude + "  " + entity.location.longitude);
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(entity.location.latitude, entity.location.longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title(entity.name));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
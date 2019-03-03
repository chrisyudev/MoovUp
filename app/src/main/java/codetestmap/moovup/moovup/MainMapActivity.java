package codetestmap.moovup.moovup;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainMapActivity extends FragmentActivity implements OnMapReadyCallback {
    List<Entity> data = new ArrayList<>();

    ListView listView;
    MainListViewAdapter mAdapter;
    TextView mapViewButton;
    TextView  listViewButton;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        listView = findViewById(R.id.list_view);
        listViewButton = (TextView) findViewById(R.id.button_list_view);

        data = Hawk.get("data");

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        listViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainMapActivity.this.onBackPressed();
                //v.getContext().startActivity(new Intent(MainMapActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME));
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.i("yoyo", "yoyo");
        mMap = googleMap;
        data = Hawk.get("data");

        Log.i("data", "data" + data.size());

        for(int i = 0; i < data.size(); i++) {
            Entity entity = data.get(i);
            LatLng sydney = new LatLng(entity.location.latitude, entity.location.longitude);
            mMap.addMarker(new MarkerOptions().position(sydney).title(entity.name));
            Log.i("addmarker", "addmarker " + i);
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }
    }
}

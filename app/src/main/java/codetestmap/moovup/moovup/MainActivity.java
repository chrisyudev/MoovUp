package codetestmap.moovup.moovup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.orhanobut.hawk.Hawk;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MainListViewAdapter mAdapter;
    TextView mapViewButton;
    TextView  listViewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_view);
        mapViewButton = (TextView) findViewById(R.id.button_map_view);

        mapViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(MainActivity.this, MainMapActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_TASK_ON_HOME));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(Hawk.get("data") == null) {
            ((ControllerApplication) this.getApplication()).getMoovUpService().listRepos().enqueue(new Callback<List<Entity>>() {
                @Override
                public void onResponse(Response<List<Entity>> response, Retrofit retrofit) {
                    mAdapter = new MainListViewAdapter(response.body(), MainActivity.this);
                    listView.setAdapter(mAdapter);
                    listView.setOnItemClickListener(mAdapter);
                    Hawk.put("data", response.body());
                }

                @Override
                public void onFailure(Throwable t) {
                }
            });
        } else {
            mAdapter = new MainListViewAdapter((List<Entity>)Hawk.get("data"), MainActivity.this);
            listView.setAdapter(mAdapter);
            listView.setOnItemClickListener(mAdapter);
        }
    }
}

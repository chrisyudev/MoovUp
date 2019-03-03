package codetestmap.moovup.moovup;

import android.app.Application;

import com.orhanobut.hawk.Hawk;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class ControllerApplication extends Application {

    private static ControllerApplication instance;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://www.json-generator.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

   private  MoovUpService moovUpService = retrofit.create(MoovUpService.class);
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Hawk.init(this.getApplicationContext()).build();
    }

    public static synchronized ControllerApplication getInstance() {
        return instance;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public MoovUpService getMoovUpService() {
        return moovUpService;
    }
}


package codetestmap.moovup.moovup;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

public interface  MoovUpService {
    @GET("api/json/get/cfdlYqzrfS")
    Call<List<Entity>> listRepos();
}

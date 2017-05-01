package service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import service.model.MoviesModel;

/**
 * Created by henriquemalvezecardoso on 16/04/17.
 */

public interface Services {

    @GET("3/movie/popular?")
    Call<MoviesModel> listRepos(@Query("api_key") String key);
}

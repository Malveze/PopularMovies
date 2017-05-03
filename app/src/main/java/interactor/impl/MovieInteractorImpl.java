package interactor.impl;

import java.util.List;

import interactor.MovieInteractor;
import presenter.HomeMoviePresenter;
import presenter.impl.HomeMoviePresenterImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import service.Services;
import model.Movie;
import model.MoviesModel;

/**
 * Created by henriquemalvezecardoso on 11/04/17.
 */

public class MovieInteractorImpl implements MovieInteractor {

    String BASE_URL;
    String apiKey;

    private List<Movie> movies;
    private HomeMoviePresenter presenter;

    public MovieInteractorImpl(HomeMoviePresenterImpl homeMoviePresenterImpl, String BASE_URL, String apiKey){
        this.presenter = homeMoviePresenterImpl;
        this.BASE_URL = BASE_URL;
        this.apiKey = apiKey;
    }

    @Override
    public List<Movie> getMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Services services = retrofit.create(Services.class);

        Call<MoviesModel> call = services.listRepos(apiKey);
        call.enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(Call<MoviesModel> call, Response<MoviesModel> response) {
                if(response.isSuccessful()){
                    movies = response.body().getResults();
                    presenter.populateView(movies);
                }
            }

            @Override
            public void onFailure(Call<MoviesModel> call, Throwable t) {

            }
        });

        return movies;
    }
}

package presenter.impl;

import android.content.Context;
import android.os.Bundle;

import ui.view.HomeMovieView;

import java.util.ArrayList;
import java.util.List;

import interactor.MovieInteractor;
import interactor.impl.MovieInteractorImpl;
import presenter.HomeMoviePresenter;
import model.Movie;

/**
 * Created by henriquemalvezecardoso on 11/04/17.
 */

public class HomeMoviePresenterImpl implements HomeMoviePresenter {

    private MovieInteractor homeMovieInteractor;
    private HomeMovieView view;
    private String titleParameter;
    private String posterParameter;
    private String overViewParameter;
    private String releaseDateParameter;
    private String trailerParameter;
    private String userRating;
    private Context applicationContext;
    private List<Movie> moviesRight = new ArrayList<>();
    private List<Movie> moviesLeft = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();

    @Override
    public void onCreate(HomeMovieView view, Context applicationContext, String BASE_URL, String apiKey,
                         String titleParameter, String posterParameter, String overViewParameter, String releaseDateParameter,
                         String trailerParameter, String userRating) {
        homeMovieInteractor = new MovieInteractorImpl(this, BASE_URL, apiKey);
        this.titleParameter = titleParameter;
        this.posterParameter = posterParameter;
        this.overViewParameter = overViewParameter;
        this.releaseDateParameter = releaseDateParameter;
        this.trailerParameter = trailerParameter;
        this.userRating = userRating;
        this.view = view;
        this.applicationContext = applicationContext;
        getMovies();
    }

    @Override
    public void populateView(List<Movie> movies) {
        this.movies = movies;
        for(int i=0; i<movies.size(); i++){
            if((i % 2) == 0){
                moviesRight.add(movies.get(i));
            }else{
                moviesLeft.add(movies.get(i));
            }
        }

        view.initialyzeRecyclerView(movies, moviesLeft, moviesRight);
    }

    @Override
    public void openMovieDetail(int itemClicked, boolean imageLeftClicked) {
        Bundle bundle = new Bundle();

        if(imageLeftClicked){
            bundle.putString(titleParameter, moviesLeft.get(itemClicked).getTitle());
            bundle.putString(posterParameter, moviesLeft.get(itemClicked).getPosterPath());
            bundle.putString(overViewParameter, moviesLeft.get(itemClicked).getOverview());
            bundle.putString(releaseDateParameter, moviesLeft.get(itemClicked).getReleaseDate());
            bundle.putDouble(userRating, moviesLeft.get(itemClicked).getVoteAverage());
            bundle.putBoolean(trailerParameter, moviesLeft.get(itemClicked).getVideo());
        }else{
            bundle.putString(titleParameter, moviesRight.get(itemClicked).getTitle());
            bundle.putString(posterParameter, moviesRight.get(itemClicked).getPosterPath());
            bundle.putString(overViewParameter, moviesRight.get(itemClicked).getOverview());
            bundle.putString(releaseDateParameter, moviesRight.get(itemClicked).getReleaseDate());
            bundle.putDouble(userRating, moviesRight.get(itemClicked).getVoteAverage());
            bundle.putBoolean(trailerParameter, moviesRight.get(itemClicked).getVideo());
        }

        view.openDetailActivity(bundle);
    }

    @Override
    public void sorteByTopRated() {
        List<Movie> moviesSorted = new ArrayList<>();
        Movie movieFixedPostion;
        Movie movieCurrentPostion;
        moviesSorted.addAll(movies);

        for(int i=0; i<movies.size(); i++){
            for(int j=i; j<movies.size(); j++){
                if(moviesSorted.get(j).getVoteAverage() > moviesSorted.get(i).getVoteAverage()){
                    movieFixedPostion = moviesSorted.get(i);
                    movieCurrentPostion = moviesSorted.get(j);
                    moviesSorted.remove(i);
                    moviesSorted.add(i, movieCurrentPostion);
                    moviesSorted.remove(j);
                    moviesSorted.add(j, movieFixedPostion);
                }
            }
        }

        for(int i=0; i<movies.size(); i++){
            if((i % 2) == 0){
                moviesRight.add(moviesSorted.get(i));
            }else{
                moviesLeft.add(moviesSorted.get(i));
            }
        }
    }

    @Override
    public void sortByMostPopular() {
        for(int i=0; i<movies.size(); i++){
            if((i % 2) == 0){
                moviesRight.add(movies.get(i));
            }else{
                moviesLeft.add(movies.get(i));
            }
        }
    }

    public void getMovies(){
        homeMovieInteractor.getMovies();
    }
}

package presenter.impl;

import android.content.Context;
import android.os.Bundle;

import ui.view.HomeMovieView;

import java.util.ArrayList;
import java.util.List;

import interactor.MovieInteractor;
import interactor.impl.MovieInteractorImpl;
import presenter.HomeMoviePresenter;
import service.model.Movie;

/**
 * Created by henriquemalvezecardoso on 11/04/17.
 */

public class HomeMoviePresenterImpl implements HomeMoviePresenter {

    private MovieInteractor homeMovieInteractor;
    private HomeMovieView view;
    private Context applicationContext;
    private List<String> postersRight = new ArrayList<>();
    private List<String> postersLeft = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();

    @Override
    public void onCreate(HomeMovieView view, Context applicationContext) {
        homeMovieInteractor = new MovieInteractorImpl(this);
        this.view = view;
        this.applicationContext = applicationContext;
        getMovies();
    }

    @Override
    public void populateView(List<Movie> movies) {
        this.movies = movies;
        for(int i=0; i<movies.size(); i++){
            if((i % 2) == 0){
                postersLeft.add(movies.get(i).getPosterPath());
            }else{
                postersRight.add(movies.get(i).getPosterPath());
            }
        }

        view.initialyzeRecyclerView(movies, postersLeft, postersRight);
    }

    @Override
    public void openMovieDetail(int itemClicked) {
        Bundle bundle = new Bundle();

        bundle.putString("title", movies.get(itemClicked).getTitle());
        bundle.putString("poster", movies.get(itemClicked).getPosterPath());
        bundle.putString("overview", movies.get(itemClicked).getOverview());
        bundle.putString("releaseDate", movies.get(itemClicked).getReleaseDate());

        view.openDetailActivity(bundle);
    }

    public void getMovies(){
        homeMovieInteractor.getMovies();
    }
}

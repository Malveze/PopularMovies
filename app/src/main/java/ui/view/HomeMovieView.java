package ui.view;

import android.os.Bundle;

import java.util.List;

import model.Movie;

/**
 * Created by henriquemalvezecardoso on 24/04/17.
 */

public interface HomeMovieView {

    void initialyzeRecyclerView(List<Movie> movies, List<Movie> moviesLeft, List<Movie> moviesRight);
    void openDetailActivity(Bundle bundle);
    void hideProgressBar();
    void showProgressBar();
}

package ui.view;

import android.os.Bundle;

import java.util.List;

import service.model.Movie;

/**
 * Created by henriquemalvezecardoso on 24/04/17.
 */

public interface HomeMovieView {

    void initialyzeRecyclerView(List<Movie> movies, List<Movie> postersLeft, List<Movie> postersRight);
    void openDetailActivity(Bundle bundle);
}

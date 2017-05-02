package presenter;

import android.content.Context;

import ui.view.HomeMovieView;

import java.util.List;

import service.model.Movie;

/**
 * Created by henriquemalvezecardoso on 11/04/17.
 */

public interface HomeMoviePresenter {
    void onCreate(HomeMovieView view, Context applicationContext, String BASE_URL, String apiKey,
                  String titleParameter, String posterParameter, String overViewParameter, String releaseDateParameter,
                  String trailerParameter);
    void populateView(List<Movie> movies);
    void openMovieDetail(int itemClicked, boolean imageLeftClicked);
}

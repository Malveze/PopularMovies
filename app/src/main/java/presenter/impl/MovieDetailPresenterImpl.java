package presenter.impl;

import presenter.MovieDetailPresenter;
import ui.view.MovieDetailView;

/**
 * Created by henriquemalvezecardoso on 26/04/17.
 */

public class MovieDetailPresenterImpl implements MovieDetailPresenter {

    private MovieDetailView view;

    public MovieDetailPresenterImpl(MovieDetailView view){
        this.view = view;
    }

    @Override
    public void onCreate() {
        view.populateMovieDetail();
    }
}

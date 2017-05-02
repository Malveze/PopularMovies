package presenter.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import presenter.MovieDetailPresenter;
import ui.view.MovieDetailView;

/**
 * Created by henriquemalvezecardoso on 26/04/17.
 */

public class MovieDetailPresenterImpl implements MovieDetailPresenter {

    private MovieDetailView view;
    private String releaseDate;
    private Boolean hasTrailer;

    public MovieDetailPresenterImpl(MovieDetailView view){
        this.view = view;
    }

    @Override
    public void onCreate(String releaseDate, Boolean hasTrailer) {
        this.releaseDate = releaseDate;
        this.hasTrailer = hasTrailer;

        if(!hasTrailer){
            view.showNoTrailerMessage();
        }

        view.populateMovieDetail(getMovieYear());
    }

    public String getMovieYear(){
        String[] movieReleaseDateSplit = releaseDate.split("-");

        return movieReleaseDateSplit[0];
    }
}

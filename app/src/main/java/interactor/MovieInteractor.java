package interactor;

import java.util.List;

import service.model.Movie;

/**
 * Created by henriquemalvezecardoso on 11/04/17.
 */

public interface MovieInteractor {
    List<Movie> getMovies();
}
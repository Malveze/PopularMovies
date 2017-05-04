package ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.Date;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import presenter.MovieDetailPresenter;
import presenter.impl.MovieDetailPresenterImpl;
import ui.view.MovieDetailView;

/**
 * Created by henriquemalvezecardoso on 08/04/17.
 */

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    private Toolbar toolbar;
    private String title;
    private String overview;
    private String poster;
    private String releaseDate;
    private Double userRating;
    private Boolean hasTrailer;
    private GoogleApiClient client;
    private MovieDetailPresenter presenter;

    @BindView(R.id.movie_title)
    TextView movieTitle;

    @BindView(R.id.movie_poster)
    ImageView moviePoster;

    @BindView(R.id.movie_description)
    TextView movieDescription;

    @BindView(R.id.movie_release_date)
    TextView movieReleaseDate;

    @BindView(R.id.no_trailer_text)
    TextView noTrailerMessage;

    @BindView(R.id.user_rating)
    TextView userRatingTextView;

    @BindString(R.string.poster_base_url)
    String posterBaseUrl;

    @BindString(R.string.movie_detail_title)
    String movieDetailTitle;

    @BindString(R.string.bundle_title_parameter)
    String titleParameter;

    @BindString(R.string.bundle_poster_parameter)
    String posterParameter;

    @BindString(R.string.bundle_overview_parameter)
    String overViewParameter;

    @BindString(R.string.bundle_releaseDate_parameter)
    String releaseDateParameter;

    @BindString(R.string.bundle_trailer_parameter)
    String trailerParameter;

    @BindString(R.string.bundle_user_rating)
    String userRatingParameter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_layout);

        ButterKnife.bind(this);

        title = getIntent().getExtras().getString(titleParameter);
        poster = getIntent().getExtras().getString(posterParameter);
        overview = getIntent().getExtras().getString(overViewParameter);
        releaseDate = getIntent().getExtras().getString(releaseDateParameter);
        hasTrailer = getIntent().getExtras().getBoolean(trailerParameter);
        userRating = getIntent().getExtras().getDouble(userRatingParameter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(movieDetailTitle);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        presenter = new MovieDetailPresenterImpl(this);
        presenter.onCreate(releaseDate, hasTrailer);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void populateMovieDetail(String movieYear) {
        movieTitle.setText(title);
        movieDescription.setText(overview);
        movieReleaseDate.setText(releaseDate);
        userRatingTextView.setText(String.valueOf(userRating));
        Picasso.with(this).load(posterBaseUrl + poster).into(moviePoster);
    }

    @Override
    public void showNoTrailerMessage() {
        noTrailerMessage.setVisibility(View.VISIBLE);
    }
}

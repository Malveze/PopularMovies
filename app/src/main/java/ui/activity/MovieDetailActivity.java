package ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_layout);

        ButterKnife.bind(this);

        title = getIntent().getExtras().getString("title");
        poster = getIntent().getExtras().getString("poster");
        overview = getIntent().getExtras().getString("overview");
        releaseDate = getIntent().getExtras().getString("releaseDate");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MovieDetail");
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        presenter = new MovieDetailPresenterImpl(this);
        presenter.onCreate();
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
    public void populateMovieDetail() {
        movieTitle.setText(title);
        movieDescription.setText(overview);
        movieReleaseDate.setText(releaseDate);
        Picasso.with(this).load("http://image.tmdb.org/t/p/w185/" + poster).into(moviePoster);
    }
}

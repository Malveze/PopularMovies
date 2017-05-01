package ui.activity;

import android.content.Intent;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import ui.adapter.MoviesListAdapter;
import com.example.android.popularmovies.R;
import ui.view.HomeMovieView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import presenter.HomeMoviePresenter;
import presenter.impl.HomeMoviePresenterImpl;
import service.model.Movie;

public class HomeMovieActivity extends AppCompatActivity implements HomeMovieView, MoviesListAdapter.ListItemClickListener{

    @BindView(R.id.progress_bar)
    RelativeLayout progressBar;

    @BindView(R.id.list_movie_recycler_view)
    RecyclerView listMovieRecyclerView;

    @BindView(R.id.linear_image_left)
    LinearLayout imageLeft;

    @BindView(R.id.linear_image_right)
    LinearLayout imageRight;

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    private Display display;
    private Point size;
    private HomeMoviePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_movie_layout);

        ButterKnife.bind(this);

        progressBar.setVisibility(View.VISIBLE);

        presenter = new HomeMoviePresenterImpl();

        presenter.onCreate(this, getApplicationContext());
    }

    @Override
    public void onItemClickListener(int itemClicked) {
        presenter.openMovieDetail(itemClicked);
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
    public void initialyzeRecyclerView(List<Movie> movies, List<String> postersLeft, List<String> postersRight) {
        progressBar.setVisibility(View.GONE);
        listMovieRecyclerView.setVisibility(View.VISIBLE);

        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);

        recyclerView = (RecyclerView)findViewById(R.id.list_movie_recycler_view);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new MoviesListAdapter(this, getApplicationContext(), postersLeft, postersRight);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void openDetailActivity(Bundle bundle) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}

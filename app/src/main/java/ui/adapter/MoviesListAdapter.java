package ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.android.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import model.Movie;

/**
 * Created by henriquemalvezecardoso on 05/04/17.
 */

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.ViewHolder> {

    private Context context;
    private List<Movie> moviesLeft;
    private List<Movie> moviesRight;
    final private ListItemClickListener myOnClickListener;

    public MoviesListAdapter(ListItemClickListener onClickListener, Context context,
                             List<Movie> moviesLeft, List<Movie> moviesRight){
        this.myOnClickListener = onClickListener;
        this.context = context;
        this.moviesLeft = moviesLeft;
        this.moviesRight = moviesRight;
    }

    public interface ListItemClickListener{
        void onItemClickListener(int itemClicked, boolean imageLeftClicked);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public ImageView launcher1;
        public ImageView launcher2;
        public LinearLayout linearLayoutLeft;
        public LinearLayout linearLayoutRight;

        public ViewHolder(View v) {
            super(v);

            launcher1 = (ImageView) v.findViewById(R.id.launcher_1);
            launcher2 = (ImageView) v.findViewById(R.id.launcher_2);
            linearLayoutLeft = (LinearLayout) v.findViewById(R.id.linear_image_left);
            linearLayoutRight = (LinearLayout) v.findViewById(R.id.linear_image_right);

            v.findViewById(R.id.linear_image_left).setOnClickListener(this);
            v.findViewById(R.id.linear_image_right).setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int itemClicked = getAdapterPosition();
            boolean imageLeftClicked = false;

            if(view.getId() == linearLayoutLeft.getId()){
                imageLeftClicked = true;
            }

            myOnClickListener.onItemClickListener(itemClicked, imageLeftClicked);
        }
    }

    @Override
    public MoviesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_cells, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(position < moviesLeft.size()){
            Picasso.with(context).load("http://image.tmdb.org/t/p/w185/" + moviesLeft.get(position).getPosterPath()).into(holder.launcher1);
            Picasso.with(context).load("http://image.tmdb.org/t/p/w185/" + moviesRight.get(position).getPosterPath()).into(holder.launcher2);
        }
    }

    @Override
    public int getItemCount() {
        return moviesLeft.size();
    }
}

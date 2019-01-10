package com.riotfallen.moviedirectory.adapter.recyler;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.riotfallen.moviedirectory.BuildConfig;
import com.riotfallen.moviedirectory.R;
import com.riotfallen.moviedirectory.activity.DetailMovieActivity;
import com.riotfallen.moviedirectory.core.model.movie.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    private Context context;
    private List<Result> movies;

    public MovieListAdapter(Context context, List<Result> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieListAdapter.MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MovieListViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item_movie, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieListViewHolder movieListViewHolder, int i) {
        final int position = i;
        movieListViewHolder.BindItem(movies.get(i));
        movieListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailMovieActivity.class);
                intent.putExtra(DetailMovieActivity.TITLE_MOVIE, movies.get(position).getTitle());
                intent.putExtra(DetailMovieActivity.ID_MOVIE, movies.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieListViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewThumb = itemView.findViewById(R.id.rimImageViewThumbnail);
        private TextView textViewTitle = itemView.findViewById(R.id.rimTextViewTitle);
        private TextView textViewLanguage = itemView.findViewById(R.id.rimTexTViewLanguage);
        private TextView textViewRating = itemView.findViewById(R.id.rimTextViewRating);
        private TextView textViewVote = itemView.findViewById(R.id.rimTextViewVoting);

        MovieListViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void BindItem(Result movie){
            textViewTitle.setText(movie.getTitle());
            textViewRating.setText(String.format(context.getResources().getString(R.string.dummy_rating),movie.getVoteAverage()));
            textViewVote.setText(String.format(context.getResources().getString(R.string.voting), movie.getVoteCount()));
            Picasso.get().load(BuildConfig.IMAGE_BASE_URL + movie.getBackdropPath()).fit().centerCrop().into(imageViewThumb);
            textViewLanguage.setText(movie.getOverview());
        }

    }
}
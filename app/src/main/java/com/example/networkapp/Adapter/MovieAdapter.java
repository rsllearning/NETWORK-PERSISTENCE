package com.example.networkapp.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.networkapp.R;
import com.example.networkapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    ArrayList<Movie> movieList;
    Context context;


    public MovieAdapter(ArrayList<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new MovieHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
         ProgressBar progressBar=holder.itemView.findViewById(R.id.progressBar);
          Movie movie=movieList.get(position);
          progressBar.setVisibility(View.VISIBLE);
          Glide.with(context).load(movie.getPoster()).listener(new RequestListener<Drawable>() {
              @Override
              public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                  progressBar.setVisibility(View.GONE);
                  return false;
              }

              @Override
              public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                  progressBar.setVisibility(View.GONE);
                  return false;
              }
          }).into(holder.imageView);
          holder.textViewTitle.setText(movieList.get(position).getTitle());
          holder.textViewCategory.setText(movieList.get(position).getCategory());

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {

            ImageView imageView;
            TextView textViewTitle;
            TextView textViewCategory;
        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.movieImage);
            textViewTitle=itemView.findViewById(R.id.title);
            textViewCategory=itemView.findViewById(R.id.caterory);


        }
    }
}

package com.example.assignment_10.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_10.R;
import com.example.assignment_10.model.Movie;
import com.example.assignment_10.util.ErrorHandler;

import java.util.List;
import android.content.Context;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    //constants for views:
    private static final int VIEW_TYPE_LIST = 0;
    private static final int VIEW_TYPE_DETAILS = 1;

    // interface for item click handling
    public interface OnItemClickListener { void onItemClick(int position); }

    private final List<Movie> movies;
    private final OnItemClickListener listener;
    private final Context context;

    /**
     * Constructor to initialize the adapter with menu data
     */
    public MovieAdapter(Context context, List<Movie> movies, OnItemClickListener listener)
    {
        this.context = context;
        this.listener = listener;
        this.movies = movies;
    }
    @Override
    public int getItemViewType(int position) {
        if (position >= 0 && position < this.movies.size()) {
            if (this.movies.get(position) != null) {
                return VIEW_TYPE_LIST;
            } else {
                return VIEW_TYPE_DETAILS;
            }
        }
        return -1; // Invalid view type
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_cards, parent, false);
        return new MovieViewHolder(view, this.listener);
    }

    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        try
        {
            Movie movie = movies.get(position);

            //set fields:
            holder.getTitleTextView().setText(movie.getTitle());
            if (movie.getYear() == 0)
                holder.getYearTextView().setText("N/A"); // year is set as 0 only if the data inside the JSON file is inaccurate (=> mark as N/A)
            else holder.getYearTextView().setText(String.valueOf(movie.getYear()));
            holder.getGenreTextView().setText(movie.getGenre());
            holder.getPosterImageView().setImageResource(R.drawable.placeholder_poster);
        }
        catch (Exception e)
        {
            ErrorHandler.logError("MovieAdapter", "Error binding movie data at position " + position, e);
            // Graceful fallback
            holder.getTitleTextView().setText("Error displaying movie.");
            holder.getYearTextView().setText("");
            holder.getGenreTextView().setText("Error displaying genre.");
            holder.getPosterImageView().setImageResource(R.drawable.placeholder_poster);
        }

    }
    @Override
    public int getItemCount()
    {
        return (movies != null) ? movies.size() : 0;
    }

    public Movie getMovie(int position)
    {
        if (position >= 0 && position < movies.size())
        {
            return movies.get(position);
        }
        return null;
    }
}

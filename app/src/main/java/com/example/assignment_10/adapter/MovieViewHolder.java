package com.example.assignment_10.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_10.R;

/*
 * ViewHolder for menu items in the RecyclerView
 */
public class MovieViewHolder extends RecyclerView.ViewHolder{
    private final ImageView posterImageView;
    private final TextView titleTextView;
    private final TextView yearTextView;
    private final TextView genreTextView;

    public MovieViewHolder(@NonNull View movieView, final MovieAdapter.OnItemClickListener listener)
    {
        super(movieView);
        posterImageView = movieView.findViewById(R.id.imageViewPoster);
        titleTextView = movieView.findViewById(R.id.textViewTitle);
        yearTextView = movieView.findViewById(R.id.textViewYear);
        genreTextView = movieView.findViewById(R.id.textViewGenre);

        // set click listener for the entire item
        movieView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && listener != null)
                {
                    listener.onItemClick(position);
                }
            }
        });
    }

    public ImageView getPosterImageView(){ return posterImageView;}
    public TextView getTitleTextView(){ return titleTextView; }
    public TextView getYearTextView(){ return yearTextView; }
    public TextView getGenreTextView(){ return genreTextView; }
}

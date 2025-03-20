package com.example.assignment_10;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.assignment_10.model.Movie;
import com.example.assignment_10.util.ErrorHandler;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String TAG = "MovieDetailActivity";
    // intent extras
    public static final String EXTRA_MOVIE = "extra_movie"; // ---- keep/remove???

    // UI components
    private ImageView movieImageView;
    private TextView movieTitleTextView, movieGenreTextView, movieYearTextView;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_movie_detail);

        // initialize UI components
        initializeViews();

        // display movie details
        displayMovieDetails();

        // set up back button
        backButton.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View view){ finish(); } });

    }

    /**
     * Initialize UI components
     */
    private void initializeViews()
    {
        movieImageView = findViewById(R.id.imageViewDetailsPoster);
        movieTitleTextView = findViewById(R.id.textViewDetailsTitle);
        movieGenreTextView = findViewById(R.id.textViewDetailsGenre);
        movieYearTextView = findViewById(R.id.textViewDetailsYear);
        backButton = findViewById(R.id.buttonBack);
    }

    /**
     * display movie details
     */
    private void displayMovieDetails()
    {
        try
        {
            //get movie from intent
            Movie movie =(Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
            if (movie == null)
            {
                Log.e(TAG,"MOVIEDETAILACTIVITY.JAVA LINIA 70");
                throw new IllegalArgumentException("Movie not found.");
            }

            // display details
            movieTitleTextView.setText(movie.getTitle());
            if (movie.getYear() == 0)
                movieYearTextView.setText("N/A"); // year is set as 0 only if the data inside the JSON file is inaccurate (=> mark as N/A)
            else movieYearTextView.setText(String.valueOf(movie.getYear()));
            movieGenreTextView.setText(movie.getGenre());

            //load image
            try
            {
                int imageResourceId = ErrorHandler.getDrawableResourceId(this,movie.getPosterResource(), android.R.drawable.ic_menu_gallery);
                movieImageView.setImageResource(imageResourceId);
            }
            catch (Exception e)
            {
                movieImageView.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                Log.e(TAG, "Error loading image", e);
            }

        }
        catch (Exception e)
        {
            Log.e(TAG, "Error displaying movie details", e);
            ErrorHandler.handleError(this, e, "Failed to display movie details. Please try again.");
            //finish activity if we can't display details:
            finish();
        }

    }
}

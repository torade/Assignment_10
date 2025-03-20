package com.example.assignment_10;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_10.adapter.MovieAdapter;
import com.example.assignment_10.model.Movie;
import com.example.assignment_10.util.ErrorHandler;
import com.example.assignment_10.util.JsonUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MovieAdapter.OnItemClickListener{

    private static final String TAG = "MainActivity";
    // UI components
    private RecyclerView moviesRecyclerView;

    // data
    private List<Movie> movies;
    private MovieAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        initializeViews();

        // Load and display data
        loadMovieData();
    }

    /**
     * Initialize UI components
     */
    public void initializeViews()
    {
        moviesRecyclerView = findViewById(R.id.recyclerViewList);
        moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        moviesRecyclerView.setHasFixedSize(true);
    }

    public void loadMovieData()
    {
        try
        {
            //load movie data from JSON file
            movies = JsonUtils.loadMoviesFromJson(this, R.raw.movies);

            //create and set adapter
            adapter = new MovieAdapter(this,movies,this);
            moviesRecyclerView.setAdapter(adapter);
        }
        catch(Exception e)
        {
            Log.e(TAG, "Error loading movie data", e);
            ErrorHandler.handleError(this, e, "Failed to load movie data. Please try again later.");
        }
    }

    /**
     * Handle item click
     */
    @Override
    public void onItemClick(int position)
    {
        try
        {
            Movie movie = adapter.getMovie(position);
            if (movie != null)
            {
                // launch detail activity
                Intent intent = new Intent(this, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, movie);
                startActivity(intent);
            }
        }
        catch (Exception e)
        {
            Log.e(TAG, "Error handling item click", e);
            ErrorHandler.handleError(this, e, "Failed to open item details. Please try again.");
        }
    }
}
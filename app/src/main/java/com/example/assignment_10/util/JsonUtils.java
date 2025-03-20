package com.example.assignment_10.util;

import android.content.Context;
import android.util.Log;

import com.example.assignment_10.R;
import com.example.assignment_10.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "JsonUtils";
    public static List<Movie> loadMoviesFromJson(Context context, int resourceId) throws IOException, JSONException
    {
        // read the JSON file content
        String jsonContent = readJsonFile(context, resourceId);

        // parse the JSON content
        JSONArray jsonArray = new JSONArray(jsonContent);
        List<Movie> movieList = new ArrayList<>();

        // parse movies

        for (int i = 0; i < jsonArray.length(); i++)
        {
            try
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Movie movie = new Movie();
                // setting title
                if (jsonObject.has("title"))
                    movie.setTitle(jsonObject.getString("title"));
                else
                {
                    movie.setTitle("Unknown title"); // default value for movies with no title
                    Log.e(TAG, "No title provided");
                }

                // setting year
                if (jsonObject.has("year"))
                {
                    try
                    {
                        int year = jsonObject.getInt("year");
                        if (year < 0)
                        {
                            movie.setYear(0);
                            Log.w(TAG, "Invalid year value: " + year + "=> setting to default (0)");
                        }
                        else movie.setYear(year);
                    }
                    catch(JSONException e)
                    {
                        movie.setYear(0); // default value for movies with invalid years
                        Log.e(TAG,"Invalid year value for movie: " + movie.getTitle());
                    }
                }
                else
                {
                    movie.setYear(0); // default value for movies with invalid years
                    Log.w(TAG, "Movie has no year mentioned.");
                }

                // setting genre
                if (jsonObject.has("genre"))
                    movie.setGenre(jsonObject.getString("genre"));
                else
                {
                    movie.setGenre("N/A"); // default value for movies with unknown genre
                    Log.w(TAG,"Movie has no genre mentioned.");
                }

                // setting poster
                if (jsonObject.has("poster"))
                    movie.setPosterResource(jsonObject.getString("poster"));
                else
                {
                    movie.setPosterResource("N/A");
                    Log.w(TAG, "Movie has no poster resource mentioned.");
                }

                // adding movie to list
                movieList.add(movie);
            }
            catch (JSONException e)
            {
                Log.e(TAG, "Error parsing category at index " + i, e);
            }
        }
        return movieList;
    }

    private static void handleJsonException(Exception exception)
    {
        Log.e(TAG, "Error parsing JSON", exception);
    }


    // Helper method to read a JSON file from resources
    private static String readJsonFile(Context context, int resourceId) throws IOException
    {
        StringBuilder stringBuilder = new StringBuilder();
        try
        {
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line);
            }
            reader.close();
        }
        catch (IOException e)
        {
            Log.e(TAG, "Error reading JSON file", e);
            throw e;
        }
        return stringBuilder.toString();
    }
}

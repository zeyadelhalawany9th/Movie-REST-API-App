package com.example.movieapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String JSON_URL = "https://run.mocky.io/v3/4af5fb2e-ce25-4cc3-aa37-dcc7fb9c9001";

    
    private List<Movie> movieList;

    RecyclerView recyclerView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieList = new ArrayList<>();

        recyclerView = findViewById(R.id.recyclerView1);

        GetData getData = new GetData();
        getData.execute();
    }


    public class GetData extends AsyncTask<String, String, String>
    {

        @Override
        protected String doInBackground(String... strings)
        {

            String current = "";

            try
            {
                URL url;
                HttpURLConnection httpURLConnection = null;

                try
                {
                    url = new URL(JSON_URL);
                    httpURLConnection = (HttpURLConnection) url.openConnection();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                    int data = inputStreamReader.read();

                    while (data != -1)
                    {
                        current = current + (char) data;
                        data = inputStreamReader.read();

                    }

                    return current;

                }
                catch (Exception e)
                {
                    e.printStackTrace();

                }

                finally
                {
                    if(httpURLConnection != null)
                    {
                        httpURLConnection.disconnect();
                    }
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();

            }

            return current;
        }

        @Override
        protected void onPostExecute(String s)
        {
            try
            {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("moviz");

                for(int i = 0; i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    Movie movie = new Movie();
                    movie.setName(jsonObject1.getString("name"));
                    movie.setImageURL(jsonObject1.getString("image"));

                    movieList.add(movie);

                }

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            putDataIntoRecyclerView(movieList);
        }
    }

    private void putDataIntoRecyclerView(List<Movie> movieList)
    {
        MovieListAdapter movieListAdapter = new MovieListAdapter(MainActivity.this, movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(movieListAdapter);

    }
}
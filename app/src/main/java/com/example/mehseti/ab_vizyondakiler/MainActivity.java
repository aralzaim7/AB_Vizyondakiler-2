package com.example.mehseti.ab_vizyondakiler;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> movies;
    Intent intent,login;
  //  String API_URL = "https://www.themoviedb.org/discover/movie?primary_release_date.gte=2014-09-15&primary_release_date.lte=2014-10-22";
    ListView movieList = null;
    TextView txt;
    AsyncTask movie;
    String API_URL = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=4cb3fb226777cd0826959de794a5fba2";
    Movie movieDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = findViewById(R.id.movie_listView);





        class MovieAsync extends AsyncTask<Void,Void,String>
        {

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(API_URL);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        StringBuilder stringBuilder = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            stringBuilder.append(line).append("\n");
                            String s = "";
                        }
                        bufferedReader.close();
                        return stringBuilder.toString();
                    }
                    finally{
                        urlConnection.disconnect();
                    }
                }
                catch(Exception e) {
                    Log.e("HATA", e.getMessage(), e);

                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                if(s == null) {
                    s = "HATA";
                }

                Log.i("INFO", s);



                try {

                    JSONObject object = (JSONObject) new JSONTokener(s).nextValue();
                    JSONArray uniObject = object.getJSONArray("results");

                    String title = null;
                    String img = null;
                    String overView = null;

                    movies = new ArrayList<>();
                    for(int i=0;i<uniObject.length();i++)
                    {
                        JSONObject jb1 = uniObject.getJSONObject(i);
                        title = jb1.getString("title");
                        img = jb1.getString("poster_path");
                        overView = jb1.getString("overview");
                        int id = jb1.getInt("id");
                        double voteAverage = jb1.getDouble("vote_average");
                        String originalLanguage = jb1.getString("original_language");

                        movies.add(i,new Movie(id,title,overView,img,originalLanguage,voteAverage));
                        Log.i("INF:", title+img+overView+id);
                    }
                    final MoviewAdapter movieAdapter = new MoviewAdapter(getApplicationContext(), 0, movies);
                    movieList.setAdapter(movieAdapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }

        new MovieAsync().execute();

        // ****************APİSİZ HALİ***********************************
        final MoviewAdapter movieAdapter = new MoviewAdapter(getApplicationContext(), 0, createDummyMovieData());
        movieList.setAdapter(movieAdapter);


        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie movie = (Movie) movieList.getItemAtPosition(i);
                intent = new Intent(MainActivity.this, MovieDetails.class);
                intent.putExtra("movieDetails", movie);
                startActivity(intent);
            }
        });



        movieList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                login = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(login);
                return true;
            }
        });


    }





       public ArrayList<Movie> createDummyMovieData() {
        movies = new ArrayList<>();


        movies.add(0, new Movie(1, "It", "In a small town in Maine, seven children known as The Losers Club come face to face with life problems, bullies and a monster that takes the shape of a clown called Pennywise.",  R.drawable.it,"en",7));
        movies.add(1, new Movie(2, "Dunkirk", "The story of the miraculous evacuation of Allied soldiers from Belgium, Britain, Canada and France, who were cut off and surrounded by the German army from the beaches and harbour of Dunkirk between May 26th and June 4th 1940 during World War II.", R.drawable.dunkirk_px,"en",6.8));
        //movies.add(2, new Movie(3, "Untouchables", "trajikomik", "tırı pırı", R.mipmap.ic_launcher_round));
        return movies;
    }


}

package com.example.mehseti.ab_vizyondakiler;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.mehseti.ab_vizyondakiler.databinding.ActivityMovieDetailsBinding;
import com.squareup.picasso.Picasso;


public class MovieDetails extends AppCompatActivity {

    Intent intent;
    TextView detail_txt;
    ActivityMovieDetailsBinding binding;
    String img_path = "https://image.tmdb.org/t/p/w500/";
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_movie_details);
        intent = getIntent();
        Movie movie = (Movie) intent.getSerializableExtra("movieDetails");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_details);
        binding.setMovie(movie);
        imageView = findViewById(R.id.imageViewCover);
        imageView.setImageResource(movie.getImageCodeDrawable());
        //setImagecontent(imageView,movie);

    }

    @BindingAdapter(value = "url")
    public  void setImagecontent(ImageView imageMovie, Movie movie)
    {
        Picasso.with(imageMovie.getContext())
                .load(img_path+movie.imageCode)
                .into(imageMovie);
    }
}

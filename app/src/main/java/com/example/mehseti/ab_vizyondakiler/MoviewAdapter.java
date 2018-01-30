package com.example.mehseti.ab_vizyondakiler;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


public class MoviewAdapter extends ArrayAdapter<Movie> {
    String img_path = "https://image.tmdb.org/t/p/w500/";


    public MoviewAdapter(Context context, int resource, List<Movie> objects) {

        super(context, resource, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie_item,parent,false);
        }
        TextView name =  convertView.findViewById(R.id.name);
        ImageView imageMovie =  convertView.findViewById(R.id.img_movie);

        name.setText(movie.name);
        setImagecontent(imageMovie,movie);
        //imageMovie.setImageResource(movie.getImageCodeDrawable());

        return  convertView;
    }


    public  void setImagecontent(ImageView imageMovie,Movie movie)
    {
        Picasso.with(imageMovie.getContext())
                .load(img_path+movie.imageCode)
                .into(imageMovie);
    }
}

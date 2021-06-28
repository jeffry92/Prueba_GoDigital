package com.example.prueba_godigital.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prueba_godigital.DetailMovie;
import com.example.prueba_godigital.ObjetArrayLlist.Movie;
import com.example.prueba_godigital.ObjetArrayLlist.Results;
import com.example.prueba_godigital.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.ViewHolderMovie> {

    ArrayList<Results> movies;
    Context mcontext;

    public AdapterMovie(ArrayList<Results> movies, Context context) {
        this.movies = movies;
        this.mcontext = context;
    }

    @Override
    public AdapterMovie.ViewHolderMovie onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie,parent,false);
        return new ViewHolderMovie(view);
    }

    @Override
    public void onBindViewHolder(AdapterMovie.ViewHolderMovie holder, int position) {
        holder.assignData(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolderMovie extends RecyclerView.ViewHolder {

        ImageButton imageView;

        public ViewHolderMovie(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
        }

        public void assignData(Results movie) {
            String buil = "https://image.tmdb.org/t/p/w500" + movie.getPoster_path();
            try {
                Picasso.get().load(buil).fit().into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_SEND);
                    intent.putExtra("descrip", movie.getOverview());
                    intent.putExtra("image", movie.getBackdrop_path());
                    intent.putExtra("subtitle",movie.getOriginal_title());
                    intent.putExtra("name",movie.getTitle());
                    intent.setClass(mcontext, DetailMovie.class);
                    mcontext.startActivity(intent);

                }
            });
        }
    }
}

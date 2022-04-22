package com.example.oscarapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.oscarapp.R;
import com.example.oscarapp.helpers.ImageAsyncTask;
import com.example.oscarapp.models.Movie;

import java.util.List;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.MyViewHolder> {
    private List<Movie> listMovie;

    public AdapterMovie(List<Movie> list) {
        this.listMovie = list;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, gender;
        ImageView photo;
        ProgressBar progressBar;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.nameView);
            gender = view.findViewById(R.id.genderView);
            photo = view.findViewById(R.id.imageViewMovie);
            progressBar = view.findViewById(R.id.progressBarMovie);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list_movie, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie obj = listMovie.get(position);
        holder.name.setText(obj.getNome());
        holder.gender.setText(obj.getGenero());
        ImageAsyncTask task = new ImageAsyncTask(holder.progressBar, holder.photo);
        task.execute(obj.getFoto());
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }
}

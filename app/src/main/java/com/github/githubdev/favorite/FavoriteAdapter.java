package com.github.githubdev.favorite;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.github.githubdev.GithubWeb;
import com.github.githubdev.R;
import com.github.githubdev.favorite.save.FavoriteContract;
import com.github.githubdev.favorite.save.FavoriteDAO;
import com.github.githubdev.favorite.save.FavoriteData;
import com.github.githubdev.favorite.save.FavoriteDatabase;
import com.github.githubdev.favorite.save.FavoriteOperation;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{

    private Context context;
    private List<FavoriteData> favoriteData = new ArrayList<>();
    private FavoriteContract.view repositoryView;
    private FavoriteData favoriteDelete;

    public FavoriteAdapter(Context context, List<FavoriteData> repositoryResponse, FavoriteContract.view repositoryView) {
        this.context = context;
        this.favoriteData = repositoryResponse;
        this.repositoryView = repositoryView;
    }

    public FavoriteAdapter(Context context) {
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleRepository;
        TextView startRepository;
        TextView forkRepository;
        TextView languageRepository;
        TextView descRepository;
        Button delete;
        Button githubWeb;
        public ViewHolder(@NonNull View view) {
            super(view);
            titleRepository = view.findViewById(R.id.repository_name);
            startRepository = view.findViewById(R.id.repository_stars);
            forkRepository = view.findViewById(R.id.repository_forks);
            languageRepository = view.findViewById(R.id.repository_language);
            descRepository = view.findViewById(R.id.repository_desc);
            delete = view.findViewById(R.id.btn_delete);
            githubWeb = view.findViewById(R.id.btn_favorite_github_web);
        }
        }
    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_design, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final FavoriteAdapter.ViewHolder holder, final int position) {
        holder.titleRepository.setText(favoriteData.get(position).getFullName());
        holder.startRepository.setText(favoriteData.get(position).getStarGazersCount());
        holder.forkRepository.setText(favoriteData.get(position).getForksCount());
        holder.languageRepository.setText(favoriteData.get(position).getLanguage());
        holder.descRepository.setText(favoriteData.get(position).getDescription());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteDelete = favoriteData.get(position);
                repositoryView.deleteRepositoryData(favoriteDelete);
            }
        });
        holder.githubWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gitHubWebView = new Intent(context, GithubWeb.class);
                gitHubWebView.putExtra("load_url", favoriteData.get(position).getLink());
                context.startActivity(gitHubWebView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteData.size();
    }

}

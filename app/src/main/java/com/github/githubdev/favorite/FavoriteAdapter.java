package com.github.githubdev.favorite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.githubdev.languagecolor.ColorChange;
import com.github.githubdev.R;
import com.github.githubdev.favorite.save.FavoriteContract;
import com.github.githubdev.favorite.save.FavoriteData;
import com.github.githubdev.webview.GithubWeb;

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

    @Override
    public void onBindViewHolder(@NonNull final FavoriteAdapter.ViewHolder holder, final int position) {
        holder.titleRepository.setText(favoriteData.get(position).getFullName());
        holder.startRepository.setText(favoriteData.get(position).getStarGazersCount());
        holder.forkRepository.setText(favoriteData.get(position).getForksCount());
        holder.languageRepository.setText(favoriteData.get(position).getLanguage());
        holder.descRepository.setText(favoriteData.get(position).getDescription());
        holder.repositoryCreated.setText(favoriteData.get(position).getCreatedAt());
        holder.repositoryUpdated.setText(favoriteData.get(position).getUpdatedAt());
        holder.repositoryWatchers.setText(favoriteData.get(position).getWatchersCount());
        new ColorChange(holder.languageRepository.getText().toString(), holder.codeLanguage, context);

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
    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_design, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleRepository;
        TextView startRepository;
        TextView forkRepository;
        TextView languageRepository;
        TextView descRepository;
        TextView repositoryUpdated;
        TextView repositoryCreated;
        TextView repositoryWatchers;
        Button delete;
        Button githubWeb;
        ImageView codeLanguage;
        public ViewHolder(@NonNull View view) {
            super(view);
            titleRepository = view.findViewById(R.id.repository_name);
            startRepository = view.findViewById(R.id.repository_stars);
            forkRepository = view.findViewById(R.id.repository_forks);
            languageRepository = view.findViewById(R.id.repository_language);
            descRepository = view.findViewById(R.id.repository_desc);
            delete = view.findViewById(R.id.btn_delete);
            githubWeb = view.findViewById(R.id.btn_favorite_github_web);
            codeLanguage = view.findViewById(R.id.ic_repository_language);
            repositoryCreated = view.findViewById(R.id.repository_created);
            repositoryUpdated = view.findViewById(R.id.repository_update);
            repositoryWatchers = view.findViewById(R.id.repository_watch);
        }
        }

    @Override
    public int getItemCount() {
        return favoriteData.size();
    }

}

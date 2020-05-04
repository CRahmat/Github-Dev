package com.github.githubdev.home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.github.githubdev.languagecolor.ColorChange;
import com.github.githubdev.R;
import com.github.githubdev.favorite.save.FavoriteContract;
import com.github.githubdev.favorite.save.FavoriteData;
import com.github.githubdev.favorite.save.FavoriteDatabase;
import com.github.githubdev.favorite.save.FavoriteOperation;
import com.github.githubdev.webview.GithubWeb;

import java.util.ArrayList;
import java.util.List;

public class MyRepositoryAdapter extends RecyclerView.Adapter<MyRepositoryAdapter.ViewHolder> implements FavoriteContract.view{
    private FavoriteDatabase favoriteDatabase;
    MyRepositoryAdapter myRepositoryAdapter;
    Context context;
    private ArrayList<MyRepositoryResponse> repositoryResponses = new ArrayList<>();
    private List<FavoriteData> favoriteData;
    FavoriteContract.view repositoryView;
    RecyclerView recyclerView;
    FavoriteOperation favoriteOperation;
    public MyRepositoryAdapter(Context context, List<FavoriteData> favoriteData, FavoriteContract.view repositoryView) {
        this.context = context;
        this.favoriteData = favoriteData;
        this.repositoryView = repositoryView;
    }

    public MyRepositoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<MyRepositoryResponse> repositoryResponses){
        this.repositoryResponses.clear();
        this.repositoryResponses.addAll(repositoryResponses);
        notifyDataSetChanged();
    }

    @Override
    public void successAdd() {
        Toast.makeText(context, "Success To Add On Favorite", Toast.LENGTH_SHORT).show();
        favoriteOperation = new FavoriteOperation(this);
        favoriteOperation.readRepositoryData(favoriteDatabase, context);
    }

    @Override
    public void successDelete() {
        Toast.makeText(context, "Success Delete From Favorite", Toast.LENGTH_SHORT).show();
        favoriteOperation = new FavoriteOperation(this);
        favoriteOperation.readRepositoryData(favoriteDatabase, context);
    }

    @Override
    public void getData(List<FavoriteData> listFavoriteDatabase) {
        myRepositoryAdapter = new MyRepositoryAdapter(context, listFavoriteDatabase, this);
    }

    @Override
    public void deleteRepositoryData(final FavoriteData favoriteData) {
        favoriteOperation = new FavoriteOperation(this);
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(context, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(context);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        favoriteOperation.deleteRepositoryData(favoriteData, favoriteDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void dataAlready() {
        Toast.makeText(context, "Repository Already Exist on Favorite", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBindViewHolder(@NonNull final MyRepositoryAdapter.ViewHolder holder, final int position) {
        final FavoriteOperation favoriteOperation = new FavoriteOperation(this);
        holder.titleRepository.setText(repositoryResponses.get(position).getName());
        holder.startRepository.setText(repositoryResponses.get(position).getStarGazersCount());
        holder.forkRepository.setText(repositoryResponses.get(position).getForksCount());
        holder.languageRepository.setText(repositoryResponses.get(position).getLanguage());
        holder.descRepository.setText(repositoryResponses.get(position).getDescription());
        holder.load_url = repositoryResponses.get(position).getLink();
        holder.repositoryCreated.setText(repositoryResponses.get(position).getCreatedAt());
        holder.repositoryUpdated.setText(repositoryResponses.get(position).getCreatedAt());
        holder.repositoryWatchers.setText(repositoryResponses.get(position).getWatchersCount());
        new ColorChange(holder.languageRepository.getText().toString(), holder.codeLanguage, context);
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteOperation.insertRepositoryData(
                        holder.titleRepository.getText().toString(),
                        holder.descRepository.getText().toString(),
                        holder.forkRepository.getText().toString(),
                        holder.startRepository.getText().toString(),
                        holder.languageRepository.getText().toString(),
                        holder.repositoryCreated.getText().toString(),
                        holder.repositoryUpdated.getText().toString(),
                        holder.repositoryWatchers.getText().toString(),
                        holder.load_url,
                        FavoriteDatabase.database(context)
                        );
            }
        });
        holder.viewSourceCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webView = new Intent(context, GithubWeb.class);
                webView.putExtra("load_url", repositoryResponses.get(position).getLink());
                context.startActivity(webView);
            }
        });
        holder.recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webView = new Intent(context, GithubWeb.class);
                webView.putExtra("load_url", repositoryResponses.get(position).getLink());
                context.startActivity(webView);
            }
        });
    }
    @NonNull
    @Override
    public MyRepositoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_design, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        FavoriteDatabase favoriteDatabase;
        TextView titleRepository;
        TextView startRepository;
        TextView forkRepository;
        TextView languageRepository;
        TextView descRepository;
        Button favorite;
        Button viewSourceCode;
        String load_url = new String();
        ImageView codeLanguage;
        TextView repositoryUpdated;
        TextView repositoryCreated;
        TextView repositoryWatchers;
        LinearLayout recyclerView;
        public ViewHolder(@NonNull View view) {
            super(view);
            titleRepository = view.findViewById(R.id.repository_name);
            startRepository = view.findViewById(R.id.repository_stars);
            forkRepository = view.findViewById(R.id.repository_forks);
            languageRepository = view.findViewById(R.id.repository_language);
            descRepository = view.findViewById(R.id.repository_desc);
            favorite = view.findViewById(R.id.btn_add_favorite);
            viewSourceCode = view.findViewById(R.id.btn_myrepository_view_source_code);
            codeLanguage = view.findViewById(R.id.ic_repository_language);
            repositoryCreated = view.findViewById(R.id.repository_created);
            repositoryUpdated = view.findViewById(R.id.repository_update);
            repositoryWatchers = view.findViewById(R.id.repository_watch);
            recyclerView = view.findViewById(R.id.recyclerview_layout);
        }
        }


    @Override
    public int getItemCount() {

        return repositoryResponses.size();
    }

}

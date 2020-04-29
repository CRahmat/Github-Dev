package com.github.githubdev.search;

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
import com.github.githubdev.favorite.save.FavoriteData;
import com.github.githubdev.favorite.save.FavoriteDatabase;
import com.github.githubdev.favorite.save.FavoriteOperation;

import java.util.ArrayList;
import java.util.List;

public class SearchRepositoryAdapter extends RecyclerView.Adapter<SearchRepositoryAdapter.ViewHolder> implements FavoriteContract.view{
    private FavoriteDatabase favoriteDatabase;
    SearchRepositoryAdapter myRepositoryAdapter;
    Context context;
    private ArrayList<SearchRepositoryModel> repositoryResponses = new ArrayList<SearchRepositoryModel>();
    private List<FavoriteData> favoriteData;
    FavoriteContract.view repositoryView;
    RecyclerView recyclerView;
    FavoriteOperation favoriteOperation;

    public SearchRepositoryAdapter(Context context, List<FavoriteData> favoriteData, FavoriteContract.view repositoryView) {
        this.context = context;
        this.favoriteData = favoriteData;
        this.repositoryView = repositoryView;
    }

    public SearchRepositoryAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<SearchRepositoryModel> repositoryResponses){
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
        myRepositoryAdapter = new SearchRepositoryAdapter(context, listFavoriteDatabase, this);
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

        public ViewHolder(@NonNull View view) {
            super(view);
            titleRepository = view.findViewById(R.id.repository_name);
            startRepository = view.findViewById(R.id.repository_stars);
            forkRepository = view.findViewById(R.id.repository_forks);
            languageRepository = view.findViewById(R.id.repository_language);
            descRepository = view.findViewById(R.id.repository_desc);
            favorite = view.findViewById(R.id.btn_add_favorite);
            viewSourceCode = view.findViewById(R.id.btn_myrepository_view_source_code);
        }
        }
    @NonNull
    @Override
    public SearchRepositoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repository_design, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final SearchRepositoryAdapter.ViewHolder holder, final int position) {
        final FavoriteOperation favoriteOperation = new FavoriteOperation(this);
        holder.titleRepository.setText(repositoryResponses.get(position).getName());
        holder.startRepository.setText(repositoryResponses.get(position).getStarGazersCount());
        holder.forkRepository.setText(repositoryResponses.get(position).getForksCount());
        holder.languageRepository.setText(repositoryResponses.get(position).getLanguage());
        holder.descRepository.setText(repositoryResponses.get(position).getDescription());
        holder.load_url = repositoryResponses.get(position).getLink();
        holder.favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteOperation.insertRepositoryData(
                        holder.titleRepository.getText().toString(),
                        holder.descRepository.getText().toString(),
                        holder.forkRepository.getText().toString(),
                        holder.startRepository.getText().toString(),
                        holder.languageRepository.getText().toString(),
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
    }

    @Override
    public int getItemCount() {

        return repositoryResponses.size();
    }

}

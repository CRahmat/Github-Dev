package com.github.githubdev.favorite;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.github.githubdev.R;
import com.github.githubdev.favorite.save.FavoriteContract;
import com.github.githubdev.favorite.save.FavoriteDAO;
import com.github.githubdev.favorite.save.FavoriteData;
import com.github.githubdev.favorite.save.FavoriteDatabase;
import com.github.githubdev.favorite.save.FavoriteOperation;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Favorite extends Fragment implements FavoriteContract.view {
    private FavoriteAdapter favoriteAdapter;
    FavoriteViewModel favoriteViewModel;
    private FavoriteDatabase favoriteDatabase;
    private FavoriteAdapter repositoryAdapter;
    RecyclerView recyclerView;
    private List<FavoriteData> favoriteData = new ArrayList<>();
    private FavoriteContract.view repositoryView;
    private FavoriteOperation favoriteOperation;
    private FavoriteDAO favoriteDAO;
    Button bttn_delete;
    public Favorite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favoriteAdapter = new FavoriteAdapter(getContext());
        favoriteAdapter.notifyDataSetChanged();
        recyclerView = view.findViewById(R.id.rv_repository_fav);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        favoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        favoriteViewModel.setRepository(getContext());
        favoriteViewModel.getRepository().observe(this, getFavorite);
        recyclerView.setAdapter(favoriteAdapter);
        favoriteOperation = new FavoriteOperation(this);
        favoriteOperation.readRepositoryData(favoriteDatabase, getContext());
    }
    private Observer<List<FavoriteData>> getFavorite = new Observer<List<FavoriteData>>() {
        @Override
        public void onChanged(List<FavoriteData> favoriteData) {
            if(favoriteData != null){
                setData(favoriteData);
            }
            else {
                Toast.makeText(getContext(),"Failed to get Data from Server", Toast.LENGTH_SHORT).show();
            }
        }
    };
    public void setData(List<FavoriteData> repositoryResponses){
        this.favoriteData.clear();
        this.favoriteData.addAll(repositoryResponses);
        if(favoriteDatabase == null){
            favoriteDatabase = FavoriteDatabase.database(getContext());
            favoriteDAO = favoriteDatabase.repositoryDAO();
            favoriteData = favoriteDatabase.repositoryDAO().getRepositoryData();
        }
    }

    @Override
    public void successAdd() {
        Toast.makeText(getContext(), "Berhasil Menambahkan di Favorite", Toast.LENGTH_SHORT).show();
        favoriteOperation.readRepositoryData(favoriteDatabase, getContext());
    }

    @Override
    public void successDelete() {
        if(favoriteOperation == null){
            new FavoriteOperation(this);
        }
        Toast.makeText(getContext(), "Berhasil Menghapus Data dari Favorite", Toast.LENGTH_SHORT).show();
        favoriteOperation.readRepositoryData(favoriteDatabase, getContext());
    }

    @Override
    public void getData(List<FavoriteData> listFavoriteDatabase) {
        repositoryAdapter = new FavoriteAdapter(getContext(), listFavoriteDatabase, this);
        recyclerView.setAdapter(repositoryAdapter);
    }

    @Override
    public void deleteRepositoryData(final FavoriteData favoriteData) {
        if(favoriteOperation == null){
            favoriteOperation = new FavoriteOperation(this);
        }
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
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
        Toast.makeText(getContext(), "Repository Already Exist On Favorite", Toast.LENGTH_SHORT).show();
    }

}

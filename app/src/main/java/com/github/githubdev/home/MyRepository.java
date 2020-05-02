package com.github.githubdev.home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.githubdev.R;
import com.github.githubdev.favorite.save.FavoriteContract;
import com.github.githubdev.favorite.save.FavoriteData;
import com.github.githubdev.favorite.save.FavoriteDatabase;
import com.github.githubdev.favorite.save.FavoriteOperation;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyRepository extends Fragment{
    private MyRepositoryAdapter myRepositoryAdapter;
    private MyRepositoryViewModel myRepositoryViewModel;
    private RecyclerView recyclerView;
    private FavoriteOperation favoriteOperation;
    private FavoriteDatabase favoriteDatabase;
    FavoriteContract.view repositoryContract;
    private List<FavoriteData> favoriteData;

    public MyRepository() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @SuppressLint("ResourceAsColor")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        myRepositoryAdapter = new MyRepositoryAdapter(getContext());
        myRepositoryAdapter.notifyDataSetChanged();
        recyclerView = view.findViewById(R.id.rv_repository);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        myRepositoryViewModel = new ViewModelProvider(this).get(MyRepositoryViewModel.class);
        myRepositoryViewModel.setRepository(getContext());
        TextView next = view.findViewById(R.id.home_next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 1;
                myRepositoryViewModel.setRepository(page++);
            }
        });
        myRepositoryViewModel.getRepository().observe(this, getRepository);
        recyclerView.setAdapter(myRepositoryAdapter);
    }

    private Observer<ArrayList<MyRepositoryResponse>> getRepository = new Observer<ArrayList<MyRepositoryResponse>>() {
        @Override
        public void onChanged(ArrayList<MyRepositoryResponse> myRepositoryRespons) {
            if(myRepositoryRespons != null){
                myRepositoryAdapter.setData(myRepositoryRespons);
            }
            else {
                Toast.makeText(getContext(),"Failed to get Data from Server", Toast.LENGTH_SHORT).show();
            }
        }
    };

}

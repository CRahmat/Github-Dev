package com.github.githubdev.search;

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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.githubdev.R;
import com.github.githubdev.favorite.save.FavoriteContract;
import com.github.githubdev.favorite.save.FavoriteData;
import com.github.githubdev.favorite.save.FavoriteDatabase;
import com.github.githubdev.favorite.save.FavoriteOperation;
import com.github.githubdev.home.MyRepositoryAdapter;
import com.github.githubdev.home.MyRepositoryResponse;
import com.github.githubdev.home.MyRepositoryViewModel;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends Fragment implements View.OnClickListener {
    private SearchRepositoryAdapter myRepositoryAdapter;
    private SearchRepositoryViewModel myRepositoryViewModel;
    private RecyclerView recyclerView;
    private EditText search;
    private TextView next;
    private ImageView btnSearch;
    private FavoriteOperation favoriteOperation;
    private FavoriteDatabase favoriteDatabase;
    FavoriteContract.view repositoryContract;
    private List<FavoriteData> favoriteData;
    ProgressBar progressBar;
    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnSearch = view.findViewById(R.id.ic_search);
        recyclerView = view.findViewById(R.id.rv_search_result);
        search = view.findViewById(R.id.et_search);
        next = view.findViewById(R.id.home_next);
        progressBar = view.findViewById(R.id.progres_bar_search);
        btnSearch.setOnClickListener(this);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int page = 1;
                myRepositoryViewModel.setRepository(page++);
            }
        });

    }

    private Observer<SearchRepositoryResponse> getRepository = new Observer<SearchRepositoryResponse>() {
        @Override
        public void onChanged(SearchRepositoryResponse searchRepositoryResponses) {
            if(searchRepositoryResponses != null){
                myRepositoryAdapter.setData(searchRepositoryResponses.result);
                recyclerView.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(getContext(),"Failed to get Data from Server", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    public void onClick(View v) {
        if(!search.getText().toString().equals("")) {
            progressBar.setVisibility(v.VISIBLE);
            myRepositoryAdapter = new SearchRepositoryAdapter(getContext());
            myRepositoryAdapter.notifyDataSetChanged();
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            myRepositoryViewModel = new ViewModelProvider(this).get(SearchRepositoryViewModel.class);
            myRepositoryViewModel.setRepository(getContext(), search.getText().toString());
            myRepositoryViewModel.getRepository().observe(this, getRepository);
            recyclerView.setAdapter(myRepositoryAdapter);
        }else {
            search.setHint("Please, Input repository name");
        }
    }
}

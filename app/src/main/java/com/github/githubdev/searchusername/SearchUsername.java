package com.github.githubdev.searchusername;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.githubdev.R;
import com.github.githubdev.favorite.save.FavoriteData;
import com.github.githubdev.favorite.save.FavoriteDatabase;
import com.github.githubdev.favorite.save.FavoriteOperation;
import com.github.githubdev.intro.IntroActivity;
import com.github.githubdev.main.MainActivity;
import com.github.githubdev.searchusername.saveusername.UsernameDAO;
import com.github.githubdev.searchusername.saveusername.UsernameOperation;
import com.github.githubdev.services.base.APIClient;
import com.github.githubdev.searchusername.saveusername.UsernameData;
import com.github.githubdev.searchusername.saveusername.UsernameDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchUsername extends AppCompatActivity{
    Button searchName;
    Animation btnSearch;
    EditText username;
    APIClient apiClient;
    private String reciveUsername;
    UsernameData usernameData;
    UsernameDatabase usernameDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_username);
        btnSearch = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.title_intro);
        searchName = findViewById(R.id.bttn_search);
        username = findViewById(R.id.search_username);
        searchName.setAnimation(btnSearch);
        //Search username event
            searchName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    reciveUsername = username.getText().toString();
                    //Check username
                    if(username.getText().equals(" ") || reciveUsername.length() == 0){
                        username.setHint("Please, Input Username");
                        Toast.makeText(getApplicationContext(),"Please Input Username!!!", Toast.LENGTH_SHORT).show();
                    }else {
                        searchStatus();
                    }
                }
            });
        }

    private void searchStatus() {
        if (apiClient == null) {
            apiClient = new APIClient();
        }
            apiClient.getAPIClient().getSearchResponse(reciveUsername).enqueue(new Callback<SearchResponse>() {
                IntroActivity introActivity = new IntroActivity();
                //Success Request data from web service
                @Override
                public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                    SearchResponse searchResponse = response.body();
                    if (searchResponse.result.size() != 0) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        UsernameOperation usernameOperation = new UsernameOperation(getApplicationContext());
                        usernameDatabase = UsernameDatabase.usernameDatabase(getApplicationContext());
                        usernameData = usernameDatabase.usernameDAO().getUsername();
                        FavoriteOperation favoriteOperation = new FavoriteOperation(getApplicationContext());
                        FavoriteDatabase favoriteDatabase = FavoriteDatabase.database(getApplicationContext());
                        List<FavoriteData> favoriteData = favoriteDatabase.repositoryDAO().getRepositoryData();
                        for (int i = 0 ; i < favoriteData.size() ; i++) {
                            favoriteOperation.deleteRepositoryData(favoriteData.get(i), favoriteDatabase);
                        }
                        usernameOperation.deleteUsername(usernameData, usernameDatabase);
                        usernameOperation.insertUsername(searchResponse.result.get(0).getLogin(), UsernameDatabase.usernameDatabase(getBaseContext()));
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        introActivity.savePrefsData(true, getApplicationContext());
                        startActivity(intent);
                    }else {
                        introActivity.savePrefsData(false, getApplicationContext());
                        username.setText("");
                        username.setHint("Please, Enter a Verified Username");
                        Toast.makeText(getApplicationContext(), "Username Not Found!!!", Toast.LENGTH_SHORT).show();
                    }
                }
                //Failed Request data from web service
                @Override
                public void onFailure(Call<SearchResponse> call, Throwable t) {
                    introActivity.savePrefsData(false, getApplicationContext());
                    username.setText(" ");
                    Toast.makeText(getApplicationContext(), "Username Not Found!!!", Toast.LENGTH_SHORT).show();
                }
            });
        }
}

package com.github.githubdev.profile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.github.githubdev.R;
import com.github.githubdev.intro.IntroActivity;
import com.github.githubdev.searchusername.saveusername.UsernameData;
import com.github.githubdev.searchusername.saveusername.UsernameDatabase;
import com.github.githubdev.searchusername.saveusername.UsernameOperation;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends Fragment {
    private UserViewModel userViewModel;
    UsernameData usernameData;
    UsernameDatabase usernameDatabase;
    private UserProfileResponse userProfileResponse;
    TextView name;
    TextView userName;
    TextView repository;
    TextView followers;
    TextView following;
    TextView bio;
    TextView location;
    TextView website;
    TextView organization;
    CircleImageView photoProfile;
    Button btn_sign_out;
    IntroActivity introActivity;

    public Profile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.setUser(getContext());
        userViewModel.getUser().observe(this, getUser);
        loadUserProfile();
    }
    private androidx.lifecycle.Observer<UserProfileResponse> getUser = new Observer<UserProfileResponse>() {
        @Override
        public void onChanged(UserProfileResponse userProfileResponse) {
            if(userProfileResponse != null){
                Picasso.get().load(userProfileResponse.getPhotoProfile()).into(photoProfile);
                name.setText(userProfileResponse.getName());
                userName.setText(userProfileResponse.getUsername());
                repository.setText(userProfileResponse.getRepository());
                followers.setText(userProfileResponse.getFollowers());
                following.setText(userProfileResponse.getFollowing());
                bio.setText(userProfileResponse.getBio());
                location.setText(userProfileResponse.getLocation());
                website.setText(userProfileResponse.getBlog());
                btn_sign_out.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setCancelable(false);
                        builder.setMessage("Do You Want to Sign Out?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent mainActivity = new Intent(getContext(), IntroActivity.class);
                                introActivity = new IntroActivity();
                                introActivity.savePrefsData(false, getContext());
                                startActivity(mainActivity);
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //if user select "No", just cancel this dialog and continue with app
                                dialog.cancel();
                            }
                        });
                        AlertDialog alert = builder.create();
                        alert.show();

                    }
                });
            }
        }
    };

    private void loadUserProfile() {
        name = getActivity().findViewById(R.id.profile_name);
        userName = getActivity().findViewById(R.id.profile_username);
        repository = getActivity().findViewById(R.id.profile_repository);
        followers = getActivity().findViewById(R.id.profile_followers);
        following = getActivity().findViewById(R.id.profile_following);
        bio = getActivity().findViewById(R.id.profiles_bio);
        location = getActivity().findViewById(R.id.profile_location);
        website = getActivity().findViewById(R.id.profile_website);
        organization = getActivity().findViewById(R.id.profile_organization);
        photoProfile = getActivity().findViewById(R.id.profile_image);
        btn_sign_out = getActivity().findViewById(R.id.profile_logout_bottom);
    }

}

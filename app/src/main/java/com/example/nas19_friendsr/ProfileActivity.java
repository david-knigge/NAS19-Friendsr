package com.example.nas19_friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    private Friend retrievedFriend;
    private SharedPreferences preferences;
    private boolean editMode;

    ImageView image;
    TextView name, bio;
    EditText nameEdit, bioEdit;
    RatingBar ratingBar;
    Button likeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences = getSharedPreferences("settings", MODE_PRIVATE);

        image = findViewById(R.id.profileImage);
        name = findViewById(R.id.profileName);
        bio = findViewById(R.id.profileBio);
        nameEdit = findViewById(R.id.profileNameEdit);
        bioEdit = findViewById(R.id.profileBioEdit);
        ratingBar = findViewById(R.id.profileRating);
        likeButton = findViewById(R.id.likeButton);

        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");
        ratingBar.setOnRatingBarChangeListener(new RatingBarClickListener());
        renderInformation();
    }

    private class RatingBarClickListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            retrievedFriend.setRating(rating);
            preferences.edit().putFloat(retrievedFriend.getName()+"Rating", rating).apply();
        }
    }

    public void likeClicked(View v) {
        Button b = (Button)v;
        retrievedFriend.addLike();
        preferences.edit().putInt(retrievedFriend.getName()+"Likes", retrievedFriend.getLikes()).apply();
        b.setText("like (" + retrievedFriend.getLikes() + ")");
    }

    public void editClicked(View v) {
        Button b = (Button)v;

        if (editMode) {
            retrievedFriend.setBio(bioEdit.getText().toString());
            retrievedFriend.setName(nameEdit.getText().toString());

            renderInformation();
            b.setText("edit");
            name.setVisibility(View.VISIBLE);
            nameEdit.setVisibility(View.GONE);
            bio.setVisibility(View.VISIBLE);
            bioEdit.setVisibility(View.GONE);
        } else {
            nameEdit.setVisibility(View.VISIBLE);
            name.setVisibility(View.INVISIBLE);
            bioEdit.setVisibility(View.VISIBLE);
            bio.setVisibility(View.INVISIBLE);
            b.setText("save");
        }
        editMode = !editMode;
    }

    public void renderInformation() {
        image.setImageResource(retrievedFriend.getDrawableId());
        name.setText(retrievedFriend.getName());
        bio.setText(retrievedFriend.getBio());
        nameEdit.setText(retrievedFriend.getName());
        bioEdit.setText(retrievedFriend.getBio());
        float rating = preferences.getFloat(retrievedFriend.getName()+"Rating", 0);
        int likes = preferences.getInt(retrievedFriend.getName()+"Likes", 0);
        if (rating != 0) {
            retrievedFriend.setRating(rating);
            ratingBar.setRating(rating);
        }
        if (likes != 0) {
            retrievedFriend.setLikes(likes);
            likeButton.setText("like (" + likes + ")");
        }
    }
}

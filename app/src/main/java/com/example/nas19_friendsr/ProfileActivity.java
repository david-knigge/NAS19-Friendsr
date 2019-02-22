package com.example.nas19_friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private Friend retrievedFriend;
    private SharedPreferences preferences;
    private boolean editMode;

    ImageView image;
    TextView name, bio;
    EditText bioEdit;
    RatingBar ratingBar;
    Button likeButton;

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences = getSharedPreferences("settings", MODE_PRIVATE);

        image = findViewById(R.id.profileImage);
        name = findViewById(R.id.profileName);
        bio = findViewById(R.id.profileBio);
        bioEdit = findViewById(R.id.profileBioEdit);
        ratingBar = findViewById(R.id.profileRating);
        likeButton = findViewById(R.id.likeButton);

        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");
        ratingBar.setOnRatingBarChangeListener(new RatingBarClickListener());
        renderInformation();
    }

    /*
     * Implements onclick listener for rating bar change.
     */
    private class RatingBarClickListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            retrievedFriend.setRating(rating);
            preferences.edit().putFloat(retrievedFriend.getName()+"Rating", rating).apply();
        }
    }

    /*
     * Increment number of likes when the like button is clicked, store number of likes in preferences.
     * Update button text.
     */
    public void likeClicked(View v) {
        Button b = (Button)v;
        retrievedFriend.addLike();
        preferences.edit().putInt(retrievedFriend.getName()+"Likes", retrievedFriend.getLikes()).apply();
        b.setText("like (" + retrievedFriend.getLikes() + ")");
    }

    /*
     * When like button is clicked, if editmode was enabled retrieve updated text and store it. If
     * editmode was not enabled, show the edittext to allow for edits. Toggle editmode.
     */
    public void editClicked(View v) {
        Button b = (Button)v;

        if (editMode) {
            preferences.edit().putString(retrievedFriend.getName() + "Bio", bioEdit.getText().toString()).apply();
            renderInformation();

            b.setText("edit");
            bio.setVisibility(View.VISIBLE);
            bioEdit.setVisibility(View.GONE);
        } else {
            bioEdit.setVisibility(View.VISIBLE);
            bio.setVisibility(View.INVISIBLE);
            b.setText("save");
        }
        editMode = !editMode;
    }

    /*
     * Retrieve and show stored information from this friend in corresponding views.
     */
    public void renderInformation() {
        image.setImageResource(retrievedFriend.getDrawableId());
        name.setText(retrievedFriend.getName());
        bio.setText(retrievedFriend.getBio());
        bioEdit.setText(retrievedFriend.getBio());
        setTitle(retrievedFriend.getName());

        String bioText = preferences.getString(retrievedFriend.getName() + "Bio", null);
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
        if (bioText != null) {
            retrievedFriend.setBio(bioText);
            bio.setText(bioText);
            bioEdit.setText(bioText);
        }
    }
}

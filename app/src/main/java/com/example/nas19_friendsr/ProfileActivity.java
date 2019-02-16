package com.example.nas19_friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProfileActivity extends AppCompatActivity {

    private class RatingBarClickListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            retrievedFriend.setRating(rating);
            Log.d("rating", ""+rating);
            preferences.edit().putFloat(retrievedFriend.getName(), rating).apply();
        }
    }

    private Friend retrievedFriend;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        preferences = getSharedPreferences("settings", MODE_PRIVATE);

        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        ImageView image = findViewById(R.id.profileImage);
        TextView name = findViewById(R.id.profileName);
        TextView bio = findViewById(R.id.profileBio);
        RatingBar ratingBar = findViewById(R.id.profileRating);
        float rating = preferences.getFloat(retrievedFriend.getName(), 0);

        image.setImageResource(retrievedFriend.getDrawableId());
        name.setText(retrievedFriend.getName());
        bio.setText(retrievedFriend.getBio());
        if (rating != 0) {
            ratingBar.setRating(rating);
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBarClickListener());
    }


}

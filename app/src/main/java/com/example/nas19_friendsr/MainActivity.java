package com.example.nas19_friendsr;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", (Serializable) clickedFriend);
            startActivity(intent);
        }
    }

    ArrayList<Friend> friends;
    static int newFriends = 0;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addItem:
                newFriends += 1;
                friends.add(new Friend("New Friend " + newFriends, "Edit this bio...", getResources().getIdentifier("tyrion" , "drawable", getPackageName())));
                setAdapter();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            friends = savedInstanceState.getParcelableArrayList("friends");
        } else {
            friends = new ArrayList<>();
            setFriends();
        }
        setAdapter();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("friends", friends);
    }

    public void setFriends() {
        friends.add(new Friend("Arya", "standard", getResources().getIdentifier("arya" , "drawable", getPackageName())));
        friends.add(new Friend("Cersei", "standard", getResources().getIdentifier("cersei" , "drawable", getPackageName())));
        friends.add(new Friend("Daenerys", "standard", getResources().getIdentifier("daenerys" , "drawable", getPackageName())));
        friends.add(new Friend("Jaime", "standard", getResources().getIdentifier("jaime" , "drawable", getPackageName())));
        friends.add(new Friend("Jon", "standard", getResources().getIdentifier("jon" , "drawable", getPackageName())));
        friends.add(new Friend("Jorah", "standard", getResources().getIdentifier("jorah" , "drawable", getPackageName())));
        friends.add(new Friend("Margaery", "standard", getResources().getIdentifier("margaery" , "drawable", getPackageName())));
        friends.add(new Friend("Melisandre", "standard", getResources().getIdentifier("melisandre" , "drawable", getPackageName())));
        friends.add(new Friend("Sannsa", "standard", getResources().getIdentifier("sansa" , "drawable", getPackageName())));
        friends.add(new Friend("Tyrion", "standard", getResources().getIdentifier("tyrion" , "drawable", getPackageName())));
    }

    public void setAdapter() {
        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        GridView view = findViewById(R.id.mainContainer);
        view.setAdapter(adapter);
        view.setOnItemClickListener(new GridItemClickListener());
    }
}

package com.example.nas19_friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Friend> friends;
    static int newFriends = 0;

    /*
     * Attempt to restore friends list from Bundle, otherwise create a new friends list,
     * fill it with friends.
     */
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
        setTitle("Friendsr");
        setAdapter();
    }

    /*
     * Store friends list on destroy.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("friends", friends);
    }

    @Override
    public void setTitle(CharSequence title) {
        super.setTitle(title);
    }

    /*
     * Implements click listener on friends grid. Start intent and pass clicked friend to it.
     */
    private class GridItemClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", (Serializable) clickedFriend);
            startActivity(intent);
        }
    }

    /*
     * Set options menu.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /*
     * When add friend button is clicked, add new friend to friends array and reinitialize adapter.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addItem:
                newFriends += 1;
                friends.add(new Friend("Friend " + newFriends, "Edit this bio...", getResources().getIdentifier("default_picture" , "drawable", getPackageName())));
                setAdapter();
                break;
        }
        return true;
    }

    /*
     * Set up all friends.
     */
    public void setFriends() {
        friends.add(new Friend("Arya", "Edit this bio...", getResources().getIdentifier("arya" , "drawable", getPackageName())));
        friends.add(new Friend("Cersei", "Edit this bio...", getResources().getIdentifier("cersei" , "drawable", getPackageName())));
        friends.add(new Friend("Daenerys", "Edit this bio...", getResources().getIdentifier("daenerys" , "drawable", getPackageName())));
        friends.add(new Friend("Jaime", "Edit this bio...", getResources().getIdentifier("jaime" , "drawable", getPackageName())));
        friends.add(new Friend("Jon", "Edit this bio...", getResources().getIdentifier("jon" , "drawable", getPackageName())));
        friends.add(new Friend("Jorah", "Edit this bio...", getResources().getIdentifier("jorah" , "drawable", getPackageName())));
        friends.add(new Friend("Margaery", "Edit this bio...", getResources().getIdentifier("margaery" , "drawable", getPackageName())));
        friends.add(new Friend("Melisandre", "Edit this bio...", getResources().getIdentifier("melisandre" , "drawable", getPackageName())));
        friends.add(new Friend("Sannsa", "Edit this bio...", getResources().getIdentifier("sansa" , "drawable", getPackageName())));
        friends.add(new Friend("Tyrion", "Edit this bio...", getResources().getIdentifier("tyrion" , "drawable", getPackageName())));
    }

    /*
     * Initialize adapter for filling friends grid, set onclicklistener.
     */
    public void setAdapter() {
        FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        GridView view = findViewById(R.id.mainContainer);
        view.setAdapter(adapter);
        view.setOnItemClickListener(new GridItemClickListener());
    }
}

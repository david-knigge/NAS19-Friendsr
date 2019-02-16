package com.example.nas19_friendsr;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendsAdapter extends ArrayAdapter<Friend> {

    private ArrayList<Friend> friends;

    public FriendsAdapter(Context context, int resource, ArrayList<Friend> objects) {
        super(context, resource, objects);
        friends = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        Friend person = friends.get(position);
        ImageView image = convertView.findViewById(R.id.image);
        TextView name = convertView.findViewById(R.id.name);

        image.setImageResource(person.getDrawableId());

        name.setText(person.getName());

        Log.d("text", "" + person.getDrawableId());

        return convertView;
    }

}

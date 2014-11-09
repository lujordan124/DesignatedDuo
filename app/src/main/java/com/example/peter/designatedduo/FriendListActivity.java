package com.example.peter.designatedduo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class FriendListActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        final ListView listview = (ListView) findViewById(R.id.friendlist);
        String[] values = new String[]{"Eric Shiao", "Jordan Lu", "Karen Qian", "Peter Shao"};

        /*final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < values.length; ++i) {
            list.add(values[i]);
        }*/
        final FriendsArrayAdapter adapter = new FriendsArrayAdapter(this, values);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                            }
                        });
            }

        });

    }
    public class FriendsArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public FriendsArrayAdapter(Context context, String [] values) {
            super(context, R.layout.friend, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = inflater.inflate(R.layout.friend, parent, false);
            TextView textView = (TextView) rowView.findViewById(R.id.name);
            ImageView imageView = (ImageView) rowView.findViewById(R.id.pic);
            textView.setText(values[position]);
            // change the icon for Windows and iPhone
            return rowView;
        }
    }
}

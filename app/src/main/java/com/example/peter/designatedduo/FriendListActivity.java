package com.example.peter.designatedduo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.net.wifi.p2p.*;


public class FriendListActivity extends Activity {
    WifiP2pManager mManager;
    Channel mChannel;
    BroadcastReceiver mReceiver;
    IntentFilter mIntentFilter;
    PeerListListener myPeerListListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        final ListView listview = (ListView) findViewById(R.id.friendlist);
        String[] values = new String[]{"Eric Shiao", "Jordan Lu", "Karen Qian", "Peter Shao"};

        final FriendsArrayAdapter adapter = new FriendsArrayAdapter(this, values);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(FriendListActivity.this, DuoMenuActivity.class);
                intent.putExtra("Name", item);
                FriendListActivity.this.startActivity(intent);
            }

        });
    }
//        Toast.makeText(getApplicationContext(), "HELLOOOOO",
//                Toast.LENGTH_LONG).show();
    class FriendsArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;

        public FriendsArrayAdapter(Context context, String[] values) {
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

    //ASYNC TASK THAT DOESN'T WORK
    final class GetFriends extends AsyncTask<String, String, Void> {
        String url = "192.168.56.1:5000/getFriends/Karen/";
        InputStream inputStream = null;
        String result = "";

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            try {
                HttpPost httpPost = new HttpPost(url);
                response = httpclient.execute(httpPost);
                HttpEntity httpentity = response.getEntity();
                inputStream = httpentity.getContent();
            } catch (Exception e) {
                System.out.println(e);
            }
            try {
                BufferedReader bReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
                StringBuilder sBuilder = new StringBuilder();

                String line = null;
                while ((line = bReader.readLine()) != null) {
                    sBuilder.append(line + "\n");
                }

                inputStream.close();
                result = sBuilder.toString();
            } catch (Exception e) {
                System.out.println(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(final Void param) {
            try {
                JSONArray jArray = new JSONArray(result);
                String[] values = new String[jArray.length()];
                for (int i = 0; i < jArray.length(); i++) {
                    values[i] = jArray.getString(i);
                }
            } catch (JSONException e) {
            } // catch (JSONException e)
        }
    }

    /*Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(getApplicationContext(), "HELLOOOOO",
                Toast.LENGTH_LONG).show();
        Intent intent = new Intent(FriendListActivity.this, AddFriendActivity.class);
        FriendListActivity.this.startActivity(intent);
        return super.onContextItemSelected(item);
    }*/


    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(getApplicationContext(), "HELLOOOOO",
        //Toast.LENGTH_LONG).show();
        Intent intent = new Intent(FriendListActivity.this, AddFriendActivity.class);
        FriendListActivity.this.startActivity(intent);
        //System.out.print("HI");
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
//        menu.add(1, Menu.FIRST, Menu.FIRST, "");
        inflater.inflate(R.menu.addfriend, menu);

        return super.onCreateOptionsMenu(menu);
    }
}

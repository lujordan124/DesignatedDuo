package com.example.peter.designatedduo;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
/**
 * Created by JordanLu on 11/9/14.
 */
public class AddFriendActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        final ArrayList <String> c = getContacts();
        String [] contacts = new String [c.size()];
        for (int i = 0; i < c.size(); i++){
            contacts[i] = c.get(i);
        }
        final ListView listview = (ListView) findViewById(R.id.contactlist);
        final FriendsArrayAdapter adapter = new FriendsArrayAdapter(this, contacts);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                Intent intent = new Intent(AddFriendActivity.this, DuoMenuActivity.class);
                c.remove(item);
                intent.putExtra("Name", item);
                AddFriendActivity.this.startActivity(intent);
            }

        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public class FriendsArrayAdapter extends ArrayAdapter<String> {
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
    public ArrayList<String> getContacts() {
        ArrayList<String> myContacts = new ArrayList<String>();
        Cursor cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        /*ListView contacts = (ListView)(findViewById(R.id.friendlist));
        ArrayList<String> alreadyFriend = new ArrayList<String>();
        for (int i = 0; i < contacts.getChildCount();i++){
            Toast.makeText(getApplicationContext(), i,
                    Toast.LENGTH_LONG).show();
            TextView text = (TextView) contacts.findViewById(R.id.friendlist_item).findViewById(R.id.name);
            alreadyFriend.add(text.getText().toString());
        }*/
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                if (Integer.parseInt(cursor.getString(
                        cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        String phoneNo = pCur.getString(pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        if(phoneNo.length() == 10)
                            myContacts.add(name);
                    }
                    pCur.close();
                }
            }
        }
        return myContacts;
    }
}

package com.example.peter.designatedduo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

/**
 * Created by Eric on 11/8/2014.
 */
public class DuoMenuActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duomenu);
        Button drinkCount = (Button) findViewById(R.id.drinkCount);
        Button drinkLimit = (Button) findViewById(R.id.drinkLimit);
        Button myGPS = (Button) findViewById(R.id.myGPS);
        Button duoGPS = (Button) findViewById(R.id.duoGPS);
        Button chat = (Button) findViewById(R.id.chat);

        drinkCount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DuoMenuActivity.this, DrinkCountActivity.class);
                DuoMenuActivity.this.startActivity(intent);
            }
        });

        drinkLimit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DuoMenuActivity.this, DrinkLimitActivity.class);
                DuoMenuActivity.this.startActivity(intent);
            }
        });

        myGPS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DuoMenuActivity.this, MyGPSActivity.class);
                DuoMenuActivity.this.startActivity(intent);
            }
        });
        duoGPS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DuoMenuActivity.this, DuoGPSActivity.class);
                DuoMenuActivity.this.startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(DuoMenuActivity.this, ChatActivity.class);
                DuoMenuActivity.this.startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_duo, menu);
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
}

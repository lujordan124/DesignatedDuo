package com.example.peter.designatedduo;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Eric on 11/8/2014.
 */
public class DrinkLimitActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_limit);
        Button subtract = (Button) findViewById(R.id.subtract);
        Button add = (Button) findViewById(R.id.add);
        subtract.setBackgroundColor(Color.parseColor("#ccc"));
        add.setBackgroundColor(Color.parseColor("#ccc"));
        final TextView text = (TextView)findViewById(R.id.drinkLimit);
        subtract.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Integer.parseInt(text.getText().toString()) == 0)
                {
                    Toast.makeText(getApplicationContext(), (String) "INVALID",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    int num = Integer.parseInt(text.getText().toString());
                    text.setText(num-1);
                }

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Integer.parseInt(text.getText().toString()) > 100) {
                    Toast.makeText(getApplicationContext(), (String) "INVALID",
                            Toast.LENGTH_LONG).show();
                } else {
                    int num = Integer.parseInt(text.getText().toString());
                    text.setText(num - 1);
                }
            }
        });
    }
}

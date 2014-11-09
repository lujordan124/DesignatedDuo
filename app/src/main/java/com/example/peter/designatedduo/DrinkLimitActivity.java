package com.example.peter.designatedduo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.Channel;

/**
 * Created by Eric on 11/8/2014.
 */
public class DrinkLimitActivity extends Activity {

    WifiP2pManager mManager;
    Channel mChannel;
    BroadcastReceiver mReceiver;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drink_limit);
        Button subtract = (Button) findViewById(R.id.subtract);
        Button add = (Button) findViewById(R.id.add);
        Button check = (Button) findViewById(R.id.check);
        final TextView text = (TextView)findViewById(R.id.count);

        subtract.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Integer.parseInt(text.getText().toString()) <= 0)
                {
                    Toast.makeText(getApplicationContext(), (String) "INVALID",
                            Toast.LENGTH_LONG).show();
                }
                else
                {
                    int num = Integer.parseInt(text.getText().toString());
                    text.setText(""+(num-1));
                }

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (Integer.parseInt(text.getText().toString()) > 10) {
                    Toast.makeText(getApplicationContext(), (String) "INVALID",
                            Toast.LENGTH_LONG).show();
                } else {
                    int num = Integer.parseInt(text.getText().toString());
                    text.setText(""+(num +1));
                }
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String str = text.getText().toString();
                Toast.makeText(getApplicationContext(), "String sent",
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}

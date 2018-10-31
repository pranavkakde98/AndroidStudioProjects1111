package com.example.hp.afinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GoToMap extends AppCompatActivity implements View.OnClickListener{

    private Button button_to_map;
    private Button button_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_to_map);

        button_to_map = (Button)findViewById(R.id.button_to_map);
        button_skip = (Button)findViewById(R.id.button_skip);

        button_to_map.setOnClickListener(this);
        button_skip.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v == button_to_map)
        {
            Intent intent = new Intent(this,CurrentLocation.class);
            startActivity(intent);
        }
        if(v == button_skip)
        {
            Intent intent = new Intent(this,NavigationDrawerActivity.class);
            startActivity(intent);
        }
    }
}

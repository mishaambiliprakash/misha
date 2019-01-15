package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {
    ImageView iv1,iv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        iv1=(ImageView)findViewById(R.id.add);
        iv2=(ImageView)findViewById(R.id.search);
        iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"add",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),Addactivity.class);
                startActivity(i);
            }
        });
        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"search",Toast.LENGTH_LONG).show();
                Intent i=new Intent(getApplicationContext(),Searchactivity.class);
                startActivity(i);
            }
        });
    }
}

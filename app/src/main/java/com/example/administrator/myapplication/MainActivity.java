package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button b;
    EditText ed1,ed2;
    String e,p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button)findViewById(R.id.login);
        ed1=(EditText)findViewById(R.id.email);
        ed2=(EditText)findViewById(R.id.password);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e=ed1.getText().toString();
                p=ed2.getText().toString();
                if (e.equals("admin")&&(p.equals("1234")))
                {
                    Intent i=new Intent(getApplicationContext(),Dashboard.class);
                    startActivity(i);

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"invalid password",Toast.LENGTH_LONG).show();

                }
               // Toast.makeText(getApplicationContext(),e,Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),p,Toast.LENGTH_LONG).show();

            }
        });
    }
}

package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Addactivity extends AppCompatActivity {

    EditText e1,e2,e3,e4;
    Button bt1;
    String name,addno,rollno,brach,url="http://logixspace.com/mzc/add.php";
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.addno);
        e3=(EditText)findViewById(R.id.rollno);
        e4=(EditText)findViewById(R.id.branch);
        bt1=(Button)findViewById(R.id.submit);

        progressBar=(ProgressBar) findViewById(R.id.pb);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=e1.getText().toString();
                addno=e2.getText().toString();
                rollno=e3.getText().toString();
                brach=e4.getText().toString();
                //Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),addno,Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),rollno,Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),brach,Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.VISIBLE);
                addToDb();

            }
        });
    }

    private void addToDb() {
        //Toast.makeText(getApplicationContext(),"hai",Toast.LENGTH_LONG).show();
        StringRequest stringrequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonobject =new JSONObject(response);

                    String responseFromServer=jsonobject.getString("status");
                    progressBar.setVisibility(View.GONE);

                    if (responseFromServer.equals("success"))
                    {

                        Toast.makeText(getApplicationContext(),"successfully Registered",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Error in Registration",Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Exception in Registration"+e,Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("name",name);
                params.put("rollno",rollno);
                params.put("admno",addno);
                params.put("branch",brach);
                return params;
            }
        };
        RequestQueue requestqueue= Volley.newRequestQueue(this);
        requestqueue.add(stringrequest);
        }
    }


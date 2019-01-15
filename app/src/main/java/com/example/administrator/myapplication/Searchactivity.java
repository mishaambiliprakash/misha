package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Searchactivity extends AppCompatActivity {
    Button b;
    EditText e1,e2,e3,e4;
    String admission;
    String url="http://logixspace.com/mzc/search.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchactivity);
        b=(Button)findViewById(R.id.search);
        e1=(EditText)findViewById(R.id.admi);
        e2=(EditText)findViewById(R.id.edit1);
        e3=(EditText)findViewById(R.id.edit2);
        e4 = (EditText)findViewById(R.id.edit3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                admission=e1.getText().toString();
                searchFromDB();
            }
        });
    }
    private void searchFromDB()
    {
        StringRequest stringRequest =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    String respFromServer=jsonObject.getString("status");
                    if (respFromServer.equals("success"))
                    {
                        e2.setVisibility(View.VISIBLE);
                        e3.setVisibility(View.VISIBLE);
                        e4.setVisibility(View.VISIBLE);

                        JSONArray jsonArray=jsonObject.getJSONArray("data");
                        for (int i=0;i<jsonArray.length();i++)
                        {
                           JSONObject obj=jsonArray.getJSONObject(i);
                            String name=obj.getString("name");
                            e2.setText(name);
                            String roll=obj.getString("rollno");
                            e3.setText(roll);
                            String branch=obj.getString("branch");
                            e4.setText(branch);
                            Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),roll,Toast.LENGTH_LONG).show();
                            Toast.makeText(getApplicationContext(),branch,Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid admission no",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<String, String>();
                params.put("admno",admission);

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

package com.example.networkapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.networkapp.Adapter.MovieAdapter;
import com.example.networkapp.model.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    public ArrayList<Movie> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    Button buttonRecommendedMovie;
    Button buttonlogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
        buttonRecommendedMovie=findViewById(R.id.btnMovieList);
        buttonlogout=findViewById(R.id.Logout);
        buttonRecommendedMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);

            }
        });





    }

    private void getData() {
        String URL="https://api.rss2json.com/v1/api.json?rss_url=https%3A%2F%2Fwww.filmfestivals.com%2Fchannel%2Ffilm%2Fvideos%2Ffeed";
        RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
        StringRequest stringRequest=new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response);
                    JSONArray jsonArray=jsonObject.getJSONArray("items");
                    if(jsonArray.length()>0){
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject1=jsonArray.getJSONObject(i);
                            String title=jsonObject1.getString("title");
                            String poster=jsonObject1.getString("thumbnail");
                            String[] arr=jsonObject1.getString("categories").split(",");
                            String category1=arr[arr.length-1];
                            String category=category1.substring(1,category1.length()-2);
                            list.add(new Movie(poster, title, category));
                        }
                        movieAdapter=new MovieAdapter( list,MainActivity2.this);
                        recyclerView.setAdapter(movieAdapter);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity2.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
       queue.add(stringRequest);
    }


}
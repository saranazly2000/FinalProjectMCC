package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class newsActivity extends AppCompatActivity {
    RequestQueue queue;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<News> newsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        queue = Volley.newRequestQueue(getApplicationContext());
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        String url="";
        try {
            String query = URLEncoder.encode("القدس", "utf-8");
            url = "https://newsapi.org/v2/everything?q="+query+"&from=2021-06-08&to=2021-06-08&sortBy=popularity&apiKey=e5c620fcd34f4c559ef4a5cb64bfc7c1";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("latest");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject news = jsonArray.getJSONObject(i);
                        String title = news.getString("title");
                        String description = news.getString("description");
                        String publishedAt = news.getString("publishedAt");
                        News news1 = new News(title,description,publishedAt);
                        newsList.add(news1);
                        adapter = new newsAdapter(newsList,this);
                        recyclerView.setAdapter(adapter);
                        Log.e("title",title);
                        Log.e("description",description);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("User-Agent", "user-agent=Mozilla/5.0");
            return headers;
        }
    };




        queue.add(jsonObject);


}

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
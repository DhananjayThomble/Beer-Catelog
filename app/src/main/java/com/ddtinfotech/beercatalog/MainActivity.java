package com.ddtinfotech.beercatalog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ddtinfotech.beercatalog.adapter.BeerAdapter;
import com.ddtinfotech.beercatalog.util.UtilityClass;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    int beerListSize = 10;
    final Handler handler = new Handler(Looper.getMainLooper());
    UtilityClass utilityClass = new UtilityClass();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        setUpRecyclerView(beerListSize);

        //send request to get beer list every 10 seconds
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                beerListSize = utilityClass.getRandomNum();
                setUpRecyclerView(beerListSize);
                handler.postDelayed(this, 10000);
            }
        }, 10000);
    }

    private void stopHandler() {
        handler.removeCallbacksAndMessages(null);
    }

    public boolean isNetworkConnected() {
        UtilityClass connectionManager = new UtilityClass();
        Log.d("test1", "isNetworkConnected: Internet is present");
        return connectionManager.isNetworkAvailable(this);
    }

    private void setUpRecyclerView(int listSize) {

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        /*Create a queue for sending the request*/
        RequestQueue queue = Volley.newRequestQueue(this);

        /*Check if the internet is present or not*/
        if (isNetworkConnected()) {
            String url = "https://random-data-api.com/api/v2/beers?size=" + String.valueOf(listSize) + "&response_type=json";

//            /*Create a request*/
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    Log.d("test1", "onResponse: " + "response is received");

                    try {
                        List<Beer> beerList = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
//                            Log.d("test1", "obj: " + jsonObject);
                            String name = jsonObject.getString("name");
                            String brand = jsonObject.getString("brand");
                            String alcoholContent = jsonObject.getString("alcohol");
                            beerList.add(new Beer(name, brand, alcoholContent));
                        }
                        BeerAdapter beerAdapter = new BeerAdapter(beerList, MainActivity.this);
                        recyclerView.setAdapter(beerAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                        progressBar.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("test1", "onResponse: " + e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("test1", "onErrorResponse: " + error);
                }
            });

            queue.add(request);
        } else {
//             for no internet connection
            Snackbar.make(findViewById(R.id.container), "No Internet Connection", Snackbar.LENGTH_LONG).show();

        }
    }

    @Override
    public void onBackPressed() {
        Log.d("test1", "onBackPressed: ");
        //stop the handler
        stopHandler();
        //start the onCreate() function when app is opened again
        super.onBackPressed();
    }


}
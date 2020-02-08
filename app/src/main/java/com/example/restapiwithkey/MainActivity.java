package com.example.restapiwithkey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    ConnectivityManager cm;
    boolean isConnected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DataService service = RetrofitClient.getRetrofitInstance(getApplicationContext()).create(DataService.class);


        cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNet = cm.getActiveNetworkInfo();

        isConnected = activeNet !=null && activeNet.isConnectedOrConnecting();
        if(!isConnected)
        {
            Toast.makeText(getApplicationContext(),"check internet",Toast.LENGTH_LONG).show();
        }
        else
        {
            Call<List<plantpojo>> call = service.getALLPlants(getString(R.string.token));
            call.enqueue(new Callback<List<plantpojo>>() {
                @Override
                public void onResponse(Call<List<plantpojo>> call, Response<List<plantpojo>> response) {


                    List<plantpojo> plantpojos = new ArrayList<>(response.body());

                    if(plantpojos != null)
                    {
                        for(int i=0;i<plantpojos.size();i++)
                        {
                            System.out.println(i+ plantpojos.get(i).getCommonName());
                        }
                    }
                }

                @Override
                public void onFailure(Call<List<plantpojo>> call, Throwable t) {

                }
            });
        }




    }
}

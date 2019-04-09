package com.daninpr.tugascatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.daninpr.tugascatapp.generator.ServiceGenerator;
import com.daninpr.tugascatapp.model.Cat;
import com.daninpr.tugascatapp.services.CatService;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private ImageView iconImage;
    private CatService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iconImage = findViewById(R.id.image_icon);
        service = ServiceGenerator.createService(CatService.class);

        Button moreButton = findViewById(R.id.button_more);

        moreCat();

        moreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moreCat();
            }
        });
    }

    private void moreCat() {
        Call<Cat> catResponse = service.getRandomCat();
        catResponse.enqueue(new Callback<Cat>() {
            @Override
            public void onResponse(Call<Cat> call, Response<Cat> response) {
                Cat cat = response.body();
                Picasso.get().load(cat.getImage()).into(iconImage);
            }

            @Override
            public void onFailure(Call<Cat> call, Throwable t) {
                Log.e(TAG, t.toString());
                String message = "Failed to get more joke, please check your connection.";
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

            }
        });
    }
}

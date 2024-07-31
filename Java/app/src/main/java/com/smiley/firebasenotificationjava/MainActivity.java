package com.smiley.firebasenotificationjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.smiley.firebasenotificationjava.service.ApiService;
import com.smiley.firebasenotificationjava.service.client.RetrofitClient;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Button send;
    String token = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if(task.isSuccessful()){
                    token = task.getResult();
                }else {
                    Log.d("Error", Objects.requireNonNull(task.getException()).toString());
                }
            }
        });
        StrictMode.ThreadPolicy policy =new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        findViewById(R.id.sendNotification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("SEND NOTIFICATION TOKEN",token);
                ApiService apiService = RetrofitClient.getClient().create(ApiService.class);

                User user = new User("John Doe", "john.doe@example.com");
                Call<UserResponse> call = apiService.createUser(user);

                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            UserResponse userResponse = response.body();
                            // Handle the response
                            Log.d("API Response", "User created: " + userResponse.getName());
                        } else {
                            // Handle the error
                            Log.d("API Response", "Error: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        // Handle the failure
                        Log.d("API Response", "Failure: " + t.getMessage());
                    }
                });
            }
        });
    }
}
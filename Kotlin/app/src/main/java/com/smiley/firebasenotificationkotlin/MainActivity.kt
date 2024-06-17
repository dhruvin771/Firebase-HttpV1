package com.smiley.firebasenotificationkotlin

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.messaging

class MainActivity : AppCompatActivity() {
    lateinit var sendNotification: Button;
    var token: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                token = task.result
            } else {
                Log.w("Failed", "Fetching FCM registration token failed", task.exception)
            }
        }

        sendNotification = findViewById(R.id.send)

        sendNotification.setOnClickListener {
            Log.d("Token",token.toString())
        }

        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }
}
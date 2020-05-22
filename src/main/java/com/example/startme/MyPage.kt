package com.example.startme

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MyPage() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_page)
        val prefs = getSharedPreferences("Program State", Context.MODE_PRIVATE)
        val email = intent.getStringExtra(R.string.user.toString())
        val user = MainActivity.users.get(email)!!
        val name: TextView = findViewById<EditText>(R.id.Name)
        //Users information prints at the screen
        name.setText(resources.getString(R.string.name) + ": "
                + user.getName())
        val surname: TextView = findViewById<EditText>(R.id.Surname)
        surname.setText(resources.getString(R.string.surname) + ": "
                + user.getSurname())
        val emailText: TextView = findViewById<EditText>(R.id.Email)
        emailText.setText(resources.getString(R.string.prompt_email) + ": "
                + user.getEmail())
        val image: ImageView = findViewById<ImageView>(R.id.Avatar)
        //if user doesn't have the avatar, systems gives him a default one
        if (user.getImage()!= -1) {
            image.setImageResource(user.getImage())
        } else {
            image.setImageResource(R.drawable.default_image)
        }
        val button = findViewById<Button>(R.id.SignOut)
        //signing out leads to a cleaning of cache
        button.setOnClickListener {
            prefs.edit().clear().apply()
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

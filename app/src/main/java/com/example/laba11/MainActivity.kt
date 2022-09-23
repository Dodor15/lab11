package com.example.laba11

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var btn: Button
    private lateinit var gobtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

      btn = findViewById(R.id.button)
      gobtn = findViewById(R.id.button2)

      btn.setOnClickListener{
          val intent = Intent(this, SecondActivity::class.java)
          startActivity(intent)
      }
       gobtn.setOnClickListener{
           val intent= Intent (this,ListActivity::class.java)
           startActivity(intent)
       }
    } }
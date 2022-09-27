package com.example.laba11

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {
    private lateinit var btn: Button
    private lateinit var gobtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val preferences=getSharedPreferences("str2", Context.MODE_PRIVATE)
      btn = findViewById(R.id.button)
      gobtn = findViewById(R.id.button2)
        if(preferences.contains("str2")==false){
            gobtn.isEnabled=false
        }

      btn.setOnClickListener{
          val intent = Intent(this, SecondActivity::class.java)
          startActivity(intent)
      }
       gobtn.setOnClickListener{
           val intent= Intent (this,ListActivity::class.java)
           intent.putExtra("boolean", true)
           startActivity(intent)
       }
    }
}

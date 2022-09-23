package com.example.laba11

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import layout.BookRVAdapter

class ListActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private val bookList: MutableList<Books> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

//        contactList.addAll(Books().getBook(getSharedPreferences("pref",
//            MODE_PRIVATE)))
        val adapter = BookRVAdapter(this, bookList)
        rv = findViewById(R.id.recyclerView)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }
    private fun getBook(){
        val preferences=getSharedPreferences("str", Context.MODE_PRIVATE)
        var json: String=""
        if(!preferences.contains("str")){
            return
        }
        else{
            json=preferences.getString("str","not_json").toString()
        }
        val books = Gson().fromJson<List<Books>>(json, object : TypeToken<List<Books>>(){}.type)
        bookList.addAll(books)
    }
}


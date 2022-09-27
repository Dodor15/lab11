package com.example.laba11

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import layout.BookRVAdapter

class ListActivity : AppCompatActivity() {
    private lateinit var rv: RecyclerView
    private val bookList: MutableList<Books> = mutableListOf()
    var indexChanged=-1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        if(indexChanged==-1) {
            val preferences = getSharedPreferences("str2", Context.MODE_PRIVATE)
            var st = preferences.getString("str2", "why?").toString()
            val save = Gson().fromJson<List<Books>>(st, object : TypeToken<List<Books>>() {}.type)
            bookList.addAll(save)
        }


        val adapter = BookRVAdapter(this, bookList)
        val rvListener = object : BookRVAdapter.ItemClickListener{
            override fun onItemClick(view: View?, position: Int) {
                val intent = Intent(this@ListActivity,SecondActivity::class.java)
               indexChanged= position
                intent.putExtra("num", position)
                startActivity(intent)
                Toast.makeText(this@ListActivity, "position: $position",
                    Toast.LENGTH_SHORT).show()
            }
        }
        adapter.setClickListener(rvListener)
        rv = findViewById(R.id.recyclerView)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        if(indexChanged !=-1){
            bookList.clear()
            val preferences=getSharedPreferences("str2", Context.MODE_PRIVATE)
            var st=preferences.getString("str2","why?").toString()
            val save = Gson().fromJson<List<Books>>(st,object :TypeToken<List<Books>>(){}.type)
            bookList.addAll(save)
            rv.adapter?.notifyItemChanged(indexChanged)
        }
    }
}
//    private fun getBook(){
//        val preferences=getSharedPreferences("str", Context.MODE_PRIVATE)
//        var json: String=""
//        if(!preferences.contains("str")){
//            return
//        }
//        else{
//            json=preferences.getString("str","not_json").toString()
//        }
//        val books = Gson().fromJson<List<Books>>(json, object : TypeToken<List<Books>>(){}.type)
//        bookList.addAll(books)
//    }
//}


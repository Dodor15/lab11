package com.example.laba11

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SecondActivity : AppCompatActivity() {
    private lateinit var titl: EditText
    private lateinit var autr:EditText
    private lateinit var ind:EditText
    private lateinit var count:EditText
    private lateinit var btn: Button
    val list: MutableList<Books> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        titl=findViewById(R.id.title)
        autr=findViewById(R.id.authors)
        ind=findViewById(R.id.index)
        count=findViewById(R.id.page)
        btn=findViewById(R.id.button3)

        val preferences=getSharedPreferences("str",Context.MODE_PRIVATE)
        val edit = preferences.edit()


        if(preferences.contains("str")==true){
            var st=preferences.getString("str","why?").toString()
            val save = Gson().fromJson<List<Books>>(st,object :TypeToken<List<Books>>(){}.type)
            list.addAll(save)
        }
       //val list: MutableList<Books>
        //val autList= autr.text.split(" ")
        btn.setOnClickListener{
            if (titl.text.toString()!=""&&autr.text.toString()!=""&&ind.text.toString()!=""&&count.text.toString()!=""){
                val autList= autr.text.split(" ")

                val book = Books(titl.text.toString(), autList,ind.text.toString(),count.text.toString().toInt())
                list.add(book)

                Toast.makeText(this,"Сохранено",Toast.LENGTH_SHORT).show()

                val books: String = Gson().toJson(list)
                edit.putString("str", books)
                edit.apply()

                Log.d("book", books.toString())
            }
            else{
                Toast.makeText(this,"Не всё записано",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
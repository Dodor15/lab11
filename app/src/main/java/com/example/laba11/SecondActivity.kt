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
        val list: MutableList<String> = mutableListOf(preferences.getString("str","why?").toString())

        btn.setOnClickListener{
            if (titl.text.toString()!=""&&autr.text.toString()!=""&&ind.text.toString()!=""&&count.text.toString()!=""){
                val book = Books(titl.text.toString(), arrayListOf("dsfsd", "rfwerfwe"),ind.text.toString(),count.text.toString().toInt())

                list.add(titl.text.toString())
                list.add(autr.text.toString())
                list.add(ind.text.toString())
                list.add(count.text.toString())
                edit.putString("str", list.toString())
                edit.apply()
                Toast.makeText(this,"Сохранено",Toast.LENGTH_SHORT).show()
                val books: List<Books> = Gson().toJson<List<Books>>(list, object : TypeToken<List<Books>>(){}.type)
                Log.d("book", books.toString())
            }
            else{
                Toast.makeText(this,"Не всё записано",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
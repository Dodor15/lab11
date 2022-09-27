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
    var index:Int = -1
    val list: MutableList<Books> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        titl=findViewById(R.id.title)
        autr=findViewById(R.id.authors)
        ind=findViewById(R.id.index)
        count=findViewById(R.id.page)
        btn=findViewById(R.id.button3)

        val preferences=getSharedPreferences("str2",Context.MODE_PRIVATE)
        val edit = preferences.edit()
        val position: Int = intent.getIntExtra("num", -1)
        val boolean: Boolean = intent.getBooleanExtra("num", false)
        var bool: Boolean=false
        bool=boolean
        index=position

        if(preferences.contains("str2")==true){
            var st=preferences.getString("str2","why?").toString()
            val save = Gson().fromJson<List<Books>>(st,object :TypeToken<List<Books>>(){}.type)
            list.addAll(save)
        }
        if (index!=-1){
            titl.setText(list[index].title)
            autr.setText(list[index].authors.toString())
            ind.setText(list[index].isindex)
            count.setText(list[index].pageCount.toString())
        }

        btn.setOnClickListener{
//            if(preferences.contains("str2")==true){
//                var st=preferences.getString("str2","why?").toString()
//                val save = Gson().fromJson<List<Books>>(st,object :TypeToken<List<Books>>(){}.type)
//                list.addAll(save)
//            }
            if (titl.text.toString()!=""&&autr.text.toString()!=""&&ind.text.toString()!=""&&count.text.toString()!=""){
                if(index==-1){


                val autList= autr.text.split(" ")

                val book = Books(titl.text.toString(), autList,ind.text.toString(),count.text.toString().toInt())
                list.add(book)

                Toast.makeText(this,"Сохранено",Toast.LENGTH_SHORT).show()

                val books: String = Gson().toJson(list)
                edit.putString("str2", books)
                edit.apply()

                Log.d("book", books.toString())
                }
                else{
                    list[index].pageCount=count.text.toString().toInt()
                    list[index].authors= listOf(autr.text.toString())
                    list[index].title=titl.text.toString()
                    list[index].isindex=ind.text.toString()
                    val books: String = Gson().toJson(list)
                    edit.putString("str2", books)
                    edit.apply()
                }
            }
            else{
                Toast.makeText(this,"Не всё записано",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
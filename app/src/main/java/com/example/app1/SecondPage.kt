package com.example.app1

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondPage : AppCompatActivity() {

    private var first: String? = null
    private var middle: String? = null
    private var last: String? = null
    private var flName: String? = null

    private var Tvname: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val receivedIntent = intent

        first = receivedIntent.getStringExtra("ET_FIRST")

        middle = receivedIntent.getStringExtra("ET_MIDDLE")
        last = receivedIntent.getStringExtra("ET_LAST")



        Tvname = findViewById(R.id.tv_fullName)
        Tvname!!.text = first + " " + last + " is logged in!"



    }
}
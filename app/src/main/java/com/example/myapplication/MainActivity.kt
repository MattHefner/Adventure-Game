package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClickStart(view: View) {
        val buttonClick = Intent(this@MainActivity, tutorialView::class.java)
        startActivity(buttonClick)
    }



    fun buttonClickExit(view: View) {
        finish()
        System.exit(0)
    }


}

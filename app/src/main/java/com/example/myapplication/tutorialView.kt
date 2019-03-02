package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView


class tutorialView : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial_view)

    }

    /*fun animate() {
        animation.reset()
        introView.clearAnimation()
        introView.startAnimation(animation)
        introView.clearAnimation()
    }*/

    /*fun arrayDialogue(array:String){
        var dialogue = arrayOf("test","this","code")
        for(i in 0..2){
            return //dialogue[i]
        }
    }*/

    fun intro(view: View) {

        var animation = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in)
        animation.reset()
        var introView = findViewById<TextView>(R.id.intro)

        var i = 0
        var dialogSize = resources.getStringArray(R.array.dialog).size

        introView.setOnClickListener {
            if (i != (dialogSize)) {
                introView.text = resources.getStringArray(R.array.dialog)[i]
                i++
                introView.clearAnimation()
                introView.startAnimation(animation)
            } else {
                val sceneChange = Intent(this@tutorialView, scene1::class.java)
                startActivity(sceneChange)
            }
        }

    }
}

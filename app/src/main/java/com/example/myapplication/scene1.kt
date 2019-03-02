package com.example.myapplication

import android.animation.Animator
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import java.util.*


class scene1 : AppCompatActivity() {

    var enemyHealth: Int = 20
    var heroHealth: Int = 20
    var rand = Random()





    //var enemyRefreshHealth = enemyHealth - slashHit


    //var testText:String = "Battle Start!"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scene1)


        var battleTextView = findViewById<TextView>(R.id.battle_text)

        var enemyTextView = findViewById<TextView>(R.id.enemy_text)

        var animation = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in)
        animation.reset()
        var enemyTitle = findViewById<TextView>(R.id.enemy_title)


        var entitySize = resources.getStringArray(R.array.entity).size

        var enemySelect = rand.nextInt(entitySize) + 0

        val enemyName = resources.getStringArray(R.array.entity)[enemySelect]


        enemyTitle.text = enemyName

        enemyTitle.clearAnimation()
        enemyTitle.startAnimation(animation)

        var enemyHealthView = findViewById<TextView>(R.id.enemyHealth)
        enemyHealthView.text = enemyHealth.toString()

        var heroHealthView = findViewById<TextView>(R.id.heroHealth)
        heroHealthView.text = heroHealth.toString()


        var slashButton = findViewById<Button>(R.id.attack_button)
        slashButton.setOnClickListener { ability1(battleTextView)}

        var healButton = findViewById<Button>(R.id.heal_button)
        healButton.setOnClickListener {ability2(battleTextView)}

        if(heroHealth >= 20){
            healButton.visibility = View.INVISIBLE
            heroHealth = 20
        }else{
            healButton.visibility = View.VISIBLE
        }
        //battleTextStart()




    }

    //Change the buttons to be located on a permanent bar, and disable the buttons when battle is in progress
    //have the abilities change colors when you can use or just make them disappear during battle


    fun ability1(view: View) {
        val enemyHealthView = findViewById<TextView>(R.id.enemyHealth)
        val attackButton = findViewById<Button>(R.id.attack_button)
        var healButton = findViewById<Button>(R.id.heal_button)




            if (view is TextView) {
                attackButton.visibility = View.INVISIBLE
                healButton.visibility = View.INVISIBLE

                val textView = view


                val slashHit = rand.nextInt(4) + 1
                var slashValue = slashHit.toString()
                textView.setTextColor(Color.RED)
                val slashText: String = "Slash hit for $slashHit"
                textView.text = slashText
                enemyHealth -= slashHit
                enemyHealthView.text = enemyHealth.toString()
                textView.setVisibility(View.VISIBLE)
                if(enemyHealth <= 0){
                    enemyHealthView.text = "Dead"
                    textView.text= "It's dead, Please Stop"
                    attackButton.visibility = View.INVISIBLE
                    /*finish()
                    System.exit(0)*/
                }else{
                textView.animate().setDuration(2000).setListener(object : Animator.AnimatorListener {

                    override fun onAnimationStart(animation: Animator?) {
                        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.abc_fade_in)

                        textView.clearAnimation()
                        textView.startAnimation(animation)

                    }

                    override fun onAnimationEnd(animation: Animator) {
                        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.abc_fade_out)

                        textView.clearAnimation()
                        textView.startAnimation(animation)
                        textView.visibility = View.GONE
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        //Toast.makeText(applicationContext, "Inside cancel", Toast.LENGTH_SHORT).show()
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                        //Toast.makeText(applicationContext, "Inside repeat", Toast.LENGTH_SHORT).show()
                    }

                }).start()




        }
                if(enemyHealth > 0){
                Handler().postDelayed({
                    enemyAttack()
                }, 2500)

            }}else{
                enemyHealthView.text = "Dead"
            }


    }

    /**
     * This is a function that is evoked when the healButton is clicked. This gets a random number 1 - 4
     * and displays it on the screen at battleTextView
     */

    fun ability2(view: View) {
        val heroHealthView = findViewById<TextView>(R.id.heroHealth)
        val attackButton = findViewById<Button>(R.id.attack_button)
        var healButton = findViewById<Button>(R.id.heal_button)



        if (view is TextView) {
            attackButton.visibility = View.INVISIBLE
            healButton.visibility = View.INVISIBLE

            val textView = view



            val healNum = rand.nextInt(4) + 1
            var healValue = healNum.toString()
            textView.setTextColor(Color.GREEN)
            val healText: String = "Healed for $healNum"
            textView.text = healText
            heroHealth += healNum
            heroHealthView.text = heroHealth.toString()
            textView.setVisibility(View.VISIBLE)

            if(heroHealth >= 20){
                heroHealthView.text = "20"
                healButton.visibility = View.INVISIBLE

            }else{
                textView.animate().setDuration(2000).setListener(object : Animator.AnimatorListener {

                    override fun onAnimationStart(animation: Animator?) {
                        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.abc_fade_in)

                        textView.clearAnimation()
                        textView.startAnimation(animation)

                    }

                    override fun onAnimationEnd(animation: Animator) {
                        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.abc_fade_out)

                        textView.clearAnimation()
                        textView.startAnimation(animation)
                        textView.visibility = View.GONE
                    }

                    override fun onAnimationCancel(animation: Animator?) {
                        //Toast.makeText(applicationContext, "Inside cancel", Toast.LENGTH_SHORT).show()
                    }

                    override fun onAnimationRepeat(animation: Animator?) {
                        //Toast.makeText(applicationContext, "Inside repeat", Toast.LENGTH_SHORT).show()
                    }

                }).start()



                //but enemy battle actions here, make button reappear after



            }
            Handler().postDelayed({
                enemyAttack()
            }, 2500)
        }




    }




    fun enemyAttack(){
        val heroHealthView = findViewById<TextView>(R.id.heroHealth)
        var enemyHealthView = findViewById<TextView>(R.id.enemyHealth)
        //var battleTextView = findViewById<TextView>(R.id.battle_text)
        var enemyTitle = findViewById<TextView>(R.id.enemy_title)
        var enemyTextView = findViewById<TextView>(R.id.enemy_text)
        val attackButton = findViewById<Button>(R.id.attack_button)
        var healButton = findViewById<Button>(R.id.heal_button)
        val enemyNameHolder = enemyTitle.getText()

        enemyTextView.visibility = View.VISIBLE

        var ranNum = rand.nextInt(2) + 1

        val enemyAttack = rand.nextInt(4) + 1

        val enemyHeal = rand.nextInt(3) + 1

        if(enemyHealth < 8){
            ranNum = 3
        }

        if(ranNum == 1){
            heroHealth-=enemyAttack
            val enemyAttackText = "$enemyNameHolder attacked for $enemyAttack"
            enemyTextView.text = enemyAttackText
        }

        //put an ability for ranNum == 2

        if(ranNum == 3){
            enemyHealth += enemyHeal
            val enemyHealText = "$enemyNameHolder healed for $enemyHeal"
            enemyTextView.text = enemyHealText
        }

        if(heroHealth <= 0){
            heroHealthView.text = "Dead"
        }else {
            heroHealthView.text = heroHealth.toString()
        }
        if(enemyHealth >= 20){
            enemyHealthView.text = "20"
        }else {

            enemyHealthView.text = enemyHealth.toString()
        }

        enemyTextView.animate().setDuration(2500).setListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(animation: Animator?) {
                //Toast.makeText(applicationContext, "Inside start", Toast.LENGTH_SHORT).show()
                val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.abc_fade_in)

                enemyTextView.clearAnimation()
                enemyTextView.startAnimation(animation)

            }

            override fun onAnimationEnd(animation: Animator) {
                //Toast.makeText(applicationContext, "Inside end", Toast.LENGTH_SHORT).show()
                val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.abc_fade_out)

                enemyTextView.clearAnimation()
               enemyTextView.startAnimation(animation)
               enemyTextView.visibility = View.GONE

                attackButton.visibility = View.VISIBLE
                healButton.visibility = View.VISIBLE
            }

            override fun onAnimationCancel(animation: Animator?) {
                //Toast.makeText(applicationContext, "Inside cancel", Toast.LENGTH_SHORT).show()
            }

            override fun onAnimationRepeat(animation: Animator?) {
                //Toast.makeText(applicationContext, "Inside repeat", Toast.LENGTH_SHORT).show()
            }

        }).start()

    }



    fun ability3() {


    }}

    /* fun battleTextStart(){
         battleTextView.animate().alpha(1.0f)
         battleTextView.text = testText
         battleTextView.animate().alpha(0.0f).duration = 4000

     }*/








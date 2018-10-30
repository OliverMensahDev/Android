package com.buildappswithpaulo.breathe

import android.content.Intent
import android.icu.text.MessageFormat
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.buildappswithpaulo.breathe.Util.Prefs
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var prefs: Prefs
    lateinit var countDownTimer: CountDownTimer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = Prefs(this)


        breathsTakenTxt.text = MessageFormat.format("{0} min today", prefs.sessions)
        todayMinusText.text = MessageFormat.format("{0} breaths", prefs.breaths)
        lastBreathTxt.text = prefs.date

        startIntroAnimation()

        startButton.setOnClickListener {

            startAnimation()
        }
    }


    private fun startAnimation() {
        ViewAnimator
                .animate(lotusImageID)
                .alpha(0f, 1f)
                .onStart {
                    guideTxt.text = "Inhale... Exhale"
                }
                .decelerate()
                .duration(1000)
                .thenAnimate(lotusImageID)
                .scale(0.02f, 1.5f, 0.02f)
                .rotation(360f)
                .repeatCount(5)
                .accelerate()
                .duration(5000)
                .onStop {
                    guideTxt.text = "Good Job"
                    lotusImageID.scaleX = 1.0f
                    lotusImageID.scaleY = 1.0f

                    prefs.sessions = prefs.sessions + 1
                    prefs.breaths = prefs.breaths + 1
                    prefs.setDate(System.currentTimeMillis())


                    val handler = Handler()
                    val countDownTimer = Runnable {
                        startActivity(Intent(this@MainActivity, MainActivity::class.java))
                        finish()
                    }
                    handler.postDelayed(countDownTimer, 100)


                    //refresh the activity

//                    object : CountDownTimer(10000, 1000) {
//                        @SuppressLint("ShowToast")
//                        override fun onFinish() {
//
//                            startActivity(Intent(this@MainActivity, MainActivity::class.java))
//                            finish()
//
//                        }
//
//                        override fun onTick(millisUntilFinished: Long) {
//
//                        }
//
//                    }

                }
                //.start()// for our countdown timer

                .start() // for animation

    }

    private fun startIntroAnimation() {
        ViewAnimator
                .animate(guideTxt)
                .scale(0f, 1f)
                .duration(1500)
                .onStart {
                    guideTxt.text = "Breathe"
                }
                .start()

    }


}

package com.devcode.tourifyapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Matrix
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import com.devcode.tourifyapp.databinding.ActivitySplashScreenBinding

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        playAnimation()
        customView1()
    }

    private fun playAnimation() {
        val motionLayout = binding.splashScreenMotionLayout
        motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                if (progress >= 0.54f) {
                    val window = window
                    window?.statusBarColor = ContextCompat.getColor(this@SplashScreen, R.color.white)
                    window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    customSpanTitleLogo()
                } else {
                    val window = window
                    window?.statusBarColor = ContextCompat.getColor(this@SplashScreen, R.color.orange_400)
                    window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    binding.titleLogo.setTextColor(Color.parseColor("#FFFFFF"))
                }
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                startActivity(Intent(this@SplashScreen, OnBoardingActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}

        })
    }

    private fun customSpanTitleLogo() {
        val txtLogo = binding.titleLogo
        val spannableString = SpannableString(resources.getString(R.string.title_logo))
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#06093D")),
            0,
            4,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannableString.setSpan(
            ForegroundColorSpan(Color.parseColor("#FA6643")),
            4,
            7,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        txtLogo.text = spannableString
    }

    private fun customView1() {
        val imageView = binding.abstract1
        val matrix = Matrix()

        val scaleFactor = 1f
        val translateX = -295f

        matrix.setScale(scaleFactor, scaleFactor)
        matrix.postTranslate(translateX, 0f)
        imageView.imageMatrix = matrix
    }
}


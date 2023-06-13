package com.devcode.tourifyapp.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Matrix
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import com.devcode.tourifyapp.MainActivity
import com.devcode.tourifyapp.OnBoardingActivity
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.ActivitySplashScreenBinding
import com.devcode.tourifyapp.ui.home.HomeFragment
import com.devcode.tourifyapp.utils.ViewModelFactory

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    private lateinit var viewModel: SplashScreenViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()
        playAnimation()
        customView1()
    }

    private fun setUpViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModels: SplashScreenViewModel by viewModels { factory }
        viewModel = viewModels
    }

    private fun playAnimation() {
        val motionLayout = binding.splashScreenMotionLayout
        motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                if (progress >= 0.54f) {
                    val window = window
                    window?.statusBarColor = ContextCompat.getColor(this@SplashScreen,
                        R.color.white
                    )
                    window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    customSpanTitleLogo()
                } else {
                    val window = window
                    window?.statusBarColor = ContextCompat.getColor(this@SplashScreen,
                        R.color.orange_400
                    )
                    window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                    binding.titleLogo.setTextColor(Color.parseColor("#FFFFFF"))
                }
            }

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                viewModel.getUserPreferences().observe(this@SplashScreen) { result ->
                    if (result.token == "") {
                        startActivity(Intent(this@SplashScreen, OnBoardingActivity::class.java))
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        finish()
                    } else {
                        viewModel.setToken(result.token)
                        startActivity(Intent(this@SplashScreen, MainActivity::class.java))
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                        finish()
                    }
                }
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


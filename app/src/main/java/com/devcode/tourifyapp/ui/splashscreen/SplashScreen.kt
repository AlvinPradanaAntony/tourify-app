package com.devcode.tourifyapp.ui.splashscreen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Matrix
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.TypedValue
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.devcode.tourifyapp.MainActivity
import com.devcode.tourifyapp.OnBoardingActivity
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.ActivitySplashScreenBinding
import com.devcode.tourifyapp.databinding.FragmentSettingsBinding
import com.devcode.tourifyapp.ui.settings.SettingsViewModel
import com.devcode.tourifyapp.utils.ThemesPreferences
import com.devcode.tourifyapp.utils.ViewModelFactory
import com.devcode.tourifyapp.utils.ViewModelFactoryForThemes

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_setting")
@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {
    private lateinit var binding : ActivitySplashScreenBinding
    private lateinit var binding2 : FragmentSettingsBinding
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

        val pref =  ThemesPreferences.getInstance(dataStore)
        val settingsViewModel = ViewModelProvider(this, ViewModelFactoryForThemes(pref)).get(
            SettingsViewModel::class.java
        )
        binding2 = FragmentSettingsBinding.inflate(layoutInflater)
        settingsViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding2.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding2.switchTheme.isChecked = false
            }
        }
    }

    private fun setupView() {

    }

    private fun playAnimation() {
        val motionLayout = binding.splashScreenMotionLayout
        motionLayout.addTransitionListener(object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionChange(motionLayout: MotionLayout?, startId: Int, endId: Int, progress: Float) {
                if (progress >= 0.54f) {
                    val typedValue = TypedValue()
                    val window = window
                    val colorAttr = com.google.android.material.R.attr.colorPrimaryVariant
                    if (theme.resolveAttribute(colorAttr, typedValue, true)) {
                        val color = typedValue.data
                        // Menggunakan warna yang diambil dari atribut gaya colorPrimary
                        window?.statusBarColor = color
                    }
                    customSpanTitleLogo()
                } else {
                    val window = window
                    window?.statusBarColor = ContextCompat.getColor(this@SplashScreen,
                        R.color.orange_400
                    )
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

        val typedValue = TypedValue()
        val theme = this.theme
        theme.resolveAttribute(com.google.android.material.R.attr.colorOnPrimary, typedValue, true)

        val color = typedValue.data
        spannableString.setSpan(
            ForegroundColorSpan(color),
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


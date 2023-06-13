package com.devcode.tourifyapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.devcode.tourifyapp.MainActivity
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.data.model.UserPreferencesModel
import com.devcode.tourifyapp.databinding.ActivityLoginBinding
import com.devcode.tourifyapp.ui.register.RegisterActivity
import com.devcode.tourifyapp.utils.Result
import com.devcode.tourifyapp.utils.ViewModelFactory

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModels: LoginViewModel by viewModels { factory }
        viewModel = viewModels

        setupView()
        setupAction()
    }

    private fun setupView() {
        window?.statusBarColor = ContextCompat.getColor(this@LoginActivity, R.color.white)
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }

    private fun setupAction() {
        binding.txtRegisternow.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        binding.buttonLogin.setOnClickListener {
            val email = binding.edLoginEmail.text?.trim().toString()
            val password = binding.edLoginPassword.text?.trim().toString()

            if (email.isEmpty() && password.isEmpty()) {
                AlertDialog.Builder(this).apply {
                    setTitle("Oops!")
                    setMessage(R.string.email_n_password_empty)
                    setPositiveButton("OK") { _, _ -> }
                    create()
                    show()
                }
            } else {
//                if (email.isEmpty()) {
//                    binding.edLoginEmail.error = resources.getString(R.string.email_empty)
//                    binding.edLoginEmail.requestFocus()
//                } else if (password.isEmpty()) {
//                    binding.edLoginPassword.error = resources.getString(R.string.password_empty)
//                    binding.edLoginPassword.requestFocus()
//                } else if (!isValidEmail(email)) {
//                    binding.edLoginEmail.error = resources.getString(R.string.email_invalid)
//                    binding.edLoginEmail.requestFocus()
//                } else if (password.length < 8) {
//                    binding.edLoginPassword.error =
//                        resources.getString(R.string.password_minimum_character)
//                    binding.edLoginPassword.requestFocus()
//                } else {
                    binding.edLoginEmail.clearFocus()
                    binding.edLoginPassword.clearFocus()
                    hideKeyboard()
                    login(email, password)
//                }
            }
        }
    }

    private fun login(email: String, password: String) {
        viewModel.doLogin(email, password).observe(this) { response ->
            if (response != null) {
                when (response) {
                    Result.Loading -> showLoading(true)
                    is Result.Success -> {
                        showLoading(false);
                        Toast.makeText(this, "Login Success, ${response.data.name}", Toast.LENGTH_SHORT).show()
                        viewModel.saveUserPreference(response.data)
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    is Result.Error -> TODO()
                }
            }
        }
//        showLoading(false)
//        startActivity(Intent(this, MainActivity::class.java))
        // ViewModel
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.overlayBg.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
package com.devcode.tourifyapp.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.devcode.tourifyapp.MainActivity
import com.devcode.tourifyapp.R
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

        setupAction()
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
                if (email.isEmpty()) {
                    binding.edLoginEmail.error = resources.getString(R.string.email_empty)
                    binding.edLoginEmail.requestFocus()
                } else if (password.isEmpty()) {
                    binding.edLoginPassword.error = resources.getString(R.string.password_empty)
                    binding.edLoginPassword.requestFocus()
                } else if (!isValidEmail(email)) {
                    binding.edLoginEmail.error = resources.getString(R.string.email_invalid)
                    binding.edLoginEmail.requestFocus()
                } else if (password.length < 8) {
                    binding.edLoginPassword.error =
                        resources.getString(R.string.password_minimum_character)
                    binding.edLoginPassword.requestFocus()
                } else {
                    binding.edLoginEmail.clearFocus()
                    binding.edLoginPassword.clearFocus()
                    hideKeyboard()
                    login(email, password)
                }
            }
        }
    }

    private fun login(email: String, password: String) {
        viewModel.doLogin(email, password).observe(this) { response ->
            if (response != null) {
                when (response) {
                    Result.Loading -> showLoading(true)
                    is Result.Success -> {
                        showLoading(false)
                        viewModel.saveUserPreference(response.data)
                        val i = Intent(this, MainActivity::class.java)
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        startActivity(i)
                        finish()
                    }
                    is Result.Error -> {
                        showLoading(false)
                        AlertDialog.Builder(this).apply {
                            setTitle("Oops!")
                            setMessage(response.error)
                            setPositiveButton("OK") { _, _ -> }
                            create()
                            show()
                        }
                    }
                }
            }
        }
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
package com.devcode.tourifyapp.ui.register

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
import androidx.core.content.ContextCompat.getSystemService
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.ActivityRegisterBinding
import com.devcode.tourifyapp.ui.login.LoginActivity
import com.devcode.tourifyapp.utils.Result
import com.devcode.tourifyapp.utils.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupAction()
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModels: RegisterViewModel by viewModels { factory }
        viewModel = viewModels
    }

    private fun setupAction() {
        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
        binding.txtLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }
        binding.buttonRegister.setOnClickListener {
            val fullName = binding.edRegisterName.text?.trim().toString()
            val emailRegister = binding.edRegisterEmail.text?.trim().toString()
            val passwordRegister = binding.edRegisterPassword.text?.trim().toString()
            val confirmPasswordRegister = binding.edRegisterConfirmPass.text?.trim().toString()

            if (fullName.isEmpty() && emailRegister.isEmpty() && passwordRegister.isEmpty()) {
                AlertDialog.Builder(this).apply {
                    setTitle("Oops!")
                    setMessage(R.string.email_n_password_empty)
                    setPositiveButton("OK") { _, _ -> }
                    create()
                    show()
                }
            } else {
                if (fullName.isEmpty()) {
                    binding.edRegisterName.error = resources.getString(R.string.full_name_empty)
                    binding.edRegisterName.requestFocus()
                } else if (emailRegister.isEmpty()) {
                    binding.edRegisterEmail.error = resources.getString(R.string.email_empty)
                    binding.edRegisterEmail.requestFocus()
                } else if (passwordRegister.isEmpty()) {
                    binding.edRegisterPassword.error = resources.getString(R.string.password_empty)
                    binding.edRegisterPassword.requestFocus()
                } else if (confirmPasswordRegister.isEmpty()) {
                    binding.edRegisterConfirmPass.error = resources.getString(R.string.password_confirmation_empty)
                    binding.edRegisterConfirmPass.requestFocus()
                } else if (!isValidEmail(emailRegister)) {
                    binding.edRegisterEmail.error = resources.getString(R.string.email_invalid)
                    binding.edRegisterEmail.requestFocus()
                } else if (passwordRegister.length < 8) {
                    binding.edRegisterPassword.error =
                        resources.getString(R.string.password_minimum_character)
                    binding.edRegisterPassword.requestFocus()
                } else if (confirmPasswordRegister != passwordRegister) {
                    binding.edRegisterConfirmPass.error = resources.getString(R.string.password_not_match)
                    binding.edRegisterConfirmPass.requestFocus()
                } else {
                    binding.edRegisterName.clearFocus()
                    binding.edRegisterEmail.clearFocus()
                    binding.edRegisterPassword.clearFocus()
                    hideKeyboard()
                    register(fullName, emailRegister, passwordRegister)
                }
            }
        }
    }

    private fun register(name:String, email: String, password: String) {
        viewModel.doRegister(name, email, name, password).observe(this) { response ->
            if (response != null) {
                when (response) {
                    Result.Loading -> showLoading(true)
                    is Result.Success -> {
                        showLoading(false);
                        showSnackBar(response.data.message)
                        finish()
                    }
                    is Result.Error -> {
                        showLoading(false);
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

    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun showSnackBar(value: String) {
        Snackbar.make(
            binding.buttonRegister, value, Snackbar.LENGTH_SHORT
        ).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.overlayBg.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}
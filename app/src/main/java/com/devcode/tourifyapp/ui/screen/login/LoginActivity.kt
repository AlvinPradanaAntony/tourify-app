package com.devcode.tourifyapp.ui.screen.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.devcode.tourifyapp.databinding.ActivityLoginBinding
import com.devcode.tourifyapp.utils.Result
import com.devcode.tourifyapp.utils.ViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this@LoginActivity)
        val viewModels: LoginViewModel by viewModels { factory }
        viewModel = viewModels

        binding.apply {
            btnLogin.setOnClickListener {
                val email = edEmail.text.toString()
                val password = edPassword.text.toString()
                doLogin(email, password)
            }
        }
    }

    private fun doLogin(email: String, password: String) {
        viewModel.doLogin(email, password).observe(this) { result ->
            if (result != null) {
                when (result) {
                    Result.Loading -> {
                        Log.d("TAG LOADING", "LOADING...")
                    }
                    is Result.Success -> {
                        Toast.makeText(this@LoginActivity, "Login Sukses, ${result.data.name}", Toast.LENGTH_SHORT).show()
                    }
                    is Result.Error -> {
                        Log.e("ERROR", "doLogin: ${result.error}" )
                    }
                }
            }
        }
    }
}
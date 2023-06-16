package com.devcode.tourifyapp.ui.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devcode.tourifyapp.MainActivity
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.FragmentSettingsBinding
import com.devcode.tourifyapp.ui.customView.CustomDialogChangetPass
import com.devcode.tourifyapp.ui.login.LoginActivity
import com.devcode.tourifyapp.utils.ViewModelFactory


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupView()
        setupAction()
        getUser()
        logout()
    }

    private fun getUser() {
        settingsViewModel.getUserPreferences().observe(requireActivity()) {
            binding.tvWelcome.text = it.name
        }
    }

    private fun logout() {
        binding.logout.setOnClickListener {
            settingsViewModel.doLogout()
            val i = Intent(requireActivity(), LoginActivity::class.java)
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(i)
            requireActivity().finish()
        }
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(requireActivity())
        val viewModels: SettingsViewModel by viewModels { factory }
        settingsViewModel = viewModels
    }

    private fun setupView() {
        settingsViewModel.getThemeSettings().observe(requireActivity()) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }
    }

    private fun setupAction() {
        binding.btnItemChangePassword.setOnClickListener {
            val dialog = CustomDialogChangetPass()
            dialog.show(childFragmentManager, "CustomDialogChangetPass")
        }
        binding.btnLanguage.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
        binding.btnItemInfo.setOnClickListener {
            val alertDialog = AlertDialog.Builder(requireActivity())
            val dialog = alertDialog.create()

            val dialogView = layoutInflater.inflate(R.layout.item_layout_info_dialog, null, false)
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
            dialog.setView(dialogView)
            dialogView.findViewById<View>(R.id.btn_close).setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }
        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingsViewModel.saveThemeSetting(isChecked)
        }
    }
}
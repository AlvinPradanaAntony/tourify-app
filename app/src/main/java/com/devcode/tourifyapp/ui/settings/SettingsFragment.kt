package com.devcode.tourifyapp.ui.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.FragmentSettingsBinding
import com.devcode.tourifyapp.ui.customView.CustomDialogChangetPass
import com.devcode.tourifyapp.utils.ThemesPreferences
import com.devcode.tourifyapp.utils.ViewModelFactoryForThemes

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "theme_setting")
class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    private lateinit var settingsViewModel: SettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupAction()
    }

    private fun setupView() {
        val pref =  ThemesPreferences.getInstance(requireContext().dataStore)
        settingsViewModel = ViewModelProvider(this, ViewModelFactoryForThemes(pref)).get(
            SettingsViewModel::class.java
        )
        settingsViewModel.getThemeSettings().observe(this) { isDarkModeActive: Boolean ->
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
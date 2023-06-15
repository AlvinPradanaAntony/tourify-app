package com.devcode.tourifyapp.ui.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.FragmentSettingsBinding
import com.devcode.tourifyapp.ui.customView.CustomDialog
import com.devcode.tourifyapp.ui.customView.CustomDialogChangetPass


class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAction()
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
    }

    companion object {

    }
}
package com.devcode.tourifyapp.ui.customView

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.devcode.tourifyapp.R
import com.devcode.tourifyapp.databinding.FragmentCustomDialogBinding


class CustomDialog : DialogFragment() {
    private var _binding: FragmentCustomDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAction()
        captureRatingNFeedback()
    }

    private fun setupAction() {
        binding.btnClose.setOnClickListener {
            dialog?.dismiss()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    private fun captureRatingNFeedback() {
        var userRating = 0F

        binding.rating.apply {
            setOnRatingBarChangeListener { _, rating, _ ->
                userRating = rating.also {
                    val value = it.toInt()
                    binding.tvRating.text = "${value}/5"
                }
            }
        }

        binding.btnSubmit.setOnClickListener {
            if (binding.edAddDescription.text!!.isNotEmpty()) {
                Toast.makeText(requireActivity(), "Rating ($userRating/5.0) And \n Feedback ${binding.edAddDescription.text}", Toast.LENGTH_SHORT)
                    .show()
            } else {
                binding.edAddDescription.error = "can't be blank"
            }
        }
    }

    companion object {

    }
}
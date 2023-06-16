package com.devcode.tourifyapp.ui.detail.tabfragment.feedback

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.devcode.tourifyapp.data.remote.response.ReviewsResponse
import com.devcode.tourifyapp.databinding.FragmentCustomDialogBinding
import com.devcode.tourifyapp.utils.ViewModelFactoryForDummy


class CustomDialog : DialogFragment() {
    private var _binding: FragmentCustomDialogBinding? = null
    private val binding get() = _binding!!
    private var userName: String? = null
    private lateinit var factory: ViewModelFactoryForDummy
    private lateinit var feedbackViewModel: FeedbackViewModel


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
        setupViewModel()
        getUser()
    }

    private fun getUser() {
        feedbackViewModel.getUserPreferences().observe(requireActivity()) {
            userName = it.name
        }
    }

    private fun setupViewModel() {
        factory = ViewModelFactoryForDummy.getInstance(requireActivity())
        feedbackViewModel = ViewModelProvider(requireActivity(), factory)[FeedbackViewModel::class.java]
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
                feedbackViewModel.addReview(
                    ReviewsResponse(
                    1,
                    393,
                    userRating.toDouble(),
                        binding.edAddDescription.text.toString(),
                    "2021-08-01T00:00:00.000Z",
                    userName.toString(),
                    "https://i.pravatar.cc/150?img=1"
                )
                )
                dialog?.dismiss()
            } else {
                binding.edAddDescription.error = "can't be blank"
            }
        }
    }

    companion object {

    }
}
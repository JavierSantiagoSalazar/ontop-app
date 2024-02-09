package com.example.ontopapp.ui.password

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ontopapp.R
import com.example.ontopapp.databinding.FragmentPasswordBinding
import com.example.ontopapp.ui.common.diff
import com.example.ontopapp.ui.common.setVisibleOrGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordFragment : Fragment(R.layout.fragment_password) {

    private val viewModel: PasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPasswordBinding.bind(view).apply {
            btnGenerate.setOnClickListener {
                viewModel.onGeneratePasswordButtonPressed()
                tvCopied.setVisibleOrGone(false)
            }
            icCopy.setOnClickListener {
                viewModel.copyPassword(context)
                tvCopy.setVisibleOrGone(false)
                tvCopied.setVisibleOrGone(true)
            }
        }

        with(viewModel.state) {
            diff(this, { it.password }) { password ->
                binding.tvGeneratedPassword.text = password
                if (password.isNotEmpty()) {
                    binding.lyCopy.setVisibleOrGone(true)
                    binding.tvCopy.setVisibleOrGone(true)
                }
            }

        }
    }
}

package com.example.ontopapp.ui.phrase

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.ontopapp.R
import com.example.ontopapp.databinding.FragmentPhraseBinding
import com.example.ontopapp.ui.common.diff
import com.example.ontopapp.ui.common.hideKeyboard
import com.example.ontopapp.ui.common.launchAndCollect
import com.example.ontopapp.ui.common.showErrorSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhraseFragment : Fragment(R.layout.fragment_phrase) {

    private val viewModel: PhraseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentPhraseBinding.bind(view).apply {
            btnContinue.setOnClickListener {
                viewModel.onButtonPressed(etPhrase.text.toString())
                requireActivity().hideKeyboard()
            }
        }

        with(viewModel.state) {
            diff(this, { it.sentence }) { sentence ->
                binding.tvOriginalSentence.text = sentence
                binding.flOriginalSentence.visibility =
                    if (sentence.isNotBlank()) View.VISIBLE else View.INVISIBLE
                binding.flConvertedSentence.visibility =
                    if (sentence.isNotBlank()) View.VISIBLE else View.INVISIBLE
            }
            diff(this, { it.convertedSentence }) { convertSentence ->
                binding.tvConvertedSentence.text = convertSentence
            }
            launchAndCollect(this) {
                it.error?.let { error ->
                    view.showErrorSnackBar(error) {}
                }
            }
        }
    }
}

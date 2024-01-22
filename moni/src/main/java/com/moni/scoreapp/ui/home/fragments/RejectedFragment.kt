package com.moni.scoreapp.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.moni.scoreapp.databinding.FragmentRejectedBinding
import com.moni.scoreapp.ui.home.HomeActivity
import com.moni.scoreapp.ui.home.HomeViewModel

class RejectedFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel
    private var _binding: FragmentRejectedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRejectedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        binding.rejectedBtn.setOnClickListener {
            viewModel.clearRecordRq()
            (requireActivity() as HomeActivity).goToScorerFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
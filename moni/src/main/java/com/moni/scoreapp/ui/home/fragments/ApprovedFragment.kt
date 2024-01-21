package com.moni.scoreapp.ui.home.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.moni.scoreapp.R
import com.moni.scoreapp.databinding.FragmentApprovedBinding
import com.moni.scoreapp.databinding.FragmentScorerBinding
import com.moni.scoreapp.ui.home.HomeActivity

class ApprovedFragment : Fragment() {
    private var _binding: FragmentApprovedBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApprovedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.approvedBtn.setOnClickListener {
            (requireActivity() as HomeActivity).goToScorerFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
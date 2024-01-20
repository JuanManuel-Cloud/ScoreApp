package com.moni.scoreapp.ui.home.fragments.record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.moni.scoreapp.databinding.FragmentRecordBinding
import data.guimodels.RecordGuiModel

class RecordsFragment : Fragment() {
    private var _binding: FragmentRecordBinding? = null
    private val binding get() = _binding!!
    private val records = mutableListOf<RecordGuiModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recordsAdapter = RecordsAdapter(requireContext(), records)

        val rv = binding.recordsList
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = recordsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
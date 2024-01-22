package com.moni.scoreapp.ui.home.fragments.record

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.moni.scoreapp.R
import com.moni.scoreapp.data.local.daos.RecordItem
import com.moni.scoreapp.databinding.FragmentRecordBinding
import com.moni.scoreapp.data.local.guimodels.RecordGuiModel
import com.moni.scoreapp.ui.home.HomeViewModel
import com.moni.scoreapp.utils.Constants.MIN_QUERY_LEN
import com.moni.scoreapp.utils.Others
import com.moni.scoreapp.utils.ResStatus

class RecordsFragment : Fragment() {
    private lateinit var viewModel: HomeViewModel

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
        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]

        val recordsAdapter = RecordsAdapter(requireContext(), records, ::onDeleteListener)

        val rv = binding.recordsList
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = recordsAdapter

        viewModel.getRecordsFirebase()

        viewModel.records.observe(viewLifecycleOwner) {
            updateList(recordsAdapter, it)
        }

        viewModel.recordStatus.observe(viewLifecycleOwner) {
            val content = it.getContentIfNotHandled()
            binding.recordsLoading.visibility =
                if (content?.status == ResStatus.LOADING) VISIBLE else INVISIBLE

            if (content?.status == ResStatus.ERROR) Others.showErrorSnackbar(
                requireActivity().findViewById(R.id.fragment_record_layout),
                getString(R.string.server_error)
            )
        }

        binding.recordsSearchBar.editText?.doAfterTextChanged {
            val query = it.toString()

            if (query.isBlank()) {
                viewModel.findRecords(query, true)
                return@doAfterTextChanged
            }

            if (query.length < MIN_QUERY_LEN) return@doAfterTextChanged

            viewModel.findRecords(query)
        }
    }

    private fun updateList(
        recordsAdapter: RecordsAdapter,
        it: List<RecordItem>
    ) {
        val oldSize = recordsAdapter.itemCount
        val newSize = it.size

        recordsAdapter.records = it.map { RecordGuiModel(it) }

        when {
            oldSize < newSize -> {
                recordsAdapter.notifyItemRangeChanged(0, oldSize)
                recordsAdapter.notifyItemRangeInserted(oldSize, newSize - oldSize)
            }

            oldSize > newSize -> {
                recordsAdapter.notifyItemRangeChanged(0, newSize)
                recordsAdapter.notifyItemRangeRemoved(newSize, oldSize - newSize)
            }

            else -> recordsAdapter.notifyItemRangeChanged(0, newSize)
        }
    }

    private fun onDeleteListener(id: String) {
        viewModel.deleteRecordFirebase(id)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
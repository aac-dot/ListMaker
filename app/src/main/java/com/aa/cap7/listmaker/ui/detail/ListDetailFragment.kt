package com.aa.cap7.listmaker.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aa.cap7.listmaker.databinding.ListDetailFragmentBinding

class ListDetailFragment : Fragment() {

    companion object {
        fun newInstance() = ListDetailFragment()
    }

    private lateinit var viewModel: ListDetailViewModel
    private lateinit var binding : ListDetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // return inflater.inflate(R.layout.list_detail_fragment, container, false)
        binding = ListDetailFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // viewModel = ViewModelProvider(requireActivity()).get(ListDetailViewModel::class.java)
        val recyclerViewAdapter = ListItemsRecyclerViewAdapter(viewModel.list)
        binding.listItemsRecyclerview.adapter = recyclerViewAdapter
        binding.listItemsRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        viewModel.onTaskAdded = {
            recyclerViewAdapter.notifyDataSetChanged()
        }

    }

}
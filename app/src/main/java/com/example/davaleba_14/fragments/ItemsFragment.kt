package com.example.davaleba_14.fragments

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.davaleba_14.ItemsAdapter
import com.example.davaleba_14.R
import com.example.davaleba_14.dataClasses.ItemA
import com.example.davaleba_14.dataClasses.ItemB
import com.example.davaleba_14.databinding.FragmentItemsBinding
import com.example.davaleba_14.databinding.ItemABinding
import com.example.davaleba_14.databinding.ItemBBinding
import com.example.davaleba_14.viewModel.ItemsViewModel
import kotlinx.coroutines.launch

class ItemsFragment : BaseFragment<FragmentItemsBinding>(FragmentItemsBinding::inflate) {

    private val viewModel: ItemsViewModel by viewModels()

    private val itemsAdapter = ItemsAdapter()

    private val navController = findNavController()


    override fun setupUI() {

        setupRecyclerView()
        observeViewModelA()
        observeViewModelB()

    }

    private val itemABinding: ItemABinding = ItemABinding.inflate(layoutInflater)
    private val itemBBinding: ItemBBinding = ItemBBinding.inflate(layoutInflater)
    private val itemA: ItemA = ItemA(id = 1, name = "ItemA1", description = "Description1")
    private val itemB: ItemB = ItemB(id = 1, name = "ItemB1", description = "Description1")

    override fun setupListeners() {

        setupSwipeRefreshLayout()
        addItemB()
        addItemA()
        editItemA(itemABinding, itemA)
        editItemB(itemBBinding, itemB)

    }

    private fun setupRecyclerView() {
        binding.rvItemsHolder.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = itemsAdapter
        }
    }

    private fun observeViewModelA() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.itemsA.collect { itemsA ->
                itemsAdapter.submitList(itemsA)
            }
        }


    }

    private fun observeViewModelB() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.itemsB.collect { itemsB ->
                itemsAdapter.submitList(itemsB)
            }
        }
    }


    private fun setupSwipeRefreshLayout() {
        binding.srRecycler.setOnRefreshListener {

            binding.srRecycler.isRefreshing = false
        }
    }

    private fun addItemA() {
        binding.btnAddA.setOnClickListener {
            navController.navigate(R.id.action_itemsFragment_to_editFragment)
        }
    }

    private fun addItemB() {
        binding.btnAddB.setOnClickListener {
            navController.navigate(R.id.action_itemsFragment_to_editFragment)
        }
    }

    private fun editItemA(binding: ItemABinding, itemA: ItemA) {
        binding.btnEditA.setOnClickListener {
            val bundle = bundleOf(
                EditFragment.ARG_ITEM_NAME to itemA.name,
                EditFragment.ARG_ITEM_DESCRIPTION to itemA.description
            )
            navController.navigate(R.id.action_itemsFragment_to_editFragment, bundle)
        }
    }

    private fun editItemB(binding: ItemBBinding, itemB: ItemB) {
        binding.btnEditB.setOnClickListener {
            val bundle = bundleOf(
                EditFragment.ARG_ITEM_NAME to itemB.name,
                EditFragment.ARG_ITEM_DESCRIPTION to itemB.description
            )
            navController.navigate(R.id.action_itemsFragment_to_editFragment, bundle)
        }
    }

}


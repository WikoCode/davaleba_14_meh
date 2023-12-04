package com.example.davaleba_14.fragments

import com.example.davaleba_14.databinding.FragmentEditBinding

class EditFragment : BaseFragment<FragmentEditBinding>(FragmentEditBinding::inflate) {

    companion object {
        const val ARG_ITEM_NAME = "arg_item_name"
        const val ARG_ITEM_DESCRIPTION = "arg_item_description"
    }


    override fun setupUI() {
    }

    override fun setupListeners() {
    }

}
package com.example.davaleba_14.viewModel

import androidx.lifecycle.ViewModel
import com.example.davaleba_14.dataClasses.ItemA
import com.example.davaleba_14.dataClasses.ItemB
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ItemsViewModel : ViewModel() {


    private val _itemsA = MutableStateFlow<List<ItemA>>(emptyList())
    val itemsA: StateFlow<List<ItemA>> = _itemsA

    private val _itemsB = MutableStateFlow<List<ItemB>>(emptyList())
    val itemsB: StateFlow<List<ItemB>> = _itemsB


    fun addItemA(item: ItemA) {
        _itemsA.value = _itemsA.value + item
    }

    fun updateItemA(updatedItem: ItemA) {
        _itemsA.value = _itemsA.value.map { if (it.id == updatedItem.id) updatedItem else it }
    }

    fun deleteItemA(itemId: Int) {
        _itemsA.value = _itemsA.value.filter { it.id != itemId }
    }

    fun addItemB(item: ItemB) {
        _itemsB.value = _itemsB.value + item
    }

    fun updateItemB(updatedItem: ItemB) {
        _itemsB.value = _itemsB.value.map { if (it.id == updatedItem.id) updatedItem else it }
    }

    fun deleteItemB(itemId: Int) {
        _itemsB.value = _itemsB.value.filter { it.id != itemId }
    }


    init {
        _itemsA.value = listOf(
            ItemA(id = 1, name = "ItemA1", description = "Description1"),
        )

        _itemsB.value = listOf(
            ItemB(id = 1, name = "ItemB1", description = "Description1"),
            ItemB(id = 2, name = "ItemB2", description = "Description2"),
            ItemB(id = 3, name = "ItemB3", description = "Description2"),
            )
    }




}
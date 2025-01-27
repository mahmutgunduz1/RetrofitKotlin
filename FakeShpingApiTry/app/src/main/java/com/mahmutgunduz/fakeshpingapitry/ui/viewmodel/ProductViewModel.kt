package com.mahmutgunduz.fakeshpingapitry.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmutgunduz.fakeshpingapitry.data.model.Product
import com.mahmutgunduz.fakeshpingapitry.data.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products
    
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    fun fetchProducts() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                _products.value = repository.getProducts()
            } catch (e: Exception) {
                _error.value = e.message ?: "An error occurred"
            } finally {
                _isLoading.value = false
            }
        }
    }
} 
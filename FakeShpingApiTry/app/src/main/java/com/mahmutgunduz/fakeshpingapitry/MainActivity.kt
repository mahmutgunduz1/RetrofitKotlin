package com.mahmutgunduz.fakeshpingapitry

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

import com.mahmutgunduz.fakeshpingapitry.ui.adapter.ProductAdapter
import com.mahmutgunduz.fakeshpingapitry.ui.viewmodel.ProductViewModel
import com.google.android.material.snackbar.Snackbar
import com.mahmutgunduz.fakeshpingapitry.databinding.ActivityMainBinding
import com.mahmutgunduz.fakeshpingapitry.ui.ProductDetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductViewModel by viewModels()
    private val adapter = ProductAdapter { product ->
        startActivity(ProductDetailActivity.newIntent(this, product))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupObservers()
        
        viewModel.fetchProducts()
    }

    private fun setupRecyclerView() {
        binding.rvProducts.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.products.observe(this) { products ->
            adapter.submitList(products)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
                    .setAction("Retry") {
                        viewModel.fetchProducts()
                    }
                    .show()
            }
        }
    }
}
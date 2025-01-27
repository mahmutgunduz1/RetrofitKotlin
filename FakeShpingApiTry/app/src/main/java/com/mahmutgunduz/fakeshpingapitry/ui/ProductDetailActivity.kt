package com.mahmutgunduz.fakeshpingapitry.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.mahmutgunduz.fakeshpingapitry.R
import com.mahmutgunduz.fakeshpingapitry.data.model.Product
import com.mahmutgunduz.fakeshpingapitry.databinding.ActivityProductDetailBinding

class ProductDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val product = intent.getParcelableExtra<Product>(EXTRA_PRODUCT)
        product?.let { displayProduct(it) }
    }

    private fun displayProduct(product: Product) {
        binding.apply {
            tvTitleDetail.text = product.title
            tvPriceDetail.text = "$ ${product.price}"
            tvRatingDetail.text = "${product.rating.rate} ⭐ (${product.rating.count} reviews)"
            tvCategory.text = product.category
            tvDescription.text = product.description

            Glide.with(this@ProductDetailActivity)
                .load(product.image)
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error_image)
                .into(ivProductDetail)
        }
    }

    companion object {
        private const val EXTRA_PRODUCT = "extra_product"

        fun newIntent(context: Context, product: Product): Intent {
            return Intent(context, ProductDetailActivity::class.java).apply {
                putExtra(EXTRA_PRODUCT, product)
            }
        }
    }
} 
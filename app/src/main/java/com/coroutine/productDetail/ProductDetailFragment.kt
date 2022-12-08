package com.coroutine.productDetail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.coroutine.R
import com.coroutine.adapter.BannerOfProductAdapter
import com.coroutine.adapter.ProductAdapter
import com.coroutine.databinding.FragmentProductDetailsBinding
import com.coroutine.interfaces.ProductDetail
import com.coroutine.product.ProductViewModel
import com.coroutine.product.ProductViewModelFactory
import com.coroutine.model.ProductModel
import com.coroutine.network.local.ProductDatabase
import com.coroutine.repository.ProductRepository
import com.google.android.material.snackbar.Snackbar

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailsBinding
    private lateinit var viewModel: ProductViewModel
    private lateinit var productRepository: ProductRepository
    private lateinit var productViewModelFactory: ProductViewModelFactory
    private lateinit var product: ProductModel
    private lateinit var bannerOfProductAdapter: BannerOfProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews()
        initRepoAndFactory()
        initViewModel()
        initAdapter()
        clickedView()

    }

    @SuppressLint("SetTextI18n")
    private fun initViews() {
        product = requireArguments().getSerializable("product") as ProductModel

        Glide
            .with(requireContext())
            .load(product.image)
            .placeholder(R.drawable.ic_launcher_foreground)
            .fitCenter()
            .into(binding.imgProductDetail)
        binding.txtTitleProductDetail.text = product.title
        binding.txtDescriptionProductDetail.text = product.description
        binding.txtPriceProductDetail.text = "${product.price} $"
        binding.rateProductDetail.rating = product.rating!!
        binding.txtRateProductDetail.text = product.rating.toString()
    }

    private fun initRepoAndFactory() {
        productRepository = ProductRepository(ProductDatabase(requireContext()))
        productViewModelFactory =
            ProductViewModelFactory(requireActivity().application, productRepository)
    }

    private fun initAdapter() {
        bannerOfProductAdapter = BannerOfProductAdapter(requireView(), product.images!!.toList())
        binding.imgBannerProductDetail.sliderAdapter = bannerOfProductAdapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, productViewModelFactory)[ProductViewModel::class.java]
    }

    private fun clickedView() {
        binding.fabFavorite.setOnClickListener {
            viewModel.insertProduct(product)
            Snackbar.make(it, "Product saved successfully", Snackbar.LENGTH_SHORT).show()
        }
    }
}
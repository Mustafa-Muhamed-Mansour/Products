package com.coroutine.product

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.coroutine.R
import com.coroutine.adapter.ProductAdapter
import com.coroutine.interfaces.ProductDetail
import com.coroutine.model.ProductModel
import com.coroutine.network.local.ProductDatabase
import com.coroutine.repository.ProductRepository
import com.coroutine.common.ProductState
import com.coroutine.databinding.FragmentProductBinding
import com.coroutine.network.remote.ProductService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class ProductFragment : Fragment(), ProductDetail {

    private lateinit var binding: FragmentProductBinding
    private lateinit var productRepository: ProductRepository
    private lateinit var productViewModelFactory: ProductViewModelFactory
    private lateinit var viewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRepoAndFactory()
        initViewModel()
        initAdapter()
        retrieveViewModel()

    }

    private fun initRepoAndFactory() {

//        val productService = ProductService::class.java
        productRepository = ProductRepository(ProductDatabase(requireContext()))
        productViewModelFactory =
            ProductViewModelFactory(requireActivity().application, productRepository)
    }


    private fun initViewModel() {
        viewModel = ViewModelProvider(this, productViewModelFactory)[ProductViewModel::class.java]
    }

    private fun initAdapter() {
        productAdapter = ProductAdapter(this@ProductFragment)
    }

    private fun retrieveViewModel() {
        binding.loadingProduct.visibility = View.VISIBLE
        retrieveProduct()
    }

    private fun retrieveProduct() {
        lifecycleScope.launchWhenStarted {
            viewModel.productMutableLiveData.observe(viewLifecycleOwner) {

                binding.loadingProduct.visibility = View.GONE

                when (it) {
                    is ProductState.Success -> {
                        binding.loadingProduct.visibility = View.INVISIBLE
                        it.data?.apply {

                            productAdapter.differ.submitList(this.productModel.toList())

                            binding.rVProduct.apply {
                                this.adapter = productAdapter
                                this.layoutManager = LinearLayoutManager(
                                    requireContext(),
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                                this.addItemDecoration(
                                    DividerItemDecoration(
                                        requireContext(),
                                        DividerItemDecoration.VERTICAL
                                    )
                                )
                            }
                        }
                    }

                    is ProductState.Error -> {
                        binding.loadingProduct.visibility = View.INVISIBLE
                        Toast.makeText(
                            requireContext(),
                            "An error eccurred: ${it.message}",
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                    is ProductState.Loading -> {
                        binding.loadingProduct.visibility = View.VISIBLE
                    }
                }
            }
        }
    }


    override fun clickedOfProduct(productModel: ProductModel) {
        val bundle = Bundle().apply {
            this.putSerializable("product", productModel)
        }
        findNavController().navigate(R.id.action_homeFragment_to_productDetailFragment, bundle)
    }

}
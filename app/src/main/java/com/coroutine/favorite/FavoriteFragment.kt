package com.coroutine.favorite

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
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.coroutine.R
import com.coroutine.adapter.ProductAdapter
import com.coroutine.databinding.FragmentFavoriteBinding
import com.coroutine.interfaces.ProductDetail
import com.coroutine.model.ProductModel
import com.coroutine.network.local.ProductDatabase
import com.coroutine.repository.ProductRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment(), ProductDetail {


    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var productRepository: ProductRepository
    private lateinit var favoriteViewModelFactory: FavoriteViewModelFactory
    private lateinit var productAdapter: ProductAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initRepoAndFactory()
        swipeToDelete()
        initViewModel()
        initAdapter()
        retrieveViewModel()

    }

    private fun initRepoAndFactory() {
        productRepository = ProductRepository(ProductDatabase(requireContext()))
        favoriteViewModelFactory = FavoriteViewModelFactory(productRepository)
    }

    private fun swipeToDelete() {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val product = productAdapter.differ.currentList[position]
                viewModel.deleteProduct(product)
                Snackbar.make(requireView(), "Successfully deleted product", Snackbar.LENGTH_SHORT)
                    .show()
//                    .apply {
//                        setAction("Undo Product") {
//                          viewModel.insertProduct(product)
//                        }.show()
//                    }
            }
        }).attachToRecyclerView(binding.rVFavorite)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, favoriteViewModelFactory)[FavoriteViewModel::class.java]
    }

    private fun initAdapter() {
        productAdapter = ProductAdapter(this@FavoriteFragment)
    }

    private fun retrieveViewModel() {

        lifecycleScope.launchWhenStarted {

            viewModel.getAllProduct().observe(viewLifecycleOwner) {
                productAdapter.differ.submitList(it)
                binding.rVFavorite.apply {
                    this.adapter = productAdapter
                    this.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                    this.addItemDecoration(
                        DividerItemDecoration(
                            requireContext(),
                            DividerItemDecoration.VERTICAL
                        )
                    )
                }
            }
        }
    }

    override fun clickedOfProduct(productModel: ProductModel) {
        val bundle = Bundle().apply {
            this.putSerializable("product", productModel)
        }
        findNavController().navigate(R.id.action_favoriteFragment_to_productDetailFragment, bundle)
    }

}
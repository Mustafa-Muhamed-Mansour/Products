package com.coroutine.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RatingBar
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coroutine.databinding.ItemProductBinding
import com.coroutine.interfaces.ProductDetail
import com.coroutine.model.ProductModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class ProductAdapter(private var productDetail: ProductDetail) :
    RecyclerView.Adapter<ProductAdapter.PostViewHolder>() {


    private val differCallBack = object : DiffUtil.ItemCallback<ProductModel>() {
        override fun areItemsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem.image == newItem.image && oldItem.title == newItem.title && oldItem.description == newItem.description && oldItem.price.toString() == newItem.price.toString() && oldItem.rating.toString() == newItem.rating.toString() && oldItem.discountPercentage.toString() == newItem.discountPercentage.toString()
        }

        override fun areContentsTheSame(oldItem: ProductModel, newItem: ProductModel): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            ItemProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val model = differ.currentList[position]

        holder.bind.apply {

            Glide
                .with(holder.itemView.context.applicationContext)
                .load(model.image)
                .into(this.imgItemProduct)
            this.txtTitleItemProduct.text = model.title
            this.txtPriceItemProduct.text = "${model.price} $"
            this.rateItemProduct.rating = model.rating!!
            this.txtDiscountItemProduct.text = "${model.discountPercentage} %"

        }

        holder.itemView.setOnClickListener {

            productDetail.clickedOfProduct(model)

        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class PostViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        val bind = binding
    }
}
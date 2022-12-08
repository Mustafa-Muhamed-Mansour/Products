package com.coroutine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.coroutine.R
import com.coroutine.databinding.ItemBannerProductBinding
import com.coroutine.model.ProductModel
import com.smarteist.autoimageslider.SliderViewAdapter
import javax.inject.Inject

class BannerOfProductAdapter(var view: View, var images: List<String>) :
    SliderViewAdapter<BannerOfProductAdapter.BannerOfProductViewHolder>() {


    override fun getCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup?): BannerOfProductViewHolder {
        return BannerOfProductViewHolder(
            ItemBannerProductBinding.inflate(
                LayoutInflater.from(parent!!.context.applicationContext),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: BannerOfProductViewHolder?, position: Int) {
        val model = images[position]
        viewHolder!!.bind.apply {
            Glide
                .with(view.context.applicationContext)
                .load(model)
                .placeholder(R.drawable.ic_launcher_foreground)
                .fitCenter()
                .into(this.imgItemProductBanner)

        }
    }

    inner class BannerOfProductViewHolder(binding: ItemBannerProductBinding) :
        SliderViewAdapter.ViewHolder(binding.root) {
        val bind = binding
    }

}
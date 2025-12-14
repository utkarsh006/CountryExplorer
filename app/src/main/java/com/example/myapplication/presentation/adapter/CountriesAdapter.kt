package com.example.myapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemLayoutBinding
import com.example.myapplication.presentation.model.CountryUiModel
import com.example.myapplication.di.ImageLoader
import javax.inject.Inject

class CountriesAdapter @Inject constructor(
    private val imageLoader: ImageLoader
) : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<CountryUiModel>() {
        override fun areItemsTheSame(oldItem: CountryUiModel, newItem: CountryUiModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CountryUiModel, newItem: CountryUiModel): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitList(countries: List<CountryUiModel>) {
        differ.submitList(countries)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class CountryViewHolder(
        private val binding: ItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(country: CountryUiModel) {
            binding.apply {
                tvTitle.text = country.title
                tvSource.text = country.subtitle
                tvDescription.text = country.description
                tvPublishedAt.text = country.details

                imageLoader.loadImage(ivArticleImage, country.imageUrl)

                root.setOnClickListener {
                    onItemClickListener?.invoke(country)
                }
            }
        }
    }

    private var onItemClickListener: ((CountryUiModel) -> Unit)? = null

    fun setOnItemClickListener(listener: (CountryUiModel) -> Unit) {
        onItemClickListener = listener
    }
}
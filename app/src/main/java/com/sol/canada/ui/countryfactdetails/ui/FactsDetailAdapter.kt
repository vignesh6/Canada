package com.sol.canada.ui.countryfactdetails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sol.canada.databinding.ItemFactDetailBinding
import com.sol.canada.ui.countryfactdetails.data.FactDetail

class FactsDetailAdapter :
    ListAdapter<FactDetail, FactDetailViewHolder>(FactDetailDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactDetailViewHolder {
        return FactDetailViewHolder(
            ItemFactDetailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: FactDetailViewHolder, position: Int) {
        val factDetail = getItem(position)
        holder.apply {
            //Binding Fact detail to view holder
            bind(factDetail)
        }
    }

}

private class FactDetailDiffCallback : DiffUtil.ItemCallback<FactDetail>() {

    override fun areItemsTheSame(
        oldItem: FactDetail, newItem: FactDetail
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FactDetail, newItem: FactDetail): Boolean {
        return oldItem == newItem
    }
}

class FactDetailViewHolder(private val binding: ItemFactDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FactDetail) {
        binding.apply {
            factDetail = item
            executePendingBindings()
        }
    }
}

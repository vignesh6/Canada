package com.sol.canada.ui.countryfactdetails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sol.canada.databinding.ItemFactDetailBinding
import com.sol.canada.ui.countryfactdetails.data.FactDetail

/**
 * Adapter responsible for updating the recyclerview with facts
 */
class FactsDetailAdapter :
    ListAdapter<FactDetail, FactDetailViewHolder>(FactDetailDiffCallback()) {

    /**
     * Creates ViewHolder associated with the FactsDetailAdapter and return it.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactDetailViewHolder {
        return FactDetailViewHolder(
            ItemFactDetailBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    /**
     * Bind fact item to the FactsViewHolder
     */
    override fun onBindViewHolder(holder: FactDetailViewHolder, position: Int) {
        val factDetail = getItem(position)
        holder.apply {
            //Binding Fact detail to view holder
            bind(factDetail)
        }
    }

}

/**
 * Calculates the difference between two lists
 */
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

/**
 * ViewHolder that is bind to FactsDetailAdapter
 * Updates individual item UI
 */
class FactDetailViewHolder(private val binding: ItemFactDetailBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: FactDetail) {
        binding.apply {
            factDetail = item
            executePendingBindings()
        }
    }
}

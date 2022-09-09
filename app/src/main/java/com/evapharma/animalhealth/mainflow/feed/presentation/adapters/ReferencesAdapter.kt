package com.evapharma.animalhealth.mainflow.feed.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evapharma.animalhealth.databinding.RefItemBinding

class ReferencesAdapter(val list: ArrayList<String>?) : RecyclerView.Adapter<ReferencesAdapter.RefViewHolder>() {

    class RefViewHolder(val binding: RefItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RefViewHolder {
        return RefViewHolder(RefItemBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun onBindViewHolder(holder: RefViewHolder, position: Int) {
        holder.binding.refTv.text = list?.get(holder.adapterPosition) ?: ""
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}
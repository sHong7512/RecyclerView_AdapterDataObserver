package com.shong.recyclerview_adapterdataobserver

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shong.recyclerview_adapterdataobserver.databinding.ItemContentBinding

class ContentAdapter @JvmOverloads constructor(private var contentList: MutableList<String> = mutableListOf()) :
    RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemContentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.contentTextView.text = contentList[position]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemContentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = contentList.size

    internal fun addList(content: String) {
        this.contentList.add(content)
//        notifyDataSetChanged()
        notifyItemInserted(contentList.size - 1)
    }

}
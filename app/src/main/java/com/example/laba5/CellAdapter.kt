package com.example.laba5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CellAdapter(
    private val items: List<CellItem>,
    private val onClick: (CellItem) -> Unit
) : RecyclerView.Adapter<CellAdapter.CellViewHolder>() {

    inner class CellViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textValue: TextView = view.findViewById(R.id.textValue)
        private val container: FrameLayout = view as FrameLayout

        fun bind(item: CellItem) {
            textValue.text = item.value.toString()
            container.setBackgroundColor(item.color)

            container.setOnClickListener {
                onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cell, parent, false)
        return CellViewHolder(view)
    }

    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size
}

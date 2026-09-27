package com.example.uu_marketplace

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(
    private val items: List<Product>,
    private val onClick: ((Product) -> Unit)? = null
) : RecyclerView.Adapter<ProductAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val card: CardView = v.findViewById(R.id.cardProduct)
        val img: ImageView = v.findViewById(R.id.imgProduct)
        val tvTitle: TextView = v.findViewById(R.id.tvTitle)
        val tvPrice: TextView = v.findViewById(R.id.tvPrice)
        val tvDesc: TextView = v.findViewById(R.id.tvDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = items[position]
        holder.img.setImageResource(p.imageRes)
        holder.tvTitle.text = p.title
        holder.tvPrice.text = p.price
        holder.tvDesc.text = p.description
        holder.card.setOnClickListener { onClick?.invoke(p) }
    }

    override fun getItemCount() = items.size
}
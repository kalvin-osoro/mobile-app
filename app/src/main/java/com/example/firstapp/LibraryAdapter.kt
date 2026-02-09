package com.example.firstapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firstapp.data.LibraryItem
import com.example.firstapp.databinding.ItemLibraryBinding
import com.google.android.material.shape.ShapeAppearanceModel

class LibraryAdapter(private val items: List<LibraryItem>) : RecyclerView.Adapter<LibraryAdapter.LibraryViewHolder>() {

    inner class LibraryViewHolder(val binding: ItemLibraryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibraryViewHolder {
        val binding = ItemLibraryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LibraryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LibraryViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            tvTitle.text = item.title
            tvSubtitle.text = item.subtitle

            // For demo purposes, we'll use local images if the URL matches certain patterns or just cycle through them
            val resId = when(position % 4) {
                0 -> R.drawable.img1
                1 -> R.drawable.img2
                2 -> R.drawable.img3
                else -> R.drawable.img4
            }
            ivItemImage.setImageResource(resId)

            if (item.isArtist) {
                ivItemImage.shapeAppearanceModel = ivItemImage.shapeAppearanceModel.toBuilder()
                    .setAllCornerSizes { it.height() / 2f }
                    .build()
            } else {
                ivItemImage.shapeAppearanceModel = ShapeAppearanceModel.builder()
                    .setAllCorners(com.google.android.material.shape.CornerFamily.ROUNDED, 8f * ivItemImage.context.resources.displayMetrics.density)
                    .build()
            }
        }
    }

    override fun getItemCount() = items.size
}

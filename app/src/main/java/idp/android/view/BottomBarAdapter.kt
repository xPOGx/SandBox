package idp.android.view

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sandbox.R
import com.example.sandbox.databinding.BottomBarItemBinding
import idp.ext.Item

class BottomBarAdapter(
    private val onItemClicked: (Item) -> Item?,
) : ListAdapter<Item, BottomBarAdapter.BottomBarViewHolder>(DiffCallback) {
    var lastSelected: Item? = null
    private var lastTextView: View? = null
    private var lastImageView: View? = null

    class BottomBarViewHolder(private var binding: BottomBarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item) {
            binding.apply {
                ivItemImage.setImageResource(item.iconRes)
                tvItemText.text = item.text
                ivItemDot.isVisible = item.priority
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): BottomBarViewHolder {
        return BottomBarViewHolder(
            BottomBarItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(
        holder: BottomBarViewHolder,
        position: Int,
    ) {
        val item = getItem(position)

        holder.bind(item)

        with(holder.itemView) {
            configureUi(this, item)

            setOnClickListener {
                val currentItem = onItemClicked(item)
                lastSelected = currentItem
                configureUi(this, currentItem)
            }
        }
    }

    private fun configureUi(view: View, item: Item?) {
        with(view) {
            val textView = findViewById<TextView>(R.id.tv_item_text)
            val imageView = findViewById<ImageView>(R.id.iv_item_image)
            findViewById<ImageView>(R.id.iv_item_dot).isVisible = item?.priority == true

            if (lastSelected == item) {
                // return last to default visual
                (lastTextView as? TextView)?.setTextAppearance(R.style.RobotoRegular)
                (lastImageView as? ImageView)?.drawable?.setColorFilter(
                    resources.getColor(R.color.grey300),
                    PorterDuff.Mode.SRC_ATOP
                )

                // update current item visual
                textView.setTextAppearance(R.style.RobotoMedium)
                imageView.drawable?.setColorFilter(
                    resources.getColor(R.color.blue),
                    PorterDuff.Mode.SRC_ATOP
                )

                // update last states
                lastTextView = textView
                lastImageView = imageView
            } else {
                textView.setTextAppearance(R.style.RobotoRegular)
                imageView.drawable?.setColorFilter(
                    resources.getColor(R.color.grey300),
                    PorterDuff.Mode.SRC_ATOP
                )
            }
        }
    }

    companion object {
        private val DiffCallback =
            object : DiffUtil.ItemCallback<Item>() {
                override fun areItemsTheSame(
                    oldItem: Item,
                    newItem: Item,
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: Item,
                    newItem: Item,
                ): Boolean {
                    return oldItem.iconRes == newItem.iconRes
                            && oldItem.text == newItem.text
                            && oldItem.priority == newItem.priority
                }
            }
    }
}

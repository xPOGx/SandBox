package idp.android.recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.sandbox.R
import com.example.sandbox.databinding.MusicItemBinding
import idp.ext.Music
import idp.ext.Theme

class MusicAdapter(
    private val onItemClick: (Music) -> Unit,
) : ListAdapter<Music, MusicAdapter.MusicAdapterViewHolder>(DiffCallback) {
    private var lastView: View? by mutableStateOf(null)

    class MusicAdapterViewHolder(
        private val binding: MusicItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Music) {
            with(binding) {
                tvMusicName.text = item.name
                tvMusicArtist.text = item.artist
                ivMusicImage.setImageResource(item.imageRes)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MusicAdapterViewHolder {
        return MusicAdapterViewHolder(
            MusicItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            ),
        )
    }

    override fun onBindViewHolder(
        holder: MusicAdapterViewHolder,
        position: Int,
    ) {
        val item = getItem(position)

        with(holder) {
            itemView.setOnClickListener {
                onItemClick(item)

                if (lastView != itemView) {
                    disableView(lastView)

                    enableView(itemView)

                    lastView = itemView
                } else {
                    disableView(itemView)

                    lastView = null
                }
            }

            bind(item)

            checkRecomposition(item, itemView)
        }
    }

    private fun checkRecomposition(item: Music?, currentView: View) {
        item ?: return
        // recomposition check
        val lastText = lastView?.findViewById<TextView>(R.id.tv_music_name)?.text
        if (lastText == item.name) {
            currentView.apply {
                findViewById<ImageView>(R.id.iv_music_play)?.isVisible = true
                findViewById<TextView>(R.id.tv_music_name)?.setTextColor(Theme.colors.blue.toArgb())
            }
            lastView = currentView
        }
    }

    private fun disableView(view: View?) {
        view?.apply {
            findViewById<ImageView>(R.id.iv_music_play)?.isVisible = false
            findViewById<TextView>(R.id.tv_music_name)?.setTextColor(Theme.colors.black.toArgb())
        }
    }

    private fun enableView(view: View?) {
        view?.apply {
            findViewById<ImageView>(R.id.iv_music_play)?.isVisible = true
            findViewById<TextView>(R.id.tv_music_name)?.setTextColor(Theme.colors.blue.toArgb())
        }
    }

    fun clear() {
        lastView?.apply {
            findViewById<ImageView>(R.id.iv_music_play)?.isVisible = false
            findViewById<TextView>(R.id.tv_music_name)?.setTextColor(Theme.colors.black.toArgb())
        }
        lastView = null
    }

    companion object {
        private val DiffCallback =
            object : DiffUtil.ItemCallback<Music>() {
                override fun areItemsTheSame(
                    oldItem: Music,
                    newItem: Music,
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: Music,
                    newItem: Music,
                ): Boolean {
                    return oldItem.name == newItem.name
                            && oldItem.artist == newItem.artist
                            && oldItem.imageRes == newItem.imageRes
                }
            }
    }
}

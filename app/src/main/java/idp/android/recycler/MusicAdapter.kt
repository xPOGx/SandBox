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
import idp.ext.formattedTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MusicAdapter(
    private val coroutineScope: CoroutineScope,
    private val onItemClick: (Music, () -> Unit) -> Unit,
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
                tvMaxTime.text = formattedTime(item.maxTime)
            }
        }

        fun bindTime(time: Int) {
            with(binding) {
                tvCurrentTime.text = formattedTime(time)
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
                onItemClick(item) { clear() }

                if (lastView != itemView) {
                    disableView(lastView)

                    enableView(itemView)

                    lastView = itemView
                } else {
                    disableView(itemView)

                    lastView = null
                }
            }

            coroutineScope.launch {
                while (isActive) {
                    when (item.player?.isPlaying) {
                        true -> {
                            val current = item.player?.currentPosition ?: 0
                            bindTime(current)
                        }
                        else -> { /* ignore action */ }
                    }
                    delay(100)
                }
            }

            bind(item)
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

    private fun clear() {
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

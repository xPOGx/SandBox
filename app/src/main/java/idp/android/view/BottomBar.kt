package idp.android.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.content.res.use
import com.example.sandbox.R
import com.example.sandbox.databinding.BottomBarBinding
import idp.ext.Item

class BottomBar : LinearLayout {
    private var _binding: BottomBarBinding? = null
    val binding: BottomBarBinding
        get() = _binding!!

    var items: MutableList<Item>? = null
        set(value) {
            field = value

            bbAdapter.submitList(items)

            if (!value.isNullOrEmpty()) {
                val firstValue = value.first()
                selectedItem = firstValue
                bbAdapter.lastSelected = firstValue
            }
        }

    private fun updateAdapter() {
        bbAdapter.submitList(items)
        bbAdapter.notifyDataSetChanged()
    }

    var selectedItem: Item? = null

    var onChange: (() -> Unit)? = null

    private val bbAdapter =
        BottomBarAdapter { item ->
            items?.let { all ->
                if (item.priority) {
                    val currentIndex = all.indexOf(item)
                    if (currentIndex != -1) {
                        val updateItem = all[currentIndex].copy(priority = false)
                        items?.set(currentIndex, updateItem)
                        updateAdapter()
                        selectedItem = updateItem
                    } else {
                        selectedItem = item
                    }
                } else {
                    selectedItem = item
                }
            }
            onChange?.invoke()
            selectedItem
        }

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet? = null) {
        _binding = BottomBarBinding.inflate(LayoutInflater.from(context), this)

        with(binding.rvBbItems) {
            adapter = bbAdapter
            layoutManager = JustifiedLayoutManager(context)
        }

        attrs?.let { initAttr(attrs) }
    }

    private fun initAttr(attrs: AttributeSet) {
        context.obtainStyledAttributes(attrs, R.styleable.BottomBar).use {
            // TODO style maybe
        }
    }
}
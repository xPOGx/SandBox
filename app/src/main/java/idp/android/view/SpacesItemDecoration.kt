package idp.android.view

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SpacesItemDecoration(private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        when(parent.getChildAdapterPosition(view)) {
            0 -> {
                outRect.left = spacing / 2
                outRect.right = spacing / 4
            }

            parent.adapter!!.itemCount - 1 -> {
                outRect.left = spacing / 4
                outRect.right = spacing / 2
            }

            else -> {
                outRect.left = spacing / 4
                outRect.right = spacing / 4
            }
        }
    }
}
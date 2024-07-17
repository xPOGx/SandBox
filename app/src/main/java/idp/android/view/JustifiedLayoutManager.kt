package idp.android.view

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class JustifiedLayoutManager(
    context: Context,
): LinearLayoutManager(context, HORIZONTAL, false)  {
    override fun canScrollHorizontally(): Boolean = false
    override fun canScrollVertically(): Boolean = false

    override fun onLayoutChildren(
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ) {
        super.onLayoutChildren(recycler, state)

        val itemCount = childCount
        var totalSpaceForItems = width

        var totalContentWidth = 0
        for (i in 0 until itemCount) {
            val view = getChildAt(i)
            if (view != null) {
                totalContentWidth += getDecoratedMeasuredWidth(view)
            }
        }

        if (totalContentWidth < width) {
            totalSpaceForItems = width - totalContentWidth
        }

        val spacePerItem = totalSpaceForItems / (itemCount - 1)

        var left = paddingStart
        for (i in 0 until itemCount) {
            val view = getChildAt(i)
            if (view != null) {
                val measuredWidth = getDecoratedMeasuredWidth(view)
                val measuredHeight = getDecoratedMeasuredHeight(view)

                // Layout the view based on calculated position and space
                layoutDecorated(
                    view,
                    left,
                    paddingTop,
                    left + measuredWidth,
                    paddingTop + measuredHeight
                )

                // Update left position for next item
                left += measuredWidth + spacePerItem
            }
        }
    }
}

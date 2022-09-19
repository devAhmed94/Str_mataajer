package com.app.mataajer.application.utils

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.app.mataajer.application.utils.LocaleHelper.locale

/**
 * Ahmed Ali Ebaid
 * ahmedali26002844@gmail.com
 * 07/09/2022
 */
class GridSpacingItemDecoration(private val spanCount: Int, private val spacing: Int,
                                private val includeEdge: Boolean) : ItemDecoration() {

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
                              state: RecyclerView.State) {
    val position = parent.getChildAdapterPosition(view)
    val column = position % spanCount

    if (includeEdge) {
      when (locale) {
        "ar" -> {
          outRect.right = spacing - column * spacing / spanCount
          outRect.left = (column + 1) * spacing / spanCount
        }
        "en" -> {
          outRect.left = spacing - column * spacing / spanCount
          outRect.right = (column + 1) * spacing / spanCount
        }
      }

      if (position < spanCount) {
        outRect.top = spacing
      }
      outRect.bottom = spacing
    } else {
      when (locale) {
        "ar" -> {
          outRect.right = column * spacing / spanCount
          outRect.left = spacing - (column + 1) * spacing / spanCount
        }
        "en" -> {
          outRect.left = column * spacing / spanCount
          outRect.right = spacing - (column + 1) * spacing / spanCount
        }
      }

      if (position >= spanCount) {
        outRect.top = spacing
      }
    }
  }
}
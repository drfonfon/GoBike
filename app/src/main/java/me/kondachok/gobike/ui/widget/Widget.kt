package me.kondachok.gobike.ui.widget

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed interface Widget : Parcelable {
    var position: Int
    var tag: String
}

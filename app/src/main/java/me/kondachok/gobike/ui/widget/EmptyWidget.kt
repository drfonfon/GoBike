package me.kondachok.gobike.ui.widget

import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.parcelize.Parcelize
import me.kondachok.gobike.ui.theme.Dimen

@Parcelize
class EmptyWidget(
    override var position: Int,
    override var tag: String = TAG_EMPTY
) : Widget, Parcelable

@Composable
fun EmptyWidgetContent(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(Dimen.dp8)
            )
    )
}

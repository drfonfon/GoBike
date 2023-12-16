package me.kondachok.gobike.ui.widget

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.parcelize.Parcelize
import me.kondachok.gobike.R
import me.kondachok.gobike.domain.providers.ValueProvider

@Parcelize
class SpeedWidget(
    override var position: Int,
    override var tag: String = TAG_SPEED
) : Widget

@Composable
fun SpeedWidgetContent(modifier: Modifier, valueProvider: ValueProvider) {
    DefaultWidget(
        modifier = modifier,
        imageVector = Icons.Outlined.Speed,
        description = stringResource(id = R.string.value_speed),
        valueProvider
    )
}

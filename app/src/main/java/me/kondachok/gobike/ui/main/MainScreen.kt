@file:OptIn(ExperimentalMaterial3Api::class)

package me.kondachok.gobike.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells.Fixed
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import kotlinx.parcelize.Parcelize
import me.kondachok.gobike.domain.providers.EmptyValueProvider
import me.kondachok.gobike.domain.providers.SpeedProvider
import me.kondachok.gobike.domain.providers.ValueProvider
import me.kondachok.gobike.location.LocationProvider
import me.kondachok.gobike.ui.widget.EmptyWidget
import me.kondachok.gobike.ui.widget.EmptyWidgetContent
import me.kondachok.gobike.ui.widget.SpeedWidget
import me.kondachok.gobike.ui.widget.SpeedWidgetContent
import me.kondachok.gobike.ui.widget.TAG_EMPTY
import me.kondachok.gobike.ui.widget.TAG_SPEED
import me.kondachok.gobike.ui.widget.Widget
import me.kondachok.gobike.utils.LightDarkFullPreview
import org.koin.compose.koinInject

@Parcelize
class MainScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content() {
        MainScreenContent()
    }

}

@Composable
fun MainScreenContent(locationProvider: LocationProvider = koinInject()) {
    val stack = LocalContainerScreen.current as StackScreen
    val coroutineScope = rememberCoroutineScope()
    MainGreed(
        modifier = Modifier,
        listOf(
            SpeedWidget(0),
            EmptyWidget(1)
        ),
        mapOf(
            TAG_EMPTY to EmptyValueProvider,
            TAG_SPEED to SpeedProvider(locationProvider, coroutineScope)
        )
    )
}

@Composable
fun MainGreed(
    modifier: Modifier = Modifier,
    widgetList: List<Widget>,
    providers: Map<String, ValueProvider>
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val widgets by remember { mutableStateOf(widgetList) }
    LazyVerticalGrid(
        columns = Fixed(3),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged { size = it }
    ) {
        if (size.width > 0 && size.height > 0) {
            items(widgets, { it }, span = { item ->
                GridItemSpan(3)
            }) { item ->
                WidgetFromTag(size = size, tag = item.tag, providers = providers)
            }
        }
    }
}

@Composable
fun WidgetFromTag(
    size: IntSize,
    tag: String,
    providers: Map<String, ValueProvider>
) {
    when (tag) {
        TAG_EMPTY -> {
            EmptyWidgetContent(
                modifier = Modifier.height(
                    with(LocalDensity.current) {
                        (size.height / 2f).toDp()
                    }
                )
            )
        }

        TAG_SPEED -> {
            SpeedWidgetContent(
                modifier = Modifier.height(
                    with(LocalDensity.current) {
                        (size.height / 2f).toDp()
                    }
                ),
                valueProvider = providers[tag] ?: EmptyValueProvider
            )
        }

        else -> EmptyWidgetContent(
            modifier = Modifier.height(
                with(LocalDensity.current) {
                    (size.height / 2f).toDp()
                }
            )
        )
    }
}

@LightDarkFullPreview
@Composable
fun MainScreenPreview() {
    MainScreenContent()
}

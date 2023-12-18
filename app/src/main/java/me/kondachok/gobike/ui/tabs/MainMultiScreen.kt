@file:OptIn(ExperimentalMaterial3Api::class)

package me.kondachok.gobike.ui.tabs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons.Outlined
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Speed
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.github.terrakok.modo.multiscreen.MultiScreen
import com.github.terrakok.modo.multiscreen.MultiScreenNavModel
import com.github.terrakok.modo.multiscreen.selectContainer
import kotlinx.parcelize.Parcelize
import me.kondachok.gobike.navigation.MainStack
import me.kondachok.gobike.ui.history.HistoryScreen
import me.kondachok.gobike.ui.main.MainScreen
import me.kondachok.gobike.ui.settings.SettingsScreen
import me.kondachok.gobike.ui.theme.Dimen

@Parcelize
class MainMultiScreen(
    private val navModel: MultiScreenNavModel = MultiScreenNavModel(
        containers = listOf(
            MainStack(MainScreen()),
            MainStack(HistoryScreen()),
            MainStack(SettingsScreen()),
        ),
        selected = 0
    )
) : MultiScreen(navModel) {
    @Composable
    override fun Content() {
        Scaffold(
            content = {
                Box(
                    modifier = Modifier.padding(
                        top = it.calculateTopPadding(),
                        bottom = it.calculateBottomPadding()
                    )
                ) {
                    SelectedScreen()
                }
            },
            bottomBar = {
                Row(
                    modifier = Modifier
                        .height(Dimen.dp56)
                        .fillMaxWidth()
                ) {
                    Tab(
                        icon = Outlined.Speed,
                        name = "Спидометр",
                        isSelected = navigationState.selected == 0
                    ) {
                        selectContainer(0)
                    }
                    Tab(
                        icon = Outlined.History,
                        name = "История",
                        isSelected = navigationState.selected == 1
                    ) {
                        selectContainer(1)
                    }
                    Tab(
                        icon = Outlined.Settings,
                        name = "Настройки",
                        isSelected = navigationState.selected == 2
                    ) {
                        selectContainer(2)
                    }
                }
            }
        )
    }
}

@Composable
private fun RowScope.Tab(
    icon: ImageVector,
    name: String,
    isSelected: Boolean,
    select: () -> Unit
) {
    Column(
        modifier = Modifier
            .weight(1f)
            .height(Dimen.dp56)
            .clickable(
                onClick = select,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(true),
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val color = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.secondary
        }
        Icon(
            modifier = Modifier.size(Dimen.dp24),
            imageVector = icon,
            contentDescription = name,
            tint = color
        )
        Text(
            text = name,
            color = color
        )
    }
}

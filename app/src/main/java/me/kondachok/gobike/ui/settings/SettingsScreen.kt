package me.kondachok.gobike.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import kotlinx.parcelize.Parcelize
import me.kondachok.gobike.BuildConfig
import me.kondachok.gobike.R
import me.kondachok.gobike.ui.theme.Dimen
import me.kondachok.gobike.ui.theme.GoBikeTheme
import me.kondachok.gobike.utils.LightDarkFullPreview

@Parcelize
class SettingsScreen(
    override val screenKey: ScreenKey = generateScreenKey()
) : Screen {

    @Composable
    override fun Content() {
        SettingsScreenContent()
    }

}

@Composable
fun SettingsScreenContent() {
    Column(modifier = Modifier.fillMaxSize()) {
        SettingItem(
            title = stringResource(id = R.string.settings_app_version),
            subtitle = BuildConfig.VERSION_NAME
        )
    }
}

@Composable
fun SettingItem(
    title: String? = null,
    subtitle: String? = null,
    onClick: (() -> Unit)? = null
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(Dimen.dp56)
            .let { modifier ->
                onClick?.let {
                    modifier.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(true),
                        onClick = onClick
                    )
                } ?: modifier
            }
            .padding(horizontal = Dimen.dp16, vertical = Dimen.dp8),
        verticalArrangement = Arrangement.Center
    ) {
        if (title != null) {
            Text(
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary,
                text = title
            )
        }
        if (subtitle != null) {
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = subtitle
            )
        }
    }
}

@LightDarkFullPreview
@Composable
fun SettingsScreenPreview() {
    GoBikeTheme(dynamicColor = false) {
        Surface {
            SettingsScreenContent()
        }
    }
}

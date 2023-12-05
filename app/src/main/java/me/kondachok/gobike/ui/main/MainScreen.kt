package me.kondachok.gobike.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.github.terrakok.modo.LocalContainerScreen
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.ScreenKey
import com.github.terrakok.modo.generateScreenKey
import com.github.terrakok.modo.stack.StackScreen
import com.github.terrakok.modo.stack.forward
import kotlinx.parcelize.Parcelize
import me.kondachok.gobike.location.LocationProvider
import me.kondachok.gobike.ui.settings.SettingsScreen
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
    val location by locationProvider.location.collectAsState()
    Column {
        if (location != null) {
            Text("la: ${location!!.latitude}, lo: ${location!!.longitude}")
        } else {
            Text("no location")
        }
        FloatingActionButton(onClick = {
            stack.forward(SettingsScreen())
        }) {
            Icon(Icons.Filled.Menu, contentDescription = "Меню")
        }
    }
}

@LightDarkFullPreview
@Composable
fun MainScreenPreview() {
    MainScreenContent()
}

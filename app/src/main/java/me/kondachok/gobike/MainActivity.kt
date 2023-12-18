package me.kondachok.gobike

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.github.terrakok.modo.Modo
import com.github.terrakok.modo.RootScreen
import com.github.terrakok.modo.stack.StackScreen
import me.kondachok.gobike.location.AnyLifecycle
import me.kondachok.gobike.location.start
import me.kondachok.gobike.navigation.MainStack
import me.kondachok.gobike.ui.tabs.MainMultiScreen
import me.kondachok.gobike.ui.theme.GoBikeTheme
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {

    private val locationProvider: AnyLifecycle<Unit> by inject()

    private var rootScreen: RootScreen<StackScreen>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        rootScreen = Modo.init(savedInstanceState, rootScreen) {
            MainStack(MainMultiScreen())
        }
        setContent {
            GoBikeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    rootScreen?.Content()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        locationProvider.start()
    }

    override fun onStop() {
        super.onStop()
        locationProvider.stop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Modo.save(outState, rootScreen)
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isFinishing) {
            Modo.onRootScreenFinished(rootScreen)
        }
    }
}

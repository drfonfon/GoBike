@file:OptIn(ExperimentalAnimationApi::class)

package me.kondachok.gobike.navigation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.github.terrakok.modo.ComposeRendererScope
import com.github.terrakok.modo.SaveableContent
import com.github.terrakok.modo.Screen
import com.github.terrakok.modo.animation.StackTransitionType
import com.github.terrakok.modo.animation.calculateStackTransitionType
import com.github.terrakok.modo.stack.StackState

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun ComposeRendererScope<StackState>.SlideTransition() {
    ScreenTransition(
        transitionSpec = {
            val transitionType = calculateStackTransitionType(oldState, newState)
            if (transitionType == StackTransitionType.Replace) {
                (scaleIn(initialScale = 2f) + fadeIn()).togetherWith(fadeOut())
            } else {
                val (initialOffset, targetOffset) = when (transitionType) {
                    StackTransitionType.Pop -> ({ size: Int -> -size }) to ({ size: Int -> size })
                    else -> ({ size: Int -> size }) to ({ size: Int -> -size })
                }
                slideInHorizontally(initialOffsetX = initialOffset) togetherWith
                        slideOutHorizontally(targetOffsetX = targetOffset)
            }
        }
    )
}

@Composable
fun ComposeRendererScope<*>.ScreenTransition(
    modifier: Modifier = Modifier,
    transitionSpec: AnimatedContentTransitionScope<Screen>.() -> ContentTransform = {
        (fadeIn(animationSpec = tween(220, delayMillis = 90)) +
                scaleIn(
                    initialScale = 0.92f,
                    animationSpec = tween(220, delayMillis = 90)
                )).togetherWith(
            fadeOut(animationSpec = tween(90))
        )
    },
    content: @Composable AnimatedVisibilityScope.(Screen) -> Unit = { it.SaveableContent() }
) {
    val transition = updateTransition(targetState = screen, label = "ScreenTransition")
    transition.AnimatedContent(
        transitionSpec = transitionSpec,
        modifier = modifier,
        contentKey = { it.screenKey },
        content = content
    )
}

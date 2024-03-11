package com.eltonkola.emrashqip

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eltonkola.emrashqip.data.Emri
import com.eltonkola.emrashqip.ui.MainAppViewModel
import com.eltonkola.emrashqip.ui.ProfileCard
import com.eltonkola.emrashqip.ui.TextCard
import com.eltonkola.emrashqip.ui.theme.EmraShqipTheme
import com.haroncode.lazycardstack.LazyCardStack
import com.haroncode.lazycardstack.items
import com.haroncode.lazycardstack.rememberLazyCardStackState
import com.haroncode.lazycardstack.swiper.SwipeDirection
import kotlinx.coroutines.launch

@Composable
fun MainAppScreen( viewModel: MainAppViewModel) {


    val scope = rememberCoroutineScope()
   // val profiles = remember { profiles() }
    val lazyCardStack = rememberLazyCardStackState(
        animationSpec = tween(400)
    )

    val emrat = viewModel.emriListState

    LazyCardStack(
        modifier = Modifier.padding(16.dp),
        state = lazyCardStack
    ) {
        items(
            items = emrat.value,
            key = Emri::id
        ) { emri ->
            ProfileCard(
                emri = emri,
                onClickLeft = {
                    scope.launch { lazyCardStack.animateToNext(SwipeDirection.Left) }
                },
                onClickRight = {
                    scope.launch { lazyCardStack.animateToNext(SwipeDirection.Right) }
                }
            )
        }

        item(
            key = { "end" }
        ) {
            TextCard(
                text = "Final unswippable card"
            )
        }

    }

}

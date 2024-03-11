package com.eltonkola.emrashqip.ui

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.eltonkola.emrashqip.data.DONTLIKE
import com.eltonkola.emrashqip.data.Emri
import com.eltonkola.emrashqip.data.FEMALE
import com.eltonkola.emrashqip.data.LIKE
import com.eltonkola.emrashqip.data.MALE
import com.eltonkola.emrashqip.ui.MainAppViewModel
import com.eltonkola.emrashqip.ui.ProfileCard
import com.haroncode.lazycardstack.LazyCardStack
import com.haroncode.lazycardstack.items
import com.haroncode.lazycardstack.rememberLazyCardStackState
import com.haroncode.lazycardstack.swiper.SwipeDirection
import kotlinx.coroutines.launch

@Composable
fun SwipeScreen( viewModel: MainAppViewModel, tts:(String) -> Unit ) {

Column {


    val scope = rememberCoroutineScope()
   // val profiles = remember { profiles() }
    val lazyCardStack = rememberLazyCardStackState(
        animationSpec = tween(400)
    )

    val allNames = remember { viewModel.unvotedNames }.collectAsState()
    val lastName = viewModel.lastName.collectAsState()

    LazyCardStack(
        modifier = Modifier.padding(16.dp),
        state = lazyCardStack,
        onSwipedItem = { index, direction ->
            if(direction == SwipeDirection.Right){
                scope.launch {
                    viewModel.updateEmri(allNames.value[index].copy(favorite = LIKE))
                }
            }else if(direction == SwipeDirection.Left){
                scope.launch {
                    viewModel.updateEmri(allNames.value[index].copy(favorite = DONTLIKE))
                }
            }
        },
    ) {
        items(
            items = allNames.value,
            key = Emri::id
        ) { emri ->
            ProfileCard(
                mbiemri = lastName.value,
                emri = emri,
                tts = tts,
                onDontLikeClick = {
                    scope.launch {
                        lazyCardStack.animateToNext(SwipeDirection.Left)
                        viewModel.updateEmri(emri.copy(favorite = DONTLIKE))
                    }
                },
                onLikeClick = {
                    scope.launch {
                        lazyCardStack.animateToNext(SwipeDirection.Right)
                        viewModel.updateEmri(emri.copy(favorite = LIKE))
                    }
                },
                onFemaleClick = {
                    scope.launch {
                        viewModel.updateEmri(emri.copy(sex = FEMALE))
                    }
                },
                onMaleClick = {
                    scope.launch {
                        viewModel.updateEmri(emri.copy(sex = MALE))
                    }
                }

            )
        }

        item(
            key = { "end" }
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "..."
                )

            }
        }

    }
}
}

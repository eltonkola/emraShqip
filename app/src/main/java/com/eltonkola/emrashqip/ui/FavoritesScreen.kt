package com.eltonkola.emrashqip.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eltonkola.emrashqip.data.Emri
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen( viewModel: MainAppViewModel) {
Column {

    val emrat = remember { viewModel.favorites }.collectAsState()
    val scope = rememberCoroutineScope()

    Text(text = "All favorites: ${emrat.value.size}")

    LazyColumn {
        items(
            items = emrat.value,
            key =  Emri::id
        ) { emri ->
            FavoriteRow(
                emri = emri,
                unFavorite = {
                    scope.launch {
                         viewModel.updateEmri(emri.copy(favorite = -1))
                    }
                }
            )

        }

    }

}
}


@Composable
fun FavoriteRow(
    emri: Emri,
    unFavorite: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp)
    ) {
        Row (modifier = Modifier.fillMaxWidth()) {
            Text(text = emri.emri)

            Text(text = emri.popularity.toString())

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                modifier = Modifier,
                onClick = unFavorite,
             ) {
                Image(
                    modifier = Modifier.size(68.dp),
                    imageVector = Icons.Default.Favorite,
                    contentDescription = ""
                )
            }


        }
    }

}
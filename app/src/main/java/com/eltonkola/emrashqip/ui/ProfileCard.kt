package com.eltonkola.emrashqip.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eltonkola.emrashqip.R
import com.eltonkola.emrashqip.data.Emri
import com.eltonkola.emrashqip.data.FEMALE
import com.eltonkola.emrashqip.data.MALE

@Composable
fun ProfileCard(
    modifier: Modifier = Modifier,
    emri: Emri,
    mbiemri: String,
    onDontLikeClick: () -> Unit,
    onLikeClick: () -> Unit,
    onFemaleClick: () -> Unit,
    onMaleClick: () -> Unit,
    tts:(String) -> Unit
) {

    Card(
        modifier = Modifier.then(modifier),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {


            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row {




                        Image(
                            modifier = Modifier
                                .size(68.dp)
                                .clickable {
                                    onFemaleClick()
                                }
                                .alpha(
                                    if (emri.sex == FEMALE) 1.0f else 0.5f
                                ),
                            painter = painterResource(id = R.drawable.girl),
                            contentDescription = ""
                        )

                        Spacer(modifier = Modifier.size(32.dp))

                        Image(
                            modifier = Modifier
                                .size(68.dp)
                                .clickable {
                                    onMaleClick()
                                }
                                .alpha(
                                    if (emri.sex == MALE) 1.0f else 0.5f
                                ),
                            painter = painterResource(id = R.drawable.boy),
                            contentDescription = ""
                        )


                }
                Spacer(modifier = Modifier.weight(0.5f))
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.background
                    ),
                    onClick = { tts("${emri.emri} ${mbiemri}") }
                ) {
                    Image(
                        modifier = Modifier.size(36.dp),
                        painter = painterResource(id = R.drawable.play),
                        contentDescription = ""
                    )
                }
                Spacer(modifier = Modifier.weight(0.5f))

                Text(
                    text = emri.emri,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 62.sp,
                    modifier = Modifier.padding(16.dp)
                )

                Text(
                    text = mbiemri,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 62.sp,
                    modifier = Modifier.padding(16.dp)
                )

                Text(
                    text = "popularity: ${emri.popularity}",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(16.dp)
                )

                Text(
                    text = if (emri.favorite == 0) "Dont like" else if (emri.sex == 1) "Like" else "",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 22.sp,
                    modifier = Modifier
                        .padding(16.dp)
                )



                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier,
                        onClick = onDontLikeClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Image(
                            modifier = Modifier.size(68.dp),
                            imageVector = Icons.Default.Clear,
                            contentDescription = ""
                        )
                    }


                    Button(
                        modifier = Modifier,
                        onClick = onLikeClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
                    ) {
                        Image(
                            modifier = Modifier.size(68.dp),
                            imageVector = Icons.Default.ThumbUp,
                            contentDescription = ""
                        )
                    }
                }
            }
        }
    }
}


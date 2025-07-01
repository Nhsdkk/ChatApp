package com.eclipsel.chatapp.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eclipsel.chatapp.R
import kotlin.random.Random

@Preview
@Composable
fun IconList(modifier: Modifier = Modifier) {
    val offsets = (1..9).map { Pair(Random.nextInt(0, 25), Random.nextInt(0, 15)) }
    val icons = listOf(
        R.drawable.fire,
        R.drawable.cool,
        R.drawable.eyes,
        R.drawable.monkey,
        R.drawable.drops,
        R.drawable.in_love,
        R.drawable.mouth,
        R.drawable.person_exhausted,
        R.drawable.valentine
    )

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        for (i in 0..2) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                for (j in 0..2) {
                    Image(
                        painter = painterResource(icons[i * 3 + j]),
                        modifier = Modifier
                            .padding(
                                horizontal = offsets[i * 3 + j].first.dp,
                                vertical = offsets[i * 3 + j].second.dp
                            )
                            .height(32.dp)
                            .width(32.dp),
                        contentDescription = null
                    )
                }
            }
        }
    }
}
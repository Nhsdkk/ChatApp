package com.eclipsel.chatapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eclipsel.chatapp.ui.theme.BlackTransparent50
import com.eclipsel.chatapp.ui.theme.BlueSolidPrimary
import com.eclipsel.chatapp.ui.theme.ChatAppTheme
import com.eclipsel.chatapp.ui.theme.PinkSolidPrimary
import com.eclipsel.chatapp.ui.theme.WhiteSolidPrimary
import com.eclipsel.chatapp.ui.theme.WhiteTransparent50
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChatAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun StartScreen() {
    ChatAppTheme(
        darkTheme = false
    ) {
        Background()
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
        ) {
            Column (
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                IconList()
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(R.drawable.app_icon),
                    modifier = Modifier
                        .width(64.dp)
                        .height(64.dp),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = "Anonymous chats with other people",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Meet random people in chats. Gift or receive tokens." ,
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
            Button(
                onClick = {},
                modifier = Modifier
                    .weight(0.1f)
                    .fillMaxWidth(),
                colors = ButtonColors(
                    containerColor = WhiteSolidPrimary,
                    contentColor = MaterialTheme.colorScheme.primary,
                    disabledContentColor = BlackTransparent50,
                    disabledContainerColor = WhiteTransparent50
                ),
                shape = RoundedCornerShape(20.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_person_outline_24),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )
                    Text(
                        text = "Sign in to account",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

    }
}

@Composable
fun IconList( modifier: Modifier = Modifier) {
    val offsets = (1..9).map { Pair(Random.nextInt(0, 25), Random.nextInt(0, 15)) }
    val  icons = listOf(
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

@Composable
fun Background(modifier: Modifier = Modifier) {
    Box (
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        PinkSolidPrimary,
                        BlueSolidPrimary
                    ),
                    start = Offset.Zero
                ),
                alpha = 0.7f
            )
    )
}
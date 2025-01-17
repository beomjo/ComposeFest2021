package io.beomjo.codelab.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.beomjo.codelab.basics.ui.theme.BasiccodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasiccodelabTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnBoarding) {
        OnboardingScreen {
            shouldShowOnBoarding = false
        }
    } else {
        Greetings()
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(4.dp)) {
        items(items = names) { name ->
            Greeting(name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isExpand by remember { mutableStateOf(false) }
    val extraPadding by animateDpAsState(
        targetValue = if (isExpand) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(24.dp)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp)),
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Hello")
                Text(
                    text = "$name!",
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Spacer(modifier = Modifier.padding(bottom = 10.dp))
                if (isExpand) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }
            }
            IconButton(
                modifier = Modifier.then(Modifier.size(24.dp)),
                onClick = { isExpand = !isExpand }) {
                Icon(
                    imageVector =
                    if (isExpand) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (isExpand) {
                        stringResource(id = R.string.show_less)
                    } else {
                        stringResource(id = R.string.show_more)
                    }
                )
            }
        }
    }
}

@Composable
fun OnboardingScreen(onClickContinue: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basic Codelab!")
            Button(
                modifier = Modifier.padding(24.dp),
                onClick = onClickContinue
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "OnBoarding Preview",
    widthDp = 320,
    heightDp = 320
)
@Composable
fun OnboardingPreview() {
    BasiccodelabTheme {
        OnboardingScreen {
        }
    }
}

@Preview(
    showBackground = true,
    name = "Greetings Preview",
    widthDp = 320,
    heightDp = 320
)
@Composable
fun GreetingsPreview() {
    BasiccodelabTheme {
        Greetings()
    }
}


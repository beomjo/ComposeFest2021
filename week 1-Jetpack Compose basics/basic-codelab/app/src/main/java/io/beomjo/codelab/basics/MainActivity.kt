package io.beomjo.codelab.basics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.beomjo.codelab.basics.ui.theme.BasiccodelabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasiccodelabTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowOnBoarding by remember { mutableStateOf(true) }

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
    val isExpand = remember { mutableStateOf(false) }
    val extraPadding = if (isExpand.value) 48.dp else 0.dp

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(extraPadding)
            ) {
                Text(text = "Hello")
                Text(text = "$name!")
            }
            OutlinedButton(
                onClick = {
                    isExpand.value = !isExpand.value
                }
            ) {
                Text(text = if (isExpand.value) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(onClickContinue: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxWidth(),
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
    name = "Text Preview",
    widthDp = 320,
    heightDp = 320
)
@Composable
fun DefaultPreview() {
    BasiccodelabTheme {
        MyApp()
    }
}

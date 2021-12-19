package com.materialuiux.jetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyScreenContent()
        }
    }
}


@Composable
fun MyScreenContent(
    namelist: List<String> = List(5) { "Hello $it" }
) {
    var counterState by remember {
        mutableStateOf(0)
    }
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(10.dp)
    ) {
        ScrolaberLazyList(namelist, Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        TestCard()
        Spacer(modifier = Modifier.height(8.dp))

        Counter(count = counterState,
            updateCount = { newCount ->
                counterState = newCount
            }
        )
        if (counterState > 5) {
            Greeting("counterState > 5 = true")
        } else {
            Greeting("counterState > 5 = false")
        }

        Divider()

        Button(onClick = {
            context.startActivity(Intent(context, TestActivity::class.java))
        }) {
            Text(text = "start test screen")
        }
        Divider()

        var text by remember {
            mutableStateOf("")
        }

        Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp, modifier = Modifier.fillMaxWidth()) {
            Column( modifier = Modifier.fillMaxWidth().padding(10.dp)) {
                Spacer(modifier = Modifier.height(8.dp))
                TextField (
                    value = text,
                    onValueChange = { text = it },
                    label = { Text("Label") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField (
                    value = text,
                    onValueChange = { text = it },
                    colors = TextFieldDefaults.outlinedTextFieldColors(unfocusedLabelColor = Color.Yellow),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun ScrolaberLazyList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier) {
        items(items = names) { name ->
            Greeting(name = name)
            Divider()
        }
    }
}

@Composable
fun TestCard() {
    // Add padding around our message
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                // Set image size to 60 dp
                .size(60.dp)
                // Clip image to be shaped as a circle
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )

        // Add a horizontal space between the image and the column
        Spacer(modifier = Modifier.width(8.dp))

        Column {
            Text(
                text = "Full Name",
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle1
            )
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = "lorim ipusim",
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}


@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(onClick = { updateCount(count + 1) }) {
        Text(text = "i have been clicked $count times")
    }
}


@Composable
fun Greeting(name: String) {
    var isSlected by remember {
        mutableStateOf(true)
    }

    val tagerColor by animateColorAsState(
        targetValue = if (isSlected) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.secondaryVariant,
        animationSpec = tween(durationMillis = 2000)
    )

    Surface(color = tagerColor, modifier = Modifier.clickable {
        isSlected = !isSlected
    }) {
        Text(
            text = "Hello $name!",
            Modifier
                .padding(16.dp)
                .clickable {
                    isSlected = !isSlected
                }
        )
    }
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
    MyScreenContent()
}
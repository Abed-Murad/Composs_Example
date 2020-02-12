package com.example.jetnews

import android.os.Bundle
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.compose.Model
import androidx.compose.unaryPlus
import androidx.ui.core.Clip
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.DrawImage
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*
import androidx.ui.material.surface.Surface
import androidx.ui.res.imageResource
import androidx.ui.text.style.TextOverflow
import androidx.ui.tooling.preview.Preview

class CoolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(children: @Composable() () -> Unit) {
    MaterialTheme {
        Surface() {
            children()
        }
    }
}


@Composable
fun MyScreenContent(
    names: List<String> = listOf("Android", "there"),
    counterState: CounterState = CounterState(),
    formState: FormState = FormState()

) {
    Column(modifier = ExpandedHeight) {
        Column(modifier = Flexible(1f)) {

            for (name in names) {
                Greeting(name = name)
                Divider(color = Color.Black)
            }
        }
        Divider(color = Color.Transparent)
        Counter(state = counterState)
        Form(formState = formState)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Spacing(24.dp))
}

@Composable
fun NewStory() {
    MaterialTheme {
        val typography = +MaterialTheme.typography()
        val image = +imageResource(R.drawable.header)

        Column(modifier = Spacing(16.dp)) {
            Container(modifier = Height(180.dp) wraps Expanded) {
                Clip(shape = RoundedCornerShape(8.dp)) {
                    DrawImage(image)
                }

            }
            HeightSpacer(height = 16.dp)

            Text(
                text = "This is the test text for testing text title test 001 and large title zoom style.",
                style = typography.h5.withOpacity(0.90f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Divider(color = Color.Black)
            Text(text = "then", style = typography.overline.withOpacity(0.60f))
            Divider(color = Color.Black)
            Text(text = "Shit Happens!", style = typography.caption.withOpacity(0.20f))
            for (i in 1..100) {
                Text(text = "then", style = typography.overline.withOpacity(0.60f))
            }
        }
    }
}

@Composable
fun Counter(state: CounterState) {
    Button(
        text = "I've been clicked ${state.count} times",
        onClick = {
            state.count++
        }, style = ContainedButtonStyle(color = if (state.count > 5) Color.Green else Color.White))
}

@Composable
fun Form(formState: FormState) {
    Checkbox(checked = formState.optionChecked,
        onCheckedChange = { newState ->
            formState.optionChecked = newState
        })
}

@Preview
@Composable
fun PreviewNewStory() {
    MaterialTheme {
        MyScreenContent()
    }
}

@Model
class CounterState(var count: Int = 0)

@Model
class FormState(var optionChecked: Boolean = false)


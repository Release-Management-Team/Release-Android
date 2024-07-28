package com.sogang.release

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sogang.release.ui.theme.ReleaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReleaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    HomeScreen("Android")
                }
            }
        }
    }
}

@Composable
fun HomeScreen(name: String, modifier: Modifier = Modifier) {
    Column {
        HomeButton(name = "jiwon")
        HomeButton(name = "jiwon")
        HomeButton(name = "jiwon")
    }
}

@Composable
fun HomeButton(name: String) {
    Box(modifier = Modifier.clickable {  }) {
        Text(text = name)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReleaseTheme {
        HomeScreen("Android")
    }
}
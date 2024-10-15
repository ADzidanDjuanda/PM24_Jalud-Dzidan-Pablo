package com.example.awkproj

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.awkproj.ui.theme.AWKProjTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Install the splash screen
        installSplashScreen()

        // Set the Compose content
        setContent {
            AWKProjTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        onButtonClick = {
                            // Show the Toast when the button is clicked
                            Toast.makeText(this, "Button clicked!", Toast.LENGTH_SHORT).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier, onButtonClick: () -> Unit) {
    // Centering the content vertically and horizontally using Box
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Add a button that shows "Click Me" and triggers a toast on click
        Button(onClick = onButtonClick) {
            Text(text = "Click Me")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AWKProjTheme {
        MainScreen(onButtonClick = {})
    }
}

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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "main"
                    ) {
                        composable("main") {
                            MainScreen(
                                modifier = Modifier.padding(innerPadding),
                                onButtonClick = {
                                    // Show the Toast when the main button is clicked
                                    Toast.makeText(this@MainActivity, "Button clicked!", Toast.LENGTH_SHORT).show()
                                },
                                onSettingsClick = {
                                    // Navigate to the settings screen
                                    navController.navigate("settings")
                                }
                            )
                        }
                        composable("settings") {
                            SettingsScreen(
                                onBackClick = {
                                    // Navigate back to the main screen
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    // Centering the content vertically and horizontally using Box
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Main screen button
        Button(onClick = onButtonClick) {
            Text(text = "Click Me")
        }
    }

    // Settings button in the top-left corner
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Button(onClick = onSettingsClick, modifier = Modifier.padding(16.dp)) {
            Text(text = "Settings")
        }
    }
}

@Composable
fun SettingsScreen(onBackClick: () -> Unit) {
    // Layout for the settings page
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Settings Page")

            Spacer(modifier = Modifier.height(16.dp))

            // Button to go back to the main screen
            Button(onClick = onBackClick) {
                Text(text = "Back")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AWKProjTheme {
        MainScreen(onButtonClick = {}, onSettingsClick = {})
    }
}

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
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : ComponentActivity() {
    // Firebase instances
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()

        enableEdgeToEdge()
        installSplashScreen()

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
                                navController = navController, // Pass navController
                                onButtonClick = { addSampleDataToFirestore() }
                            )
                        }
                        composable("settings") {
                            SettingsScreen(navController = navController) // Pass navController
                        }
                    }
                }
            }
        }
    }
    private fun addSampleDataToFirestore() {
        val sampleData = mapOf(
            "name" to "Robot Controller",
            "type" to "Demo"
        )

        firestore.collection("robots")
            .add(sampleData)
            .addOnSuccessListener {
                Toast.makeText(this, "Data added successfully!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to add data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController, // Add navController parameter
    onButtonClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = onButtonClick) {
            Text(text = "Click Me")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("settings") }) {
            Text(text = "Settings")
        }
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) { // Add navController parameter
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Settings Page")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.popBackStack() }) {
            Text(text = "Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    AWKProjTheme {
        val navController = rememberNavController()
        MainScreen(navController = navController, onButtonClick = {})
    }
}

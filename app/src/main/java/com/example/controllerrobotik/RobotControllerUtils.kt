package com.example.controllerrobotik

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ControllerRobotikApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") { MainScreen(navController) }
        composable("robotStatus") { RobotStatusPage() }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    var xCoordinate by remember { mutableStateOf("") }
    var yCoordinate by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf("Status: Ready") }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        TextField(
                            value = xCoordinate,
                            onValueChange = { xCoordinate = it },
                            label = { Text("Enter X Coordinate") }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = yCoordinate,
                            onValueChange = { yCoordinate = it },
                            label = { Text("Enter Y Coordinate") }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(onClick = {
                            val x = xCoordinate.toIntOrNull()
                            val y = yCoordinate.toIntOrNull()
                            if (x != null && y != null) {
                                sendCoordinates(x, y)
                                statusMessage = "Sending coordinates: ($x, $y)"
                            } else {
                                statusMessage = "Please enter valid coordinates."
                            }
                        }) {
                            Text("Send Coordinates")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(statusMessage)
                    }
                }

                Button(onClick = { navController.navigate("robotStatus") }) {
                    Text("Check Robot Status")
                }
            }
        }
    }
}

fun sendCoordinates(x: Int, y: Int) {
    // Logic to send coordinates to the robot
    // Add your sending logic here (e.g., via Bluetooth, Wi-Fi, etc.)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ControllerRobotikApp()
}
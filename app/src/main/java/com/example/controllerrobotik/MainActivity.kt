package com.example.controllerrobotik

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ControllerRobotikApp()
        }
    }
}

@Composable
fun ControllerRobotikApp() {
    var xCoordinate by remember { mutableStateOf("") }
    var yCoordinate by remember { mutableStateOf("") }
    var statusMessage by remember { mutableStateOf("Status: Ready") }

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
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

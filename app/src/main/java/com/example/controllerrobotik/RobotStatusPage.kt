package com.example.controllerrobotik

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RobotStatusPage() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text("Robot Status Checks", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))

            StatusItem(icon = Icons.Filled.CheckCircle, status = "Connection Status: Online", color = Color.Green)
            StatusItem(icon = Icons.Filled.Warning, status = "Battery Level: Warning", color = Color.Yellow)
            StatusItem(icon = Icons.Filled.CheckCircle, status = "Sensor Readings: Normal", color = Color.Green)
            StatusItem(icon = Icons.Filled.Warning, status = "Motor Status: Warning", color = Color.Yellow)
            StatusItem(icon = Icons.Filled.CheckCircle, status = "Error Codes: None", color = Color.Green)
            // Add more status items as needed
        }
    }
}

@Composable
fun StatusItem(icon: ImageVector, status: String, color: Color) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(status)
    }
}

@Preview(showBackground = true)
@Composable
fun RobotStatusPagePreview() {
    RobotStatusPage()
}
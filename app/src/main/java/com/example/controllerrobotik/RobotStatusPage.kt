package com.example.controllerrobotik

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RobotStatusPage() {
    Column {
        Text("Robot Status Checks")
        // Add list items for robot checks here
        Text("- Connection Status")
        Text("- Battery Level")
        Text("- Sensor Readings")
        Text("- Motor Status")
        Text("- Error Codes")
        // Add more checks as needed
    }
}

@Preview(showBackground = true)
@Composable
fun RobotStatusPagePreview() {
    RobotStatusPage()
}
package com.orlove101.android.lab2orlov.ui.result

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun ResultScreen(
    navController: NavController,
    result: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable { navController.popBackStack() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = result.ifEmpty { "Немає відповіді" }, color = Color.White)
    }
}
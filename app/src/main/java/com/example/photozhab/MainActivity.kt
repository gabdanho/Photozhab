package com.example.photozhab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.photozhab.ui.screens.EditorScreen
import com.example.photozhab.ui.theme.PhotozhabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotozhabTheme {
                EditorScreen()
            }
        }
    }
}
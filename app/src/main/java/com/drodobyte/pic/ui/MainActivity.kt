package com.drodobyte.pic.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.drodobyte.feature.intake.IntakeScreen
import com.drodobyte.pic.ui.theme.ProteinIntakeCalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProteinIntakeCalculatorTheme {
                IntakeScreen()
            }
        }
    }
}

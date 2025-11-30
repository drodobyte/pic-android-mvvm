package com.drodobyte.pic.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.drodobyte.feature.nutriens.IntakeScreen
import com.drodobyte.pic.R
import com.drodobyte.pic.ui.theme.ProteinIntakeCalculatorTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProteinIntakeCalculatorTheme {
                ProteinIntakeCalculatorApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun ProteinIntakeCalculatorApp() {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.padding(30.dp),
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
    ) { padding ->
        val error = stringResource(R.string.error_message)
        IntakeScreen(
            modifier = Modifier.padding(padding),
            onError = {
                scope.launch {
                    snackbarHostState.showSnackbar(error)
                }
            }
        )
    }
}

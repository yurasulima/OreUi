package io.dangerous.oreui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class OreUiSampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OreTheme {
                OreUiSample()
            }
        }
    }
}

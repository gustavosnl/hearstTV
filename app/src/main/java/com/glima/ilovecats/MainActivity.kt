package com.glima.ilovecats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.glima.ilovecats.ui.theme.ILoveCats20Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ILoveCats20Theme {
                val navController = rememberNavController()
                Navigation(navController)
            }
        }
    }
}

package com.glima.ilovecats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.glima.ilovecats.feature.list.BreedList
import com.glima.ilovecats.feature.list.BreedListViewModel
import com.glima.ilovecats.ui.theme.ILoveCats20Theme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val breedListViewModel by viewModel<BreedListViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ILoveCats20Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    BreedList(viewModel = breedListViewModel)

                }
            }
        }
    }
}
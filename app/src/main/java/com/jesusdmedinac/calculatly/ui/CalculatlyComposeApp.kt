package com.jesusdmedinac.calculatly.ui

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jesusdmedinac.calculatly.ui.pages.CalculatorPage
import com.jesusdmedinac.calculatly.ui.theme.CalculatlyTheme
import com.jesusdmedinac.calculatly.viewmodel.CalculatorViewModel

@ExperimentalFoundationApi
@Composable
fun CalculatlyApp() {
    CalculatlyTheme {
        val navController: NavHostController = rememberNavController()
        NavHost(
            modifier = Modifier.fillMaxSize(),
            navController = navController,
            startDestination = "CalculatorPage"
        ) {
            composable("CalculatorPage") {
                val calculatorViewModel: CalculatorViewModel = hiltViewModel()

                val calculatorState by calculatorViewModel.container.stateFlow.collectAsState()

                CalculatorPage(
                    calculatorState,
                ) {
                    calculatorViewModel.onCalculatorKeyClicked(it)
                    println(it.toString())
                }
            }
        }
    }
}
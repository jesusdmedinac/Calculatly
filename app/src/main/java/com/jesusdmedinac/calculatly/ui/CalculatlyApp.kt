package com.jesusdmedinac.calculatly.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.jesusdmedinac.calculatly.ui.pages.CalculatorPage
import com.jesusdmedinac.calculatly.ui.theme.CalculatlyTheme

@ExperimentalFoundationApi
@Composable
fun CalculatlyApp() {
    CalculatlyTheme {
        CalculatorPage()
    }
}
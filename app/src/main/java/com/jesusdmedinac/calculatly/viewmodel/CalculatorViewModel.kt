package com.jesusdmedinac.calculatly.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.container
import org.orbitmvi.orbit.syntax.simple.SimpleContext
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor() : ViewModel(),
    ContainerHost<CalculatorViewModel.CalculatorState, CalculatorViewModel.CalculatorSideEffect> {
    override val container: Container<CalculatorState, CalculatorSideEffect> =
        viewModelScope.container(CalculatorState())

    data class CalculatorState(
        val displayText: String = "0",
        val clickedKeys: List<CalculatorKey> = emptyList()
    ) {
        val shouldDisplayACKey: Boolean get() = displayText == "0"
    }

    sealed class CalculatorSideEffect

    sealed class CalculatorKey {
        sealed class MainKey : CalculatorKey() {
            object Ac : MainKey()
            object MoreOrLess : MainKey()
            object Percentage : MainKey()
        }

        sealed class OperationKey : CalculatorKey() {
            object Division : OperationKey()
            object Multiply : OperationKey()
            object Subtraction : OperationKey()
            object Addition : OperationKey()
            object Equality : OperationKey()
        }

        sealed class NumberOrPointKey : CalculatorKey() {
            sealed class NumberKey : NumberOrPointKey() {
                object One : NumberKey()
                object Two : NumberKey()
                object Three : NumberKey()
                object Four : NumberKey()
                object Five : NumberKey()
                object Six : NumberKey()
                object Seven : NumberKey()
                object Eight : NumberKey()
                object Nine : NumberKey()
                object Zero : NumberKey()
            }

            object Point : NumberOrPointKey()
        }
    }

    fun onCalculatorKeyClicked(calculatorKey: CalculatorKey) {
        intent {
            reduce {
                val clickedKeys = state.clickedKeys.toMutableList()
                clickedKeys.add(0, calculatorKey)
                state.copy(clickedKeys = clickedKeys)
            }
            calculateDisplayText()
        }
    }

    private suspend fun SimpleSyntax<CalculatorState, CalculatorSideEffect>.calculateDisplayText() {
        reduce {
            val clickedKeys = state.clickedKeys.toMutableList()
            when (val lastClickedKey = clickedKeys.first()) {
                is CalculatorKey.MainKey -> onMainKeyClicked(lastClickedKey)
                is CalculatorKey.OperationKey -> TODO("Operation key not implemented yet")
                is CalculatorKey.NumberOrPointKey -> onNumberOrPointKeyClicked(lastClickedKey)
            }
        }
    }

    private fun SimpleContext<CalculatorState>.onMainKeyClicked(
        clickedKey: CalculatorKey.MainKey
    ): CalculatorState = when (clickedKey) {
        CalculatorKey.MainKey.Ac -> onAcClicked()
        CalculatorKey.MainKey.MoreOrLess -> onMoreOrLessClicked()
        CalculatorKey.MainKey.Percentage -> onPercentageClicked()
    }

    private fun onAcClicked() = CalculatorState(
        displayText = "0",
        clickedKeys = emptyList()
    )

    private fun SimpleContext<CalculatorState>.onPercentageClicked(): CalculatorState {
        val displayTextAsNumber = state.displayText.toDouble()
        val displayValue: Double = displayTextAsNumber / 100
        val displayText = "${if (displayValue == 0.0) displayValue.toInt() else displayValue}"
        return state.copy(displayText = displayText)
    }

    private fun SimpleContext<CalculatorState>.onMoreOrLessClicked(): CalculatorState {
        val addMinusSign = state.displayText.first() != '-'
        val displayText = if (addMinusSign) {
            "-${state.displayText}"
        } else {
            state.displayText.drop(1)
        }
        val clickedKeys = if (addMinusSign) {
            state.clickedKeys
        } else {
            state
                .clickedKeys
                .toMutableList()
                .apply { drop(1) }
        }
        return state.copy(
            displayText = displayText,
            clickedKeys = clickedKeys
        )
    }

    private fun SimpleContext<CalculatorState>.onNumberOrPointKeyClicked(
        clickedKey: CalculatorKey.NumberOrPointKey
    ): CalculatorState {
        val displayText = state.displayText
        val isDecimalNumber = displayText.startsWith("0.")
        val isZeroNumber = displayText == "0"
        return when (clickedKey) {
            is CalculatorKey.NumberOrPointKey.NumberKey -> {
                onNumberFrom0To9KeyClicked(clickedKey)
            }
            CalculatorKey.NumberOrPointKey.Point -> {
                val isNotNumberSmallerThanDecimal = displayText.length <= 2
                val isPointJustAdded = displayText == "0."
                val newDisplayText = when {
                    isDecimalNumber && isNotNumberSmallerThanDecimal -> "0."
                    isPointJustAdded -> displayText
                    isZeroNumber -> "${displayText}."
                    else -> displayText
                }
                state.copy(
                    displayText = newDisplayText
                )
            }
        }
    }

    private fun SimpleContext<CalculatorState>.onNumberFrom0To9KeyClicked(
        numberKey: CalculatorKey.NumberOrPointKey.NumberKey
    ): CalculatorState {
        val displayText = state.displayText
        val isDecimalNumber = displayText.startsWith("0.")
        val isZeroNumber = displayText == "0"
        val charNumber = when (numberKey) {
            CalculatorKey.NumberOrPointKey.NumberKey.Zero -> '0'
            CalculatorKey.NumberOrPointKey.NumberKey.One -> '1'
            CalculatorKey.NumberOrPointKey.NumberKey.Two -> '2'
            CalculatorKey.NumberOrPointKey.NumberKey.Three -> '3'
            CalculatorKey.NumberOrPointKey.NumberKey.Four -> '4'
            CalculatorKey.NumberOrPointKey.NumberKey.Five -> '5'
            CalculatorKey.NumberOrPointKey.NumberKey.Six -> '6'
            CalculatorKey.NumberOrPointKey.NumberKey.Seven -> '7'
            CalculatorKey.NumberOrPointKey.NumberKey.Eight -> '8'
            CalculatorKey.NumberOrPointKey.NumberKey.Nine -> '9'
        }
        val newDisplayText = when {
            isZeroNumber -> "$charNumber"
            isDecimalNumber -> "${displayText}${charNumber}"
            else -> "${displayText}${charNumber}"
        }
        return state.copy(
            displayText = newDisplayText
        )
    }
}

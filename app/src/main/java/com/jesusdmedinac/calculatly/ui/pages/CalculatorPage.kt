package com.jesusdmedinac.calculatly.ui.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jesusdmedinac.calculatly.ui.theme.CalculatlyTheme
import com.jesusdmedinac.calculatly.viewmodel.CalculatorViewModel

@ExperimentalFoundationApi
@Composable
fun CalculatorPage(
    calculatorState: CalculatorViewModel.CalculatorState,
    onCalculatorKeyClicked: (CalculatorViewModel.CalculatorKey) -> Unit,
) {
    val displayText = calculatorState.displayText
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val displayRef = createRef()
        val firstKeyRow = createRef()
        val secondKeyRow = createRef()
        val thirdKeyRow = createRef()
        val fourthKeyRow = createRef()
        val fifthKeyRow = createRef()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .constrainAs(displayRef) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(firstKeyRow.top)
                }
                .padding(32.dp),
            contentAlignment = Alignment.BottomEnd,
        ) {
            Text(
                text = displayText,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 72.sp
                ),
            )
        }

        KeyRow(
            modifier = Modifier.constrainAs(firstKeyRow) {
                top.linkTo(displayRef.bottom)
                bottom.linkTo(secondKeyRow.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            },
            firstKey = CalculatorViewModel.CalculatorKey.MainKey.Ac,
            secondKey = CalculatorViewModel.CalculatorKey.MainKey.MoreOrLess,
            thirdKey = CalculatorViewModel.CalculatorKey.MainKey.Percentage,
            fourthKey = CalculatorViewModel.CalculatorKey.OperationKey.Division,
            calculatorState = calculatorState,
            onCalculatorKeyClicked = onCalculatorKeyClicked,
        )

        KeyRow(
            modifier = Modifier
                .constrainAs(secondKeyRow) {
                    top.linkTo(firstKeyRow.bottom)
                    bottom.linkTo(thirdKeyRow.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            firstKey = CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Seven,
            secondKey = CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Eight,
            thirdKey = CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Nine,
            fourthKey = CalculatorViewModel.CalculatorKey.OperationKey.Multiply,
            calculatorState = calculatorState,
            onCalculatorKeyClicked = onCalculatorKeyClicked,
        )

        KeyRow(
            modifier = Modifier
                .constrainAs(thirdKeyRow) {
                    top.linkTo(secondKeyRow.bottom)
                    bottom.linkTo(fourthKeyRow.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            firstKey = CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Four,
            secondKey = CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Five,
            thirdKey = CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Six,
            fourthKey = CalculatorViewModel.CalculatorKey.OperationKey.Subtraction,
            calculatorState = calculatorState,
            onCalculatorKeyClicked = onCalculatorKeyClicked,
        )

        KeyRow(
            modifier = Modifier
                .constrainAs(fourthKeyRow) {
                    top.linkTo(thirdKeyRow.bottom)
                    bottom.linkTo(fifthKeyRow.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            firstKey = CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.One,
            secondKey = CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Two,
            thirdKey = CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Three,
            fourthKey = CalculatorViewModel.CalculatorKey.OperationKey.Addition,
            calculatorState = calculatorState,
            onCalculatorKeyClicked = onCalculatorKeyClicked,
        )

        LastKeyRow(
            modifier = Modifier
                .constrainAs(fifthKeyRow) {
                    top.linkTo(fourthKeyRow.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            calculatorState = calculatorState,
            onCalculatorKeyClicked = onCalculatorKeyClicked,
        )
    }
}

@Composable
private fun KeyRow(
    modifier: Modifier = Modifier,
    firstKey: CalculatorViewModel.CalculatorKey,
    secondKey: CalculatorViewModel.CalculatorKey,
    thirdKey: CalculatorViewModel.CalculatorKey,
    fourthKey: CalculatorViewModel.CalculatorKey,
    calculatorState: CalculatorViewModel.CalculatorState,
    onCalculatorKeyClicked: (CalculatorViewModel.CalculatorKey) -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxHeight(0.12f)
            .fillMaxWidth(),
    ) {
        val firstRef = createRef()
        val secondRef = createRef()
        val thirdRef = createRef()
        val fourthRef = createRef()

        Key(
            modifier = Modifier
                .constrainAs(firstRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(secondRef.start)
                },
            firstKey,
            calculatorState = calculatorState,
            onCalculatorKeyClicked = onCalculatorKeyClicked,
        )

        Key(
            modifier = Modifier
                .constrainAs(secondRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(firstRef.end)
                    end.linkTo(thirdRef.start)
                },
            secondKey,
            calculatorState = calculatorState,
            onCalculatorKeyClicked = onCalculatorKeyClicked,
        )

        Key(
            modifier = Modifier
                .constrainAs(thirdRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(secondRef.end)
                    end.linkTo(fourthRef.start)
                },
            thirdKey,
            calculatorState = calculatorState,
            onCalculatorKeyClicked = onCalculatorKeyClicked,
        )

        Key(
            modifier = Modifier
                .constrainAs(fourthRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(thirdRef.end)
                    end.linkTo(parent.end)
                },
            fourthKey,
            calculatorState = calculatorState,
            onCalculatorKeyClicked = onCalculatorKeyClicked,
        )
    }
}

@Composable
private fun LastKeyRow(
    modifier: Modifier = Modifier,
    calculatorState: CalculatorViewModel.CalculatorState,
    onCalculatorKeyClicked: (CalculatorViewModel.CalculatorKey) -> Unit,
) {
    ConstraintLayout(
        modifier = modifier
            .fillMaxHeight(0.12f)
            .fillMaxWidth(),
    ) {
        val firstRef = createRef()
        val secondRef = createRef()
        val thirdRef = createRef()
        val fourthRef = createRef()
        val fifthRef = createRef()

        val firstKey = CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Zero
        val secondKey = CalculatorViewModel.CalculatorKey.NumberOrPointKey.Point
        val thirdKey = CalculatorViewModel.CalculatorKey.OperationKey.Equality
        Box(
            modifier = Modifier
                .constrainAs(firstRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(secondRef.start)
                }
                .size(72.dp),
        )

        Box(
            modifier = Modifier
                .constrainAs(secondRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(firstRef.end)
                    end.linkTo(thirdRef.start)
                }
                .size(72.dp),
        )

        Button(
            modifier = Modifier
                .constrainAs(fifthRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(firstRef.start)
                    end.linkTo(secondRef.end)
                    width = Dimension.fillToConstraints
                }
                .clip(RoundedCornerShape(72.dp))
                .height(72.dp),
            onClick = { onCalculatorKeyClicked(firstKey) },
            colors = ButtonDefaults.buttonColors(
                containerColor = firstKey.toBackgroundColor()
            )
        ) {
            Text(
                text = firstKey.toStringResBy(calculatorState),
                color = firstKey.toForegroundColor()
            )
        }

        Key(
            modifier = Modifier
                .constrainAs(thirdRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(secondRef.end)
                    end.linkTo(fourthRef.start)
                },
            secondKey,
            calculatorState = calculatorState,
            onCalculatorKeyClicked = onCalculatorKeyClicked,
        )

        Key(
            modifier = Modifier
                .constrainAs(fourthRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(thirdRef.end)
                    end.linkTo(parent.end)
                },
            thirdKey,
            calculatorState = calculatorState,
            onCalculatorKeyClicked = onCalculatorKeyClicked,
        )
    }
}

@Composable
private fun Key(
    modifier: Modifier = Modifier,
    key: CalculatorViewModel.CalculatorKey,
    calculatorState: CalculatorViewModel.CalculatorState,
    onCalculatorKeyClicked: (CalculatorViewModel.CalculatorKey) -> Unit,
) {
    Button(
        modifier = modifier
            .clip(RoundedCornerShape(72.dp))
            .size(72.dp),
        onClick = { onCalculatorKeyClicked(key) },
        colors = ButtonDefaults.buttonColors(
            containerColor = key.toBackgroundColor()
        )
    ) {
        Text(
            text = key.toStringResBy(calculatorState),
            color = key.toForegroundColor()
        )
    }
}

@Composable
private fun CalculatorViewModel.CalculatorKey.toBackgroundColor(): Color = when (this) {
    is CalculatorViewModel.CalculatorKey.MainKey -> MaterialTheme.colorScheme.primary
    is CalculatorViewModel.CalculatorKey.OperationKey -> MaterialTheme.colorScheme.secondary
    is CalculatorViewModel.CalculatorKey.NumberOrPointKey -> MaterialTheme.colorScheme.tertiary
}

@Composable
private fun CalculatorViewModel.CalculatorKey.toForegroundColor(): Color = when (this) {
    is CalculatorViewModel.CalculatorKey.MainKey -> MaterialTheme.colorScheme.onPrimary
    is CalculatorViewModel.CalculatorKey.OperationKey -> MaterialTheme.colorScheme.onSecondary
    is CalculatorViewModel.CalculatorKey.NumberOrPointKey -> MaterialTheme.colorScheme.onTertiary
}

private fun CalculatorViewModel.CalculatorKey.toStringResBy(
    calculatorState: CalculatorViewModel.CalculatorState,
) = when (this) {
    CalculatorViewModel.CalculatorKey.MainKey.Ac ->
        if (calculatorState.shouldDisplayACKey) "AC"
        else "C"
    CalculatorViewModel.CalculatorKey.MainKey.MoreOrLess -> "+/-"
    CalculatorViewModel.CalculatorKey.MainKey.Percentage -> "%"
    CalculatorViewModel.CalculatorKey.OperationKey.Addition -> "+"
    CalculatorViewModel.CalculatorKey.OperationKey.Division -> "/"
    CalculatorViewModel.CalculatorKey.OperationKey.Multiply -> "*"
    CalculatorViewModel.CalculatorKey.OperationKey.Subtraction -> "-"
    CalculatorViewModel.CalculatorKey.OperationKey.Equality -> "="
    CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Zero -> "0"
    CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.One -> "1"
    CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Two -> "2"
    CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Three -> "3"
    CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Four -> "4"
    CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Five -> "5"
    CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Six -> "6"
    CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Seven -> "7"
    CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Eight -> "8"
    CalculatorViewModel.CalculatorKey.NumberOrPointKey.NumberKey.Nine -> "9"
    CalculatorViewModel.CalculatorKey.NumberOrPointKey.Point -> "."
}

@ExperimentalFoundationApi
@Preview(widthDp = 512)
@Composable
fun CalculatorPagePreviewOne() {
    CalculatlyTheme {
        CalculatorPage(CalculatorViewModel.CalculatorState(), {})
    }
}

@ExperimentalFoundationApi
@Preview(widthDp = 1024)
@Composable
fun CalculatorPagePreviewTwo() {
    CalculatlyTheme {
        CalculatorPage(
            CalculatorViewModel.CalculatorState(), {})
    }
}
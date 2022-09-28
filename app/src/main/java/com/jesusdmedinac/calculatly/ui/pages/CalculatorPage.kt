package com.jesusdmedinac.calculatly.ui.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.jesusdmedinac.calculatly.ui.theme.CalculatlyTheme

@ExperimentalFoundationApi
@Composable
fun CalculatorPage() {
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
                text = "0.000000000",
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
            firstKey = CalculatorKey.AC,
            secondKey = CalculatorKey.MORE_OR_LESS,
            thirdKey = CalculatorKey.PERCENTAGE,
            fourthKey = CalculatorKey.DIVISION,
        )

        KeyRow(
            modifier = Modifier
                .constrainAs(secondKeyRow) {
                    top.linkTo(firstKeyRow.bottom)
                    bottom.linkTo(thirdKeyRow.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            firstKey = CalculatorKey.SEVEN,
            secondKey = CalculatorKey.EIGHT,
            thirdKey = CalculatorKey.NINE,
            fourthKey = CalculatorKey.MULTIPLY,
        )

        KeyRow(
            modifier = Modifier
                .constrainAs(thirdKeyRow) {
                    top.linkTo(secondKeyRow.bottom)
                    bottom.linkTo(fourthKeyRow.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            firstKey = CalculatorKey.FOUR,
            secondKey = CalculatorKey.FIVE,
            thirdKey = CalculatorKey.SIX,
            fourthKey = CalculatorKey.SUBTRACTION,
        )

        KeyRow(
            modifier = Modifier
                .constrainAs(fourthKeyRow) {
                    top.linkTo(thirdKeyRow.bottom)
                    bottom.linkTo(fifthKeyRow.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            firstKey = CalculatorKey.ONE,
            secondKey = CalculatorKey.TWO,
            thirdKey = CalculatorKey.THREE,
            fourthKey = CalculatorKey.ADDITION,
        )

        LastKeyRow(
            modifier = Modifier
                .constrainAs(fifthKeyRow) {
                    top.linkTo(fourthKeyRow.bottom)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
        )
    }
}

@Composable
private fun KeyRow(
    modifier: Modifier = Modifier,
    firstKey: CalculatorKey,
    secondKey: CalculatorKey,
    thirdKey: CalculatorKey,
    fourthKey: CalculatorKey,
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
            firstKey,
            modifier = Modifier
                .constrainAs(firstRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(secondRef.start)
                },
        )

        Key(
            secondKey,
            modifier = Modifier
                .constrainAs(secondRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(firstRef.end)
                    end.linkTo(thirdRef.start)
                },
        )

        Key(
            thirdKey,
            modifier = Modifier
                .constrainAs(thirdRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(secondRef.end)
                    end.linkTo(fourthRef.start)
                },
        )

        Key(
            fourthKey,
            modifier = Modifier
                .constrainAs(fourthRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(thirdRef.end)
                    end.linkTo(parent.end)
                },
        )
    }
}

@Composable
private fun LastKeyRow(
    modifier: Modifier = Modifier,
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

        val firstKey = CalculatorKey.ZERO
        val secondKey = CalculatorKey.POINT
        val thirdKey = CalculatorKey.EQUALITY
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

        Box(
            modifier = Modifier
                .constrainAs(fifthRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(firstRef.start)
                    end.linkTo(secondRef.end)
                    width = Dimension.fillToConstraints
                }
                .clip(RoundedCornerShape(72.dp))
                .height(72.dp)
                .background(firstKey.type.toBackgroundColor()),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = firstKey.toStringRes(),
                color = firstKey.type.toForegroundColor()
            )
        }

        Key(
            secondKey,
            modifier = Modifier
                .constrainAs(thirdRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(secondRef.end)
                    end.linkTo(fourthRef.start)
                },
        )

        Key(
            thirdKey,
            modifier = Modifier
                .constrainAs(fourthRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(thirdRef.end)
                    end.linkTo(parent.end)
                },
        )
    }
}

/**
divisionRef,
multiplyRef,
subtractionRef,
additionRef,
equalityRef,
zeroRef,
oneRef,
twoRef,
threeRef,
fourRef,
fiveRef,
sixRef,
sevenRef,
eightRef,
nineRef,
pointRef,
 * */

@Composable
private fun Key(
    key: CalculatorKey,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(72.dp))
            .size(72.dp)
            .background(key.type.toBackgroundColor()),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = key.toStringRes(),
            color = key.type.toForegroundColor()
        )
    }
}

@Composable
private fun CalculatorKey.KeyType.toBackgroundColor(): Color = when (this) {
    CalculatorKey.KeyType.MAIN -> MaterialTheme.colorScheme.primary
    CalculatorKey.KeyType.OPERATION -> MaterialTheme.colorScheme.secondary
    CalculatorKey.KeyType.NUMBER -> MaterialTheme.colorScheme.tertiary
}

@Composable
private fun CalculatorKey.KeyType.toForegroundColor(): Color = when (this) {
    CalculatorKey.KeyType.MAIN -> MaterialTheme.colorScheme.onPrimary
    CalculatorKey.KeyType.OPERATION -> MaterialTheme.colorScheme.onSecondary
    CalculatorKey.KeyType.NUMBER -> MaterialTheme.colorScheme.onTertiary
}

private fun CalculatorKey.toStringRes() = when (this) {
    CalculatorKey.AC -> "AC"
    CalculatorKey.MORE_OR_LESS -> "+/-"
    CalculatorKey.PERCENTAGE -> "%"
    CalculatorKey.DIVISION -> "/"
    CalculatorKey.MULTIPLY -> "*"
    CalculatorKey.SUBTRACTION -> "-"
    CalculatorKey.ADDITION -> "+"
    CalculatorKey.EQUALITY -> "="
    CalculatorKey.ZERO -> "0"
    CalculatorKey.ONE -> "1"
    CalculatorKey.TWO -> "2"
    CalculatorKey.THREE -> "3"
    CalculatorKey.FOUR -> "4"
    CalculatorKey.FIVE -> "5"
    CalculatorKey.SIX -> "6"
    CalculatorKey.SEVEN -> "7"
    CalculatorKey.EIGHT -> "8"
    CalculatorKey.NINE -> "9"
    CalculatorKey.POINT -> "."
}

enum class CalculatorKey(
    val type: KeyType,
) {
    AC(KeyType.MAIN),
    MORE_OR_LESS(KeyType.MAIN),
    PERCENTAGE(KeyType.MAIN),
    DIVISION(KeyType.OPERATION),
    MULTIPLY(KeyType.OPERATION),
    SUBTRACTION(KeyType.OPERATION),
    ADDITION(KeyType.OPERATION),
    EQUALITY(KeyType.OPERATION),
    ZERO(KeyType.NUMBER),
    ONE(KeyType.NUMBER),
    TWO(KeyType.NUMBER),
    THREE(KeyType.NUMBER),
    FOUR(KeyType.NUMBER),
    FIVE(KeyType.NUMBER),
    SIX(KeyType.NUMBER),
    SEVEN(KeyType.NUMBER),
    EIGHT(KeyType.NUMBER),
    NINE(KeyType.NUMBER),
    POINT(KeyType.NUMBER);

    enum class KeyType {
        MAIN,
        OPERATION,
        NUMBER
    }
}

@ExperimentalFoundationApi
@Preview(widthDp = 512)
@Composable
fun CalculatorPagePreviewOne() {
    CalculatlyTheme {
        CalculatorPage()
    }
}

@ExperimentalFoundationApi
@Preview(widthDp = 1024)
@Composable
fun CalculatorPagePreviewTwo() {
    CalculatlyTheme {
        CalculatorPage()
    }
}
package dev.hyuwah.kalkulator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import dev.hyuwah.kalkulator.domain.model.CalculatorAction
import dev.hyuwah.kalkulator.ui.theme.KalkulatorTheme


private val buttons = listOf(
    listOf("C", "√", "^", "⌫"),
    listOf("%", "(", ")", "×"),
    listOf("7", "8", "9", "÷"),
    listOf("4", "5", "6", "-"),
    listOf("1", "2", "3", "+"),
    listOf(".", "0", "000", "="),
)

@Composable
fun CalculatorButtons(
    modifier: Modifier = Modifier,
    onAction: (CalculatorAction) -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        buttons.forEach { col ->
            Row(
                modifier = Modifier.weight(1f)
            ) {
                col.forEach { buttonText ->
                    val buttonColor: Pair<Color, Color> = when {
                        buttonText.isDigitsOnly() -> {
                            Pair(
                                MaterialTheme.colorScheme.surfaceVariant,
                                MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }

                        buttonText == "⌫" || buttonText == "C" -> {
                            Pair(
                                MaterialTheme.colorScheme.errorContainer,
                                MaterialTheme.colorScheme.onErrorContainer
                            )
                        }

                        else -> {
                            Pair(
                                MaterialTheme.colorScheme.primaryContainer,
                                MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                    CalculatorButton(
                        text = buttonText,
                        background = buttonColor.first,
                        textColor = buttonColor.second,
                        onClick = {
                            when (it) {
                                "=" -> onAction(CalculatorAction.Evaluate)
                                "⌫" -> onAction(CalculatorAction.Delete)
                                "C" -> onAction(CalculatorAction.Clear)
                                else -> onAction(CalculatorAction.Append(it))
                            }
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun CalculatorButtonsPreview() {
    KalkulatorTheme {
        CalculatorButtons(
            onAction = {}
        )
    }
}
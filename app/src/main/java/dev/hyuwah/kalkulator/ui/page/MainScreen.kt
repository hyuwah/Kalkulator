package dev.hyuwah.kalkulator.ui.page

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hyuwah.kalkulator.domain.model.CalculatorAction
import dev.hyuwah.kalkulator.ui.component.CalculatorButtons
import dev.hyuwah.kalkulator.ui.theme.KalkulatorTheme
import dev.hyuwah.kalkulator.ui.theme.firaFontFamily
import dev.hyuwah.kalkulator.utils.OperatorColorTransformation
import dev.hyuwah.kalkulator.utils.tapVibrate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    state: MainState,
    onAction: (MainAction) -> Unit
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {

        Column(
        ) {
            CompositionLocalProvider(value = LocalTextInputService provides null) {
                TextField(
                    value = TextFieldValue(
                        text = state.input,
                        selection = TextRange(state.cursorPos)
                    ),
                    onValueChange = {
                        onAction(MainAction.UpdateInput(it.text, it.selection.start))
                    },
                    textStyle = TextStyle(
                        textAlign = TextAlign.Start,
                        fontSize = 26.sp,
                        fontFamily = firaFontFamily,
                        fontWeight = FontWeight.SemiBold
                    ),
                    visualTransformation = OperatorColorTransformation(
                        parensColor = MaterialTheme.colorScheme.secondary,
                        operatorColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
            ) {
                Text(
                    text = "=",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = firaFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary.copy(if (state.result.isNotBlank()) 0.8f else 0.3f)
                    ),
                )
                Text(
                    text = state.result,
                    style = TextStyle(
                        textAlign = TextAlign.End,
                        fontSize = 28.sp,
                        fontFamily = firaFontFamily,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface.copy(if (state.isResultError) 0.5f else 1.0f)
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                        .basicMarquee()
                        .combinedClickable(
                            enabled = state.result.isNotBlank(),
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onLongClickLabel = "Copy Result",
                            onLongClick = {
                                clipboardManager.setText(AnnotatedString(state.result))
                                context.tapVibrate()
                            },
                            onClick = {}
                        )
                )
            }
            CalculatorButtons(
                modifier = Modifier.weight(2f),
                onAction = {
                    when (it) {
                        is CalculatorAction.Append -> onAction(MainAction.Append(it.symbol))
                        CalculatorAction.Clear -> onAction(MainAction.Clear)
                        CalculatorAction.Delete -> onAction(MainAction.Delete)
                        CalculatorAction.Evaluate -> onAction(MainAction.SaveResult)
                    }
                }
            )
        }

    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun MainScreenPreview() {
    KalkulatorTheme {
        MainScreen(
            state = MainState(result = ""),
            onAction = {}
        )
    }
}
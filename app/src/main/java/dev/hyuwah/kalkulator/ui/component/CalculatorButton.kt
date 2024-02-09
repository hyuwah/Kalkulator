package dev.hyuwah.kalkulator.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.hyuwah.kalkulator.ui.theme.KalkulatorTheme
import dev.hyuwah.kalkulator.ui.theme.firaFontFamily
import dev.hyuwah.kalkulator.utils.bounceClickable
import dev.hyuwah.kalkulator.utils.tapVibrate

@Composable
fun CalculatorButton(
    text: String,
    background: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    onClick: (text: String) -> Unit
) {
    val context = LocalContext.current
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .bounceClickable {
                context.tapVibrate()
                onClick(text)
            }
            .fillMaxSize()
            .padding(4.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(background)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = firaFontFamily,
                fontWeight = FontWeight.SemiBold,
                color = textColor
            ),
        )
    }
}

@Preview(showBackground = true, apiLevel = 33)
@Composable
fun OperatorCalculatorButtonPreview() {
    KalkulatorTheme {
        CalculatorButton(
            text = "=",
            background = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.primary,
            onClick = {})
    }
}
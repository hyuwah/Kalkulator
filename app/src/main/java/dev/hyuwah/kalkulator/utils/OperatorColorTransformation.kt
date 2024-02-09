package dev.hyuwah.kalkulator.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle

class OperatorColorTransformation(
    val parensColor: Color,
    val operatorColor: Color,
) : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            transform(text.toString()),
            OffsetMapping.Identity
        )
    }

    private fun transform(text: String): AnnotatedString {
        val builder = AnnotatedString.Builder()
        text.forEach {
            when {
                listOf('%', '^', '√', '×', '÷', '*', '/', '+', '-').contains(it) -> {
                    builder.withStyle(style = SpanStyle(color = operatorColor)) {
                        append(it)
                    }
                }

                it == '(' || it == ')' -> {
                    builder.withStyle(style = SpanStyle(color = parensColor)) {
                        append(it)
                    }
                }

                else -> {
                    builder.append(it)
                }
            }
        }
        return builder.toAnnotatedString()
    }
}
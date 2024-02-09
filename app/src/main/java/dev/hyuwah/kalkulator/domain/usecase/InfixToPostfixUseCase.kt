package dev.hyuwah.kalkulator.domain.usecase

import dev.hyuwah.kalkulator.domain.model.Operator

class InfixToPostfixUseCase {
    operator fun invoke(input: String): List<String> {
        val adjustedInput = input.trim()
            .replace(" ", "")
            .replace("×", "*")
            .replace("⨉", "*")
            .replace("x", "*", ignoreCase = true)
            .replace("÷", "/")
        val postfix = mutableListOf<String>()
        val operatorStack = ArrayDeque<Char>()

        var i = 0
        var isNegative = false

        while (i < adjustedInput.length) {
            val c = adjustedInput[i]
            when {
                i == 0 && c == Operator.UNARY_MINUS.symbol -> {
                    isNegative = true
                }

                c.isDigit() || c == '.' -> {
                    var digits = ""
                    while (i < adjustedInput.length) {
                        if (adjustedInput[i].isDigit() || adjustedInput[i] == '.') {
                            digits += adjustedInput[i++]
                        } else {
                            break
                        }
                    }
                    i--

                    val negativeSign = if (isNegative) {
                        isNegative = false
                        "-"
                    } else {
                        ""
                    }

                    postfix.add(negativeSign + digits)
                }

                c == Operator.LEFT_PARENTHESES.symbol -> {
                    operatorStack.add(c)
                }

                c == Operator.RIGHT_PARENTHESES.symbol -> {
                    var lastOperator = operatorStack.removeLastOrNull()
                    while (lastOperator != null && lastOperator != Operator.LEFT_PARENTHESES.symbol) {
                        postfix.add(lastOperator.toString())
                        lastOperator = operatorStack.removeLastOrNull()
                    }
                }

                Operator.isBinaryOperator(c) -> {
                    val prevC = adjustedInput[i - 1]
                    if (c == Operator.UNARY_MINUS.symbol && Operator.isNegativeSign(c, prevC)) {
                        // Is Unary Minus
                        isNegative = true
                    } else {
                        // Is Binary Operator
                        while (operatorStack.isNotEmpty() && Operator.precedence(operatorStack.lastOrNull()) >= Operator.precedence(
                                c
                            )
                        ) {
                            operatorStack.removeLastOrNull()?.let { postfix.add(it.toString()) }
                        }
                        operatorStack.add(c)
                    }
                }

                else -> {
                    throw IllegalArgumentException("Invalid char: $c")
                }
            }
            i++
        }

        while (operatorStack.isNotEmpty()) {
            postfix.add(operatorStack.removeLast().toString())
        }

        return postfix
    }
}
package dev.hyuwah.kalkulator.domain.usecase

import dev.hyuwah.kalkulator.domain.model.Operator
import kotlin.math.pow

class EvaluatePostfixUseCase {
    operator fun invoke(input: List<String>): String {
        val evaluateStack = ArrayDeque<Float>()

        input.forEach {
            when {
                it.toFloatOrNull() != null -> {
                    evaluateStack.add(it.toFloat())
                }

                Operator.isBinaryOperator(it) -> {
                    val a = evaluateStack.removeLastOrNull()
                    val b = evaluateStack.removeLastOrNull()
                    if (a != null && b != null) {
                        when (it) {
                            Operator.EXPONENT.symbol() -> {
                                evaluateStack.add(b.toDouble().pow(a.toDouble()).toFloat())
                            }

                            Operator.MULTIPLY.symbol() -> {
                                evaluateStack.add(b * a)
                            }

                            Operator.MODULUS.symbol() -> {
                                evaluateStack.add(b.mod(a))
                            }

                            Operator.DIVIDE.symbol() -> {
                                if (a == 0f) throw ArithmeticException("Divide by $a")
                                evaluateStack.add(b / a)
                            }

                            Operator.ADD.symbol() -> {
                                evaluateStack.add(b + a)
                            }

                            Operator.SUBSTRACT.symbol() -> {
                                evaluateStack.add(b - a)
                            }
                        }
                    }

                }
            }
        }

        return evaluateStack.removeLast().toString()
    }
}
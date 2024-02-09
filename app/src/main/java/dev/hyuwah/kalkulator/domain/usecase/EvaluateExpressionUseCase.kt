package dev.hyuwah.kalkulator.domain.usecase

import dev.hyuwah.kalkulator.domain.model.EvalResult
import org.mariuszgromada.math.mxparser.Expression

class EvaluateExpressionUseCase(
    val infixToPostfixUseCase: InfixToPostfixUseCase,
    val evaluatePostfixUseCase: EvaluatePostfixUseCase,
) {
    operator fun invoke(input: String, useBuiltInExpressionEvaluator: Boolean = false): EvalResult {
        val adjustedInput = input.replace(",","")
        return if (useBuiltInExpressionEvaluator) {
            try {
                EvalResult.Success(evaluatePostfixUseCase(infixToPostfixUseCase(adjustedInput)))
            } catch (e: Exception) {
                EvalResult.Failed
            }
        } else {
            val expression = Expression(adjustedInput)
            if (expression.checkLexSyntax()) {
                EvalResult.Success(expression.calculate().toString())
            } else {
                EvalResult.Failed
            }
        }
    }
}
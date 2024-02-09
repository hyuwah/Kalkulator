package dev.hyuwah.kalkulator.domain.model

sealed interface CalculatorAction {
    data class Append(val symbol: String) : CalculatorAction
    data object Evaluate : CalculatorAction
    data object Clear : CalculatorAction
    data object Delete : CalculatorAction
}
package dev.hyuwah.kalkulator.domain.model

sealed interface EvalResult {
    data class Success(val result: String) : EvalResult
    data object Failed : EvalResult
}
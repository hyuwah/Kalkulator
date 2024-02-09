package dev.hyuwah.kalkulator.ui.page

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.hyuwah.kalkulator.domain.model.EvalResult
import dev.hyuwah.kalkulator.domain.usecase.EvaluateExpressionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.NumberFormat
import java.util.Locale

data class MainState(
    val input: String = "",
    val cursorPos: Int = 0,
    val result: String = "",
    val isResultError: Boolean = false,
)

sealed interface MainAction {
    data class Append(val symbol: String) : MainAction
    data class UpdateInput(val expression: String, val cursorPos: Int) : MainAction
    data object Clear : MainAction
    data object Delete : MainAction
    data object SaveResult : MainAction
}

class MainViewModel(
    private val evaluateExpressionUseCase: EvaluateExpressionUseCase
) : ViewModel() {

    private var evaluationJob: Job? = null
    var state by mutableStateOf(MainState())

    fun onAction(action: MainAction) {
        when (action) {
            is MainAction.Append -> {
                val newInput = state.input.map { it.toString() }.toMutableList()
                newInput.add(state.cursorPos, action.symbol)
                state = state.copy(
                    input = newInput.joinToString(""),
                    cursorPos = state.cursorPos + action.symbol.length
                )
                evaluate(state.input)
            }

            is MainAction.UpdateInput -> {
                state = state.copy(input = action.expression, cursorPos = action.cursorPos)
            }

            MainAction.Clear -> {
                evaluationJob?.cancel()
                state = state.copy(input = "", result = "", isResultError = false, cursorPos = 0)
            }

            MainAction.Delete -> {
                evaluationJob?.cancel()
                if (state.input.isNotBlank()) {
                    if (state.cursorPos != 0) {
                        val newInput = state.input.toMutableList()
                        newInput.removeAt(state.cursorPos - 1)
                        state = state.copy(
                            input = newInput.joinToString(""),
                            cursorPos = state.cursorPos - 1
                        )
                        if (newInput.isEmpty()) state = state.copy(result = "")
                        evaluate(state.input)
                    }
                } else {
                    state = state.copy(result = "", isResultError = false)
                }
            }

            MainAction.SaveResult -> {
                evaluationJob?.cancel()
                if (state.result.isNotBlank()) {
                    state = state.copy(input = state.result, cursorPos = state.result.length)
                }
            }
        }
    }

    private fun evaluate(expression: String) {
        evaluationJob?.cancel()
        evaluationJob = viewModelScope.launch(Dispatchers.IO) {
            val eval = evaluateExpressionUseCase(expression)
            state = when (eval) {
                EvalResult.Failed -> {
                    state.copy(isResultError = true)
                }

                is EvalResult.Success -> {
                    val formatted = formatResult(eval.result)
                    withContext(Dispatchers.Main) {
                        state.copy(
                            result = formatted,
                            isResultError = false
                        )
                    }
                }
            }
        }
    }

    private fun formatResult(result: String): String {
        val resultDouble = result.toDoubleOrNull()
        return try {
            if (resultDouble != null && (resultDouble >= 999_999_999 || resultDouble <= -999_999_999)) {
                result
            } else {
                NumberFormat.getInstance(Locale.getDefault()).format(resultDouble)
            }
        } catch (e: Exception) {
            result
        }
    }
}
package com.orlove101.android.lab2orlov.ui.operation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class OperationsViewModel: ViewModel() {

    private val _events = Channel<OperationsScreenEvents>()
    val events = _events.receiveAsFlow()
    var state by mutableStateOf(OperationsState())
    var lastResult = ""

    fun handleAction(action: Action) {
        when(action) {
            is Action.Number -> enterNumber(action.number)
            is Action.Delete -> delete()
            is Action.Clear -> state = OperationsState()
            is Action.Operation -> enterOperation(action.operation)
            is Action.Decimal -> enterDecimal()
            is Action.Calculate -> calculate()
        }
    }

    private fun enterOperation(operationType: OperationType) {
        if(state.number1.isNotBlank()) {
            state = state.copy(operationType = operationType)
        }
    }

    private fun calculate() {
        val number1 = state.number1.toDoubleOrNull()
        val number2 = state.number2.toDoubleOrNull()
        if(number1 != null && number2 != null) {
            val result = when(state.operationType) {
                is OperationType.Add -> number1 + number2
                is OperationType.Subtract -> number1 - number2
                is OperationType.Multiply -> number1 * number2
                is OperationType.Divide -> number1 / number2
                null -> return
            }.toString().take(15)
            state = state.copy(
                number1 = result,
                number2 = "",
                operationType = null
            )
            lastResult = result
        }
    }

    private fun delete() {
        when {
            state.number2.isNotBlank() -> state = state.copy(
                number2 = state.number2.dropLast(1)
            )
            state.operationType != null -> state = state.copy(
                operationType = null
            )
            state.number1.isNotBlank() -> state = state.copy(
                number1 = state.number1.dropLast(1)
            )
        }
    }

    private fun enterDecimal() {
        if(state.operationType == null && !state.number1.contains(".") && state.number1.isNotBlank()) {
            state = state.copy(
                number1 = state.number1 + "."
            )
            return
        } else if(!state.number2.contains(".") && state.number2.isNotBlank()) {
            state = state.copy(
                number2 = state.number2 + "."
            )
        }
    }

    private fun enterNumber(number: Int) {
        if(state.operationType == null) {
            if(state.number1.length >= MAX_NUM_LENGTH) {
                return
            }
            state = state.copy(
                number1 = state.number1 + number
            )
            return
        }
        if(state.number2.length >= MAX_NUM_LENGTH) {
            return
        }
        state = state.copy(
            number2 = state.number2 + number
        )
    }

    fun openResult() = viewModelScope.launch {
        _events.send(
            OperationsScreenEvents.OpenResultScreen(lastResult)
        )
    }

    sealed class OperationsScreenEvents {
        data class OpenResultScreen(val result: String) : OperationsScreenEvents()
    }
}
// think that file level constants more efficient that companion object fields.
// Interesting to know Your point of view about this)
private const val MAX_NUM_LENGTH = 8

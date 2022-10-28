package com.orlove101.android.lab2orlov.ui.operation

sealed class Action {
    data class Number(val number: Int): Action()
    object Clear: Action()
    object Delete: Action()
    data class Operation(val operation: OperationType): Action()
    object Calculate: Action()
    object Decimal: Action()
}
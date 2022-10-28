package com.orlove101.android.lab2orlov.ui.operation

sealed class OperationType(val symbol: String) {
    object Add: OperationType("+")
    object Subtract: OperationType("-")
    object Multiply: OperationType("x")
    object Divide: OperationType("/")
}
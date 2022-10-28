package com.orlove101.android.lab2orlov.ui.operation

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.orlove101.android.lab2orlov.ui.navigation.Screen
import com.orlove101.android.lab2orlov.ui.theme.LightGray
import com.orlove101.android.lab2orlov.ui.theme.MediumGray
import com.orlove101.android.lab2orlov.ui.theme.Orange
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun OperationsScreen(
    viewModel: OperationsViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.events
            .onEach { event ->
                when (event) {
                    is OperationsViewModel.OperationsScreenEvents.OpenResultScreen ->  {
                        Log.d("TAG", "OperationsScreen: ${Screen.Result.withArgs(event.result)}")
                        navController.navigate(Screen.Result.withArgs(event.result))
                    }
                }
            }
            .launchIn(this)
    }

    val state = viewModel.state
    val buttonSpacing = 8.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
            .padding(16.dp)
    ) {
        Text(
            text = "To last result!",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .border(3.dp, Color.White)
                .padding(8.dp)
                .clickable { viewModel.openResult() },
            fontSize = 30.sp,
            color = Color.White
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            Text(
                text = state.number1 + (state.operationType?.symbol ?: "") + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                fontWeight = FontWeight.Light,
                fontSize = 80.sp,
                color = Color.White,
                maxLines = 2
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Button(
                    content = "AC",
                    color = LightGray,
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f)
                ) {
                    viewModel.handleAction(Action.Clear)
                }
                Button(
                    content = "Del",
                    color = LightGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Delete)
                }
                Button(
                    content = "/",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Operation(OperationType.Divide))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Button(
                    content = "7",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Number(7))
                }
                Button(
                    content = "8",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Number(8))
                }
                Button(
                    content = "9",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Number(9))
                }
                Button(
                    content = "x",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Operation(OperationType.Multiply))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Button(
                    content = "4",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Number(4))
                }
                Button(
                    content = "5",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Number(5))
                }
                Button(
                    content = "6",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Number(6))
                }
                Button(
                    content = "-",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Operation(OperationType.Subtract))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Button(
                    content = "1",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Number(1))
                }
                Button(
                    content = "2",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Number(2))
                }
                Button(
                    content = "3",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Number(3))
                }
                Button(
                    content = "+",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Operation(OperationType.Add))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                Button(
                    content = "0",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f)
                ) {
                    viewModel.handleAction(Action.Number(0))
                }
                Button(
                    content = ".",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Decimal)
                }
                Button(
                    content = "=",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.handleAction(Action.Calculate)
                }
            }
        }
    }
}

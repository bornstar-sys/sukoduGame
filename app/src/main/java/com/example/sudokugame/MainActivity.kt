package com.example.sudokugame

import android.R.attr.text
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.sudokugame.state.SudokuViewModel
import com.example.sudokugame.ui.theme.SudokuGameTheme
import com.example.sudokugame.userInterface.SudokuBoard

class MainActivity : ComponentActivity() {

    private val viewModel: SudokuViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SudokuGameTheme {
                val gameState by viewModel.gameState.collectAsState()
                val selectedRow by viewModel.selectedRow.collectAsState()
                val selectedColumn by viewModel.selectedColumn.collectAsState()
                Column(
                    modifier = Modifier.fillMaxSize().padding(top =50.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                   ) {
                    Text(text = "Sudoku Game",
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(30.dp),
                        fontWeight = FontWeight.ExtraBold,
                        fontStyle = FontStyle.Italic,
                        fontSize = 30.sp,
                        color = Color.Red,
                        style = TextStyle(shadow = Shadow(color = Color.Red, blurRadius = 3f))
                    )
                    SudokuBoard(
                        board = gameState.board,
                        selectedRow = selectedRow,
                        selectedColumn = selectedColumn,
                        onCellTapped = { row, col ->
                            viewModel.updateSelectedCell(row, col)
                        },
                        modifier = Modifier.fillMaxSize().padding(start = 20.dp)
                    )
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SudokuGameTheme {
        Greeting("Android")
    }
}
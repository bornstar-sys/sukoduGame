package com.example.sudokugame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sudokugame.domain.SudokuGame
import com.example.sudokugame.ui.theme.SudokuGameTheme
import com.example.sudokugame.userInterface.SudokuBoard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SudokuGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val samplePuzzle = listOf(
                        listOf(5, 3, 0, 0, 7, 0, 0, 0, 0),
                        listOf(6, 0, 0, 1, 9, 5, 0, 0, 0),
                        listOf(0, 9, 8, 0, 0, 0, 0, 6, 0),
                        listOf(8, 0, 0, 0, 6, 0, 0, 0, 3),
                        listOf(4, 0, 0, 8, 0, 3, 0, 0, 1),
                        listOf(7, 0, 0, 0, 2, 0, 0, 0, 6),
                        listOf(0, 6, 0, 0, 0, 0, 2, 8, 0),
                        listOf(0, 0, 0, 4, 1, 9, 0, 0, 5),
                        listOf(0, 0, 0, 0, 8, 0, 0, 7, 9)
                    )
                    // Create an instance of SudokuGame
                    val sudokuGame = SudokuGame(samplePuzzle)
                    // Call the SudokuBoard composable with the board data
                    SudokuBoard(
                        board = sudokuGame.board,
                        modifier = Modifier.padding(innerPadding)
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
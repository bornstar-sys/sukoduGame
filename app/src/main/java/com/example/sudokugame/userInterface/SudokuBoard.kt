package com.example.sudokugame.userInterface

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sudokugame.domain.SudokuCell

@Composable
fun SudokuBoard(board: List<List<SudokuCell>>, modifier: Modifier = Modifier) {
    //TODO: Implement Sudoku Board
    Column(modifier = modifier) {
        repeat(9) { rowIndex ->// This creates 9 rows
            Row {
                // We'll create 9 cells here later
                repeat(9) { columnIndex ->// This creates 9 cells in each row
                    // Get the specific cell for this row and column
                    val cell = board[rowIndex][columnIndex]
                    // Pass its value to our cell view
                    SudokuCellView(value = cell.value)
                }
            }
        }
    }
}


@Composable
fun SudokuCellView(value: Int) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Black)
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        if (value != 0) {
            Text(text = value.toString())
        }
    }
}
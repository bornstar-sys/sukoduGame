package com.example.sudokugame.userInterface

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sudokugame.domain.SudokuCell

@Composable
fun SudokuBoard(board: List<List<SudokuCell>>,
                selectedRow: Int?,
                selectedColumn: Int?,
                onCellTapped: (Int, Int) -> Unit,
                modifier: Modifier = Modifier) {
    //TODO: Implement Sudoku Board
    Column(modifier = modifier) {
        repeat(9) { rowIndex ->// This creates 9 rows
            Row {
                // We'll create 9 cells here later
                repeat(9) { columnIndex ->// This creates 9 cells in each row
                    // Get the specific cell for this row and column
                    val cell = board[rowIndex][columnIndex]
                    val isSelected = selectedRow == rowIndex && selectedColumn == columnIndex
                    // Pass its value to our cell view
                    SudokuCellView(
                        value = cell.value,
                        onTap = { onCellTapped(rowIndex, columnIndex) },
                        isSelected = isSelected
                    )
                }
            }
        }
    }
}


@Composable
fun SudokuCellView(value: Int, onTap: () -> Unit, isSelected: Boolean = false){
    val backgroundColor = if (isSelected) Color.Yellow else Color.White
    Box(
        modifier = Modifier
            .border(1.dp, Color.Black)
            .size(40.dp)
            .background(backgroundColor)
            .clickable {
                //TODO: Implement clickable cell
                onTap()
            },
        contentAlignment = Alignment.Center
    ) {
        if (value != 0) {
            Text(text = value.toString())
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SudokuBoardPreview() {
    SudokuBoard(board = List(9) { row -> List(9) { column -> SudokuCell(row, false) } },
        selectedRow = 0,
        selectedColumn = 0,
        onCellTapped = { _, _ -> })
}
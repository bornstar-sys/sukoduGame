package com.example.sudokugame.userInterface

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sudokugame.domain.SudokuCell
import com.example.sudokugame.domain.SudokuGame

@Composable
fun SudokuBoard(
    board: List<List<SudokuCell>>,
    selectedRow: Int?,
    selectedColumn: Int?,
    onCellTapped: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        repeat(9) { rowIndex ->
            Row {
                repeat(9) { columnIndex ->
                    val cell = board[rowIndex][columnIndex]
                    val isSelected = selectedRow == rowIndex && selectedColumn == columnIndex

                    SudokuCellView(
                        value = cell.value,
                        // ✅ Pass the isReadOnly property down
                        isReadOnly = cell.isReadOnly,
                        isSelected = isSelected,
                        onTap = { onCellTapped(rowIndex, columnIndex) }
                    )
                }
            }
        }
    }
}


@Composable
fun SudokuCellView(
    value: Int,
    isReadOnly: Boolean, // ✅ New parameter
    isSelected: Boolean,
    onTap: () -> Unit
){
    // ✅ Updated logic to determine background color
    val backgroundColor = when {
        isReadOnly -> Color(0xFFC9E4C5) // A light green for read-only cells
        isSelected -> Color.Yellow
        else -> Color.White
    }

    Box(
        modifier = Modifier
            .border(1.dp, Color.Black)
            .size(40.dp)
            .background(backgroundColor)
            .clickable { onTap() },
        contentAlignment = Alignment.Center
    ) {
        if (value != 0) {
            Text(text = value.toString())
        }
    }
}

@Composable
fun NumberPad(
    onNumberClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly // Spreads items out
    ) {
        (1..9).forEach { number ->
            Text(
                text = number.toString(),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clickable { onNumberClick(number) } // Makes the text clickable
                    .padding(16.dp) // Increases the clickable area and adds space
            )
        }
    }
}
@Preview(showSystemUi = true)
@Composable
fun SudokuBoardPreview() {
    // A sample puzzle for a more realistic preview
    val samplePuzzle = listOf(
        listOf(5, 3, 0, 0, 7, 0, 0, 0, 0),
        // ... (add the rest of the puzzle data)
        listOf(6, 0, 0, 1, 9, 5, 0, 0, 0),
        listOf(0, 9, 8, 0, 0, 0, 0, 6, 0),
        listOf(8, 0, 0, 0, 6, 0, 0, 0, 3),
        listOf(4, 0, 0, 8, 0, 3, 0, 0, 1),
        listOf(7, 0, 0, 0, 2, 0, 0, 0, 6),
        listOf(0, 6, 0, 0, 0, 0, 2, 8, 0),
        listOf(0, 0, 0, 4, 1, 9, 0, 0, 5),
        listOf(0, 0, 0, 0, 8, 0, 0, 7, 9)
    )
    val game = SudokuGame.fromPuzzle(samplePuzzle)// Create a game instance
    SudokuBoard(
        board = game.board, // Use the game's board
        selectedRow = 0,
        selectedColumn = 0,
        onCellTapped = { _, _ -> }
    )
}
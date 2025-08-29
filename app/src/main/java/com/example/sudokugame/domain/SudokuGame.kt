package com.example.sudokugame.domain

// A data class for a single cell
data class SudokuCell(val value: Int, val isReadOnly: Boolean)

// The main class for our game logic
class SudokuGame(puzzle: List<List<Int>>) {

    // The property holding our 9x9 grid
    val board: List<List<SudokuCell>>

    init {
        board = puzzle.map { row ->
            row.map { cellValue ->
                SudokuCell(
                    value = cellValue,
                    isReadOnly = cellValue != 0
                )
            }
        }
    }
}
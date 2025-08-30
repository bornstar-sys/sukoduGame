package com.example.sudokugame.domain

data class SudokuCell(val value: Int, val isReadOnly: Boolean)

class SudokuGame private constructor(val board: List<List<SudokuCell>>) {

    companion object {
        // Factory function to create a game from a simple puzzle
        fun fromPuzzle(puzzle: List<List<Int>>): SudokuGame {
            val board = puzzle.map { row ->
                row.map { cellValue ->
                    SudokuCell(
                        value = cellValue,
                        isReadOnly = cellValue != 0
                    )
                }
            }
            return SudokuGame(board)
        }

        // Factory function to create a game from an existing board
        fun fromBoard(board: List<List<SudokuCell>>): SudokuGame {
            return SudokuGame(board)
        }
    }
}
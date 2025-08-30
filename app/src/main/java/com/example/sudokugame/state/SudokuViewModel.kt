package com.example.sudokugame.state

import androidx.lifecycle.ViewModel
import com.example.sudokugame.domain.SudokuGame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SudokuViewModel : ViewModel() {
    private val _gameState = MutableStateFlow(SudokuGame.fromPuzzle(samplePuzzle))
    val gameState: StateFlow<SudokuGame> = _gameState.asStateFlow()

    private val _selectedRow = MutableStateFlow<Int?>(null)
    val selectedRow: StateFlow<Int?> = _selectedRow.asStateFlow()

    private val _selectedColumn = MutableStateFlow<Int?>(null)
    val selectedColumn: StateFlow<Int?> = _selectedColumn.asStateFlow()

    fun updateSelectedCell(row: Int, col: Int) {
        // If the same cell is tapped again, deselect it
        if (_selectedRow.value == row && _selectedColumn.value == col) {
            _selectedRow.value = null
            _selectedColumn.value = null
        } else {
            _selectedRow.value = row
            _selectedColumn.value = col
        }
    }

    fun onNumberInput(number: Int) {
        val row = _selectedRow.value
        val col = _selectedColumn.value
        if (row == null || col == null) return

        val currentGame = _gameState.value
        val currentBoard = currentGame.board
        val cell = currentBoard[row][col]

        if (cell.isReadOnly) return

        // Create a deep, mutable copy of the board
        val newBoard = currentBoard.map { it.toMutableList() }.toMutableList()
        // Update the cell's value in the new board
        newBoard[row][col] = cell.copy(value = number)

        // Update the game state with the new board using the secondary constructor
        _gameState.value = SudokuGame.fromBoard(newBoard.map { it.toList() })
    }

    companion object {
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
    }
}
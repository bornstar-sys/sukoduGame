package com.example.sudokugame.state

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.ViewModel
import com.example.sudokugame.domain.SudokuGame
import kotlinx.coroutines.flow.MutableStateFlow

class SudokuViewModel: ViewModel() {
    private val _gameState = MutableStateFlow(SudokuGame(samplePuzzle))
    val gameState: StateFlow<SudokuGame> = _gameState.asStateFlow()

    private val _selectedRow = MutableStateFlow<Int?>(null)
    val selectedRow: StateFlow<Int?> = _selectedRow.asStateFlow()

    private val _selectedColumn = MutableStateFlow<Int?>(null)
    val selectedColumn: StateFlow<Int?> = _selectedColumn.asStateFlow()

    fun updateSelectedCell(row: Int, col: Int){
        _selectedRow.value = row
        _selectedColumn.value = col
    }

    companion object{
        val samplePuzzle = listOf(
            // Your sample puzzle data here
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
package com.example.appchess

interface ChessDelegate {
    fun pieceAtInterface(col: Int, row: Int): ChessPiece?
}
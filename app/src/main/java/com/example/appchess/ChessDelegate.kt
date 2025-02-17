package com.example.appchess

interface ChessDelegate {
    fun pieceAtInterface(col: Int, row: Int): ChessPiece?

    fun movePiece(colFrom: Int, rowFrom: Int, colTo: Int, rowTo: Int)

}
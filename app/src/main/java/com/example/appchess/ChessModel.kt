package com.example.appchess

import android.util.Log

class ChessModel {

    private val TAG = "ChessModel1"

    var piecesBox = mutableSetOf<ChessPiece>()

    init {
        reset()
//        Log.d(TAG, "${piecesBox.size}")
//        movePiece(0,0, 1,7)
//        movePiece(1,7, 3,3)
        movePiece(0, 0, 0, 1)
//        Log.d(TAG, "${piecesBox.size}")
        Log.d(TAG, toString())
    }

    fun reset() {
        piecesBox.removeAll(piecesBox)
        for (i in 0..1) {
            piecesBox.add(
                ChessPiece(
                    0 + 7 * i,
                    0,
                    ChessPlayer.WHITE,
                    ChessRank.ROOK,
                    R.drawable.rook_white
                )
            )
            piecesBox.add(
                ChessPiece(
                    0 + 7 * i,
                    7,
                    ChessPlayer.BLACK,
                    ChessRank.ROOK,
                    R.drawable.rook_black
                )
            )
        }
        for (i in 0..1) {
            piecesBox.add(
                ChessPiece(
                    1 + 5 * i,
                    0,
                    ChessPlayer.WHITE,
                    ChessRank.KNIGHT,
                    R.drawable.knight_white
                )
            )
            piecesBox.add(
                ChessPiece(
                    1 + 5 * i,
                    7,
                    ChessPlayer.BLACK,
                    ChessRank.KNIGHT,
                    R.drawable.knight_black
                )
            )
        }
        for (i in 0..1) {
            piecesBox.add(
                ChessPiece(
                    2 + 3 * i,
                    0,
                    ChessPlayer.WHITE,
                    ChessRank.BISHOP,
                    R.drawable.bishop_white
                )
            )
            piecesBox.add(
                ChessPiece(
                    2 + 3 * i,
                    7,
                    ChessPlayer.BLACK,
                    ChessRank.BISHOP,
                    R.drawable.bishop_black
                )
            )
        }
        for (i in 0..7) {
            piecesBox.add(
                ChessPiece(
                    i,
                    1,
                    ChessPlayer.WHITE,
                    ChessRank.PAWN,
                    R.drawable.pawn_white
                )
            )
            piecesBox.add(
                ChessPiece(
                    i,
                    6,
                    ChessPlayer.BLACK,
                    ChessRank.PAWN,
                    R.drawable.pawn_black
                )
            )
        }
        piecesBox.add(ChessPiece(3, 0, ChessPlayer.WHITE, ChessRank.QUEEN, R.drawable.queen_white))
        piecesBox.add(ChessPiece(3, 7, ChessPlayer.BLACK, ChessRank.QUEEN, R.drawable.queen_black))
        piecesBox.add(ChessPiece(4, 0, ChessPlayer.WHITE, ChessRank.KING, R.drawable.king_white))
        piecesBox.add(ChessPiece(4, 7, ChessPlayer.BLACK, ChessRank.KING, R.drawable.king_black))
    }

    fun movePiece(colFrom: Int, rowFrom: Int, colTo: Int, rowTo: Int) {
        val pieceFrom = pieceAt(colFrom, rowFrom) ?: return
        if (colFrom == colTo && rowFrom == rowTo) return
        val pieceTo = pieceAt(colTo, rowTo)
        pieceTo?.let {
            if (it.player == pieceFrom.player) return
            piecesBox.remove(it)
        }
        pieceFrom.let {
            piecesBox.remove(it)
        }
        piecesBox.add(ChessPiece(colTo, rowTo, pieceFrom.player, pieceFrom.rank, pieceFrom.resId))
//        pieceFrom.col = colTo
//        pieceFrom.row = rowTo
        Log.d(TAG, toString())
    }

    fun pieceAt(col: Int, row: Int): ChessPiece? {
        for (piece in piecesBox) {
            if (piece.col == col && piece.row == row) {
                return piece
            }
        }
        return null
    }

    override fun toString(): String {
        var desc = ""
        for (row in 7 downTo 0) {
            desc += "$row"
            for (col in 0..7) {
                val piece = pieceAt(col, row)
                if (piece == null) {
                    desc += " ."
                } else {
                    desc += " "
                    val isWhite = piece.player == ChessPlayer.WHITE
                    desc += when (piece.rank) {
                        ChessRank.ROOK -> {
                            if (isWhite) "r" else "R"
                        }

                        ChessRank.KNIGHT -> {
                            if (isWhite) "n" else "N"
                        }

                        ChessRank.BISHOP -> {
                            if (isWhite) "b" else "B"
                        }

                        ChessRank.QUEEN -> {
                            if (isWhite) "q" else "Q"
                        }

                        ChessRank.KING -> {
                            if (isWhite) "k" else "K"
                        }

                        ChessRank.PAWN -> {
                            if (isWhite) "p" else "P"
                        }
                    }
                }

            }
            desc += "\n"
        }
        desc += "  0 1 2 3 4 5 6 7\n"
        return desc
    }
}
package com.example.appchess

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ChessDelegate {

    private val TAG = "MainActivity1"

    private var chessModel = ChessModel()

    val chessView: ChessView by lazy { findViewById(R.id.chess_view) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chessView.chessDelegate = this
        Log.d(TAG, "$chessModel")

    }

    override fun pieceAtInterface(col: Int, row: Int): ChessPiece? {
        return chessModel.pieceAt(col, row)
    }

    override fun movePiece(colFrom: Int, rowFrom: Int, colTo: Int, rowTo: Int) {
        chessModel.movePiece(colFrom, rowFrom, colTo, rowTo)
        chessView.invalidate()
    }
}
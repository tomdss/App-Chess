package com.example.appchess

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ChessDelegate {

    private val TAG = "MainActivity1"

    private var chessModel = ChessModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val chessView = findViewById<ChessView>(R.id.chess_view)
        chessView.chessDelegate = this
        Log.d(TAG, "$chessModel")
    }

    override fun pieceAtInterface(col: Int, row: Int): ChessPiece? {
        return chessModel.pieceAt(col, row)
    }
}
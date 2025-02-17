package com.example.appchess

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.io.PrintWriter
import java.net.Socket
import java.util.Scanner
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity(), ChessDelegate {

    private val TAG = "MainActivity1"

    private var chessModel = ChessModel()

    private val chessView: ChessView by lazy { findViewById(R.id.chess_view) }
    private val resetButton: Button by lazy { findViewById(R.id.btn_reset) }
    private val listenButton: Button by lazy { findViewById(R.id.btn_listen) }
    private val connectButton: Button by lazy { findViewById(R.id.btn_connect) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chessView.chessDelegate = this
        Log.d(TAG, "$chessModel")
        resetButton.setOnClickListener {
            chessModel.reset()
            chessView.invalidate()
        }
        listenButton.setOnClickListener {
            Log.d(TAG, "listen")
        }
        connectButton.setOnClickListener {
            Log.d(TAG, "connect")
/*            Executors.newSingleThreadExecutor().execute {
                val socket = Socket("localhost", 50000)
                val scanner = Scanner(socket.getInputStream())
                val printWriter = PrintWriter(socket.getOutputStream())
                while (scanner.hasNextLine()) {
                    Log.d(TAG, "scanner ${scanner.nextLine()}")
                }
            }*/
        }
    }

    override fun pieceAtInterface(col: Int, row: Int): ChessPiece? {
        return chessModel.pieceAt(col, row)
    }

    override fun movePiece(colFrom: Int, rowFrom: Int, colTo: Int, rowTo: Int) {
        chessModel.movePiece(colFrom, rowFrom, colTo, rowTo)
        chessView.invalidate()
    }
}
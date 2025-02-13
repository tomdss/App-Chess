package com.example.appchess

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.math.min

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = "ChessView1"

    private var originX = 20f
    private var originY = 200f
    private var cellSide = 150f
    private val paint = Paint()
    private val darkColor = Color.rgb(105, 101, 85)
    private val lightColor = Color.rgb(190, 195, 165)
    private val scaleFactor = 0.9f

    var chessDelegate: ChessDelegate? = null

    private val imgResIds = setOf(
        R.drawable.queen_black,
        R.drawable.queen_white,
        R.drawable.rook_black,
        R.drawable.rook_white,
        R.drawable.knight_black,
        R.drawable.knight_white,
        R.drawable.bishop_black,
        R.drawable.bishop_white,
        R.drawable.pawn_black,
        R.drawable.pawn_white,
        R.drawable.king_black,
        R.drawable.king_white
    )
    private val bitmaps = mutableMapOf<Int, Bitmap>()

    init {
        loadBitmaps()
    }

    override fun onDraw(canvas: Canvas) {
        Log.d(TAG, "onDraw canvas w = ${canvas.width}, h = ${canvas.height}")
        val chessSideMin = min(canvas.width, canvas.height) * scaleFactor
        cellSide = chessSideMin / 8
        originX = (canvas.width - chessSideMin) / 2
        originY = (canvas.height - 8 * cellSide) / 2
        drawChessBoard(canvas)
        drawPieces(canvas)
    }

    private fun drawPieces(canvas: Canvas) {
//        val chessModel = ChessModel()
//        chessModel.reset()
        for (col in 0..7) {
            for (row in 0..7) {
                chessDelegate?.pieceAtInterface(col, row)?.let {
                    drawPieceAt(canvas, col, row, it.resId)
                }
            }
        }
    }

    private fun drawPieceAt(canvas: Canvas, col: Int, row: Int, drawableResId: Int) {
        val bitmapPiece = bitmaps[drawableResId] ?: return
        canvas.drawBitmap(
            bitmapPiece,
            null,
            RectF(
                originX + cellSide * col,
                originY + cellSide * (7 - row),
                originX + cellSide * (col + 1),
                originY + cellSide * (8 - row)
            ),
            null
        )
    }

    private fun loadBitmaps() {
        imgResIds.forEach {
            bitmaps[it] = BitmapFactory.decodeResource(resources, it)
        }
    }

    private fun drawChessBoard(canvas: Canvas) {
        for (col in 0..7) {
            for (row in 0..7) {
                drawSquareAt(canvas, col, row, (row + col) % 2 == 1)
            }
        }
    }

    private fun drawSquareAt(canvas: Canvas, col: Int, row: Int, isDark: Boolean) {
        paint.color = if (isDark) darkColor else lightColor
        canvas.drawRect(
            originX + cellSide * row,
            originY + cellSide * col,
            originX + cellSide * (row + 1),
            originY + cellSide * (col + 1),
            paint
        )
    }


}
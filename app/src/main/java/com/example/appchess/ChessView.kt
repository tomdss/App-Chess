package com.example.appchess

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private val originX = 20f
    private val originY = 200f
    private val cellSide = 130f
    private val paint = Paint()
    private val darkColor = Color.rgb(105, 101, 85)
    private val lightColor = Color.rgb(190, 195, 165)

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
        drawBoard(canvas)
        drawPieces(canvas)
    }

    private fun drawPieces(canvas: Canvas) {
        val chessModel = ChessModel()
        chessModel.reset()
        for (col in 0..7) {
            for (row in 0..7) {
                val piece = chessModel.pieceAt(col, row)
                if (piece != null) {
                    drawPieceAt(canvas, col, row, piece.resId)
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

    private fun drawBoard(canvas: Canvas) {
        for (j in 0..7) {
            for (i in 0..7) {
                paint.color = if ((i + j) % 2 == 1) darkColor else lightColor
                canvas.drawRect(
                    originX + cellSide * i,
                    originY + cellSide * j,
                    originX + cellSide * (i + 1),
                    originY + cellSide * (j + 1),
                    paint
                )
            }
        }
    }


}
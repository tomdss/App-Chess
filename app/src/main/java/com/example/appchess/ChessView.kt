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
import android.view.MotionEvent
import android.view.View
import kotlin.math.min

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private val TAG = "ChessView1"

    private var originX = 20f
    private var originY = 200f
    private var cellSide = 150f
    private val paint = Paint()
    private val darkColor = Color.parseColor("#EEEEEE")
    private val lightColor = Color.parseColor("#BBBBBB")
    private val scaleFactor = 0.9f

    private var fromCol = -1
    private var fromRow = -1

    private var fromMovingCol = -1f
    private var fromMovingRow = -1f

    var chessDelegate: ChessDelegate? = null

    var movingPieceBitmap: Bitmap? = null

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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure w = $width, h = $height")
        val minMeasure = min(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(minMeasure, minMeasure)
    }

    override fun onDraw(canvas: Canvas) {
        Log.d(TAG, "onDraw canvas w = $width, h = $height")
        val chessSideMin = min(width, height) * scaleFactor
        cellSide = chessSideMin / 8
        originX = (width - chessSideMin) / 2
        originY = (height - 8 * cellSide) / 2
        drawChessBoard(canvas)
        drawPieces(canvas)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if(event == null) return false
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                fromCol = ((event.x - originX) / cellSide).toInt()
                fromRow = 7 - ((event.y - originY) / cellSide).toInt()
                Log.d(TAG, "onTouchEvents ACTION_DOWN $fromCol, $fromRow")
                chessDelegate?.pieceAtInterface(fromCol, fromRow)?.let {
                    movingPieceBitmap = bitmaps[it.resId]
                }
            }

            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG, "onTouchEvent ACTION_MOVE ${event.x}, ${event.y}")
                fromMovingCol = event.x
                fromMovingRow = event.y
                invalidate()
            }

            MotionEvent.ACTION_UP -> {
                val col = ((event.x - originX) / cellSide).toInt()
                val row = 7 -((event.y - originY) / cellSide).toInt()
                Log.d(TAG, "onTouchEvents ACTION_UP $col, $row")
                chessDelegate?.movePiece(fromCol, fromRow, col, row)
                movingPieceBitmap = null
            }
        }
        return true
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
        movingPieceBitmap?.let {
            canvas.drawBitmap(
                it,
                null,
                RectF(
                    fromMovingCol - cellSide/2,
                    fromMovingRow - cellSide/2,
                    fromMovingCol + cellSide/2,
                    fromMovingRow + cellSide/2
                ),
                null
            )
        }

//        chessDelegate?.pieceAtInterface(fromCol, fromRow)?.let {
//            val bitmap = bitmaps[it.resId] ?: return
//            canvas.drawBitmap(
//                bitmap,
//                null,
//                RectF(
//                    fromMovingCol - cellSide/2,
//                    fromMovingRow - cellSide/2,
//                    fromMovingCol + cellSide/2,
//                    fromMovingRow + cellSide/2
//                ),
//                null
//            )
//        }
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
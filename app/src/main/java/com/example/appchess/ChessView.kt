package com.example.appchess

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class ChessView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var originX = 20f
    private var originY = 200f
    private var cellSide = 130f

    override fun onDraw(canvas: Canvas) {
        val paint = Paint()
        for (j in 0..7) {
            for (i in 0..7) {
                paint.color = if((i+j)%2 == 1) Color.DKGRAY else Color.LTGRAY
                canvas.drawRect(
                    originX + (cellSide * i),
                    originY + (cellSide * j),
                    originX + (cellSide * (i + 1)),
                    originY + (cellSide * (j + 1)),
                    paint
                )
            }
        }
    }

//    override fun onDraw(canvas: Canvas) {
//        val paint = Paint()
//        for (j in 0..3) {
//            for (i in 0..3) {
//                paint.color = Color.LTGRAY
//                canvas.drawRect(
//                    originX + (cellSide * i * 2),
//                    originY + (cellSide * j * 2),
//                    originX + (cellSide * (i * 2 + 1)),
//                    originY + (cellSide * (j * 2 + 1)),
//                    paint
//                )
//                canvas.drawRect(
//                    originX + (cellSide * (i * 2 + 1)),
//                    originY + (cellSide * (j * 2 + 1)),
//                    originX + (cellSide * (i * 2 + 2)),
//                    originY + (cellSide * (j * 2 + 2)),
//                    paint
//                )
//
//                paint.color = Color.DKGRAY
//                canvas.drawRect(
//                    originX + (cellSide * (i * 2 + 1)),
//                    originY + (cellSide * j * 2),
//                    originX + (cellSide * (i * 2 + 2)),
//                    originY + (cellSide * (j * 2 + 1)),
//                    paint
//                )
//                canvas.drawRect(
//                    originX + (cellSide * (i * 2)),
//                    originY + (cellSide * (j * 2 + 1)),
//                    originX + (cellSide * (i * 2 + 1)),
//                    originY + (cellSide * (j * 2 + 2)),
//                    paint
//                )
//            }
//        }
//
//    }

}
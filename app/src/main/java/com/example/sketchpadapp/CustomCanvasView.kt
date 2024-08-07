package com.example.sketchpadapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.Stack

class CustomCanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var paint: Paint = Paint()
    private var path = Path()
    private val paths = Stack<Path>()
    private val undonePaths = Stack<Path>()
    private val pathList = ArrayList<Path>() // List of paths drawn
    private val colorList = ArrayList<Int>() // List of colors for each path
    private var currentBrush = Color.BLACK // Current brush color

    init {
        paint.color = Color.BLACK
        paint.isAntiAlias = true
        paint.strokeWidth = 8f
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Draw all paths with their respective colors
        for (i in paths.indices) {
            paint.color = colorList[i]
            canvas.drawPath(paths[i], paint)
        }

        // Draw the current path being drawn
        paint.color = currentBrush
        canvas.drawPath(path, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                path = Path()
                path.moveTo(x, y)
                paths.push(path)
                colorList.add(currentBrush)
                undonePaths.clear()
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(x, y)
                invalidate() // Request a redraw
                return true
            }

            else -> return false
        }
    }

    fun changeColor(color: Int) {
        currentBrush = color
    }

    fun undo() {
        if (paths.isNotEmpty()) {
            undonePaths.push(paths.pop())
            colorList.removeAt(colorList.size - 1)
            invalidate()
        }
    }

    fun redo() {
        if (undonePaths.isNotEmpty()) {
            paths.push(undonePaths.pop())
            colorList.add(currentBrush)
            invalidate()
        }
    }

    fun currentColor(color: Int) {
        changeColor(color)
        path = Path() // Reset the path
    }

    // Clear all paths
    fun clearAll() {
        paths.clear()
        undonePaths.clear()
        pathList.clear()
        colorList.clear()
        path.reset() // Reset the current path
        invalidate()
    }
}

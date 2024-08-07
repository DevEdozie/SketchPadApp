package com.example.sketchpadapp

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.sketchpadapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var canvasView: CustomCanvasView
    private lateinit var redColor: ImageView
    private lateinit var blueColor: ImageView
    private lateinit var blackColor: ImageView
    private lateinit var eraserButton: ImageView
    private lateinit var undoButton: ImageView
    private lateinit var redoButton: ImageView
    private lateinit var clearButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUI()
        setUpButtonListeners()
    }

    private fun setUpUI() {
        canvasView = binding.canvasView
        redColor = binding.redColor
        blueColor = binding.blueColor
        blackColor = binding.blackColor
        eraserButton = binding.eraser
        undoButton = binding.undo
        redoButton = binding.redo
        clearButton = binding.clear
    }

    private fun setUpButtonListeners() {
        redColor.setOnClickListener {
            // Example color change
            canvasView.currentColor(Color.RED)
        }
        blueColor.setOnClickListener {
            // Example color change
            canvasView.currentColor(Color.BLUE)
        }
        blackColor.setOnClickListener {
            // Example color change
            canvasView.currentColor(Color.BLACK)
        }

        eraserButton.setOnClickListener {
            // Assuming background is white, use this color for erasing
            canvasView.currentColor(Color.WHITE)
        }

        undoButton.setOnClickListener {
            canvasView.undo()
        }

        redoButton.setOnClickListener {
            canvasView.redo()
        }

        clearButton.setOnClickListener {
            canvasView.clearAll()
        }
    }
}

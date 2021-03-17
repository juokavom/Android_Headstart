package com.example.drawing

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.core.view.get
import com.example.drawing.databinding.ActivityMainBinding
import com.example.drawing.databinding.DialogBrushSizeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var binding_brush: DialogBrushSizeBinding
    private var mImageButtonCurrentPaint: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.drawingView.setSizeForBrush(20.toFloat())
        mImageButtonCurrentPaint = binding.llPaintColors[2] as ImageButton
        mImageButtonCurrentPaint!!.setImageDrawable(
            ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
        )

        binding.ibBrush.setOnClickListener{
            showBrushSizeChooserDialog()
        }
    }

    fun paintClicked(view: View){
        if(view !== mImageButtonCurrentPaint){
            val imageButton = view as ImageButton
            val colorTag = imageButton.tag.toString()
            binding.drawingView.setColor(colorTag)
            imageButton.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_pressed)
            )
            mImageButtonCurrentPaint!!.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.pallet_normal)
            )
            mImageButtonCurrentPaint = view
        }
    }

    private fun showBrushSizeChooserDialog(){
        binding_brush = DialogBrushSizeBinding.inflate(layoutInflater)
        val brushDialog = Dialog(this)
        brushDialog.setContentView(binding_brush.root)
        brushDialog.setTitle("Brush size: ")
        binding_brush.ibSmallBrush.setOnClickListener{
            binding.drawingView.setSizeForBrush(10.toFloat())
            brushDialog.dismiss()
        }
        binding_brush.ibMediumBrush.setOnClickListener{
            binding.drawingView.setSizeForBrush(20.toFloat())
            brushDialog.dismiss()
        }
        binding_brush.ibLargeBrush.setOnClickListener{
            binding.drawingView.setSizeForBrush(30.toFloat())
            brushDialog.dismiss()
        }
        brushDialog.show()
    }
}
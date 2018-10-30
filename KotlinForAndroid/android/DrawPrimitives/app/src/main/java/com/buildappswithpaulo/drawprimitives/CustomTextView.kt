package com.buildappswithpaulo.drawprimitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by paulodichone on 2/10/18.
 */
class CustomTextView(context: Context?, attrs: AttributeSet?)
    : TextView(context, attrs) {

    lateinit var pen: Paint


    override fun draw(canvas: Canvas?) {

        pen = Paint(Paint.ANTI_ALIAS_FLAG)
        pen.color = Color.parseColor("blue")
        pen.textSize = 66f
        pen.textAlign = Paint.Align.CENTER


        canvas!!.drawText("Hello World", (measuredWidth/ 2).toFloat(),
                (measuredHeight / 2).toFloat(), pen)

        canvas.save()
        super.draw(canvas)


    }

}
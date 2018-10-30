package com.buildappswithpaulo.drawprimitives

import android.content.Context
import android.graphics.*
import android.view.View

/**
 * Created by paulodichone on 2/10/18.
 */
class Drawing(context: Context?) : View(context) {
    private lateinit var brush: Paint
    private lateinit var lineBrush: Paint


    override fun draw(canvas: Canvas?) {

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.paulo)

        canvas!!.drawBitmap(bitmap,(measuredWidth/2).toFloat() - (bitmap.width/2),
                (measuredHeight/2).toFloat() - (bitmap.height/2), null)









//        brush = Paint(Paint.ANTI_ALIAS_FLAG)
//        lineBrush = Paint(Paint.ANTI_ALIAS_FLAG)
//        lineBrush.strokeWidth = 13f
//
//        brush.color = Color.parseColor("red")
//        lineBrush.color = Color.MAGENTA
//
//
//        canvas!!.drawCircle((measuredWidth/2).toFloat(), (measuredHeight/2).toFloat(),
//                69f, brush )
//        canvas!!.drawLine(0.0f, 0.0f, (measuredWidth/2).toFloat(),
//                (measuredHeight/2).toFloat(), lineBrush)

        canvas.save()
        super.draw(canvas)
    }


}
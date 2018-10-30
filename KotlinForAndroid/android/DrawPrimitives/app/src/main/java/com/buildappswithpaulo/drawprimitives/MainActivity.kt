package com.buildappswithpaulo.drawprimitives

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val drawing = Drawing(this)
        val customTextView = CustomTextView(this, null)

        drawing.setOnClickListener {
            Toast.makeText(this, "Hello there", Toast.LENGTH_LONG)
                    .show()
        }

        setContentView(customTextView)
    }
}

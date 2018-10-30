package imageviewappkotlinandroid.gohool.com.imagefilterapp

import android.graphics.Color
import android.graphics.PorterDuff
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val colorsArray = arrayOf(Color.BLACK, Color.CYAN, Color.BLUE, Color.GREEN, Color.DKGRAY
                , Color.GRAY, Color.RED, Color.TRANSPARENT, Color.WHITE)

        var porterModes = arrayListOf(PorterDuff.Mode.OVERLAY, PorterDuff.Mode.DST_ATOP, PorterDuff.Mode.DARKEN)

        catImageView.setOnClickListener {

            catImageView.setColorFilter(colorsArray[getRandom(colorsArray.size)], porterModes[getRandom(porterModes.size)])

            //println(getRandom(4))


        }


    }

    fun getRandom(arraySize: Int): Int {

        var rand = Random()
        var randomNum = rand.nextInt(arraySize)

        return randomNum

    }
}

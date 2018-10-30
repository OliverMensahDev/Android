package randombgappkotlinandroid.gohool.com.randombackground

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var colorArray = arrayOf(Color.BLACK, Color.WHITE, Color.TRANSPARENT, Color.RED,
                 Color.GRAY, Color.GREEN, Color.CYAN, Color.LTGRAY, Color.MAGENTA, Color.YELLOW)

        tapButton.setOnClickListener {

            mainView.setBackgroundColor(colorArray[getRandom(colorArray.size)])

        }
    }


    fun getRandom(arraySize: Int): Int {

        var rand = Random()
        var randomNum = rand.nextInt(arraySize)

        return randomNum

    }
}

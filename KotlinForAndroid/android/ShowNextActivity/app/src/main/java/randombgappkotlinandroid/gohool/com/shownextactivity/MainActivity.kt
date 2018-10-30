package randombgappkotlinandroid.gohool.com.shownextactivity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.widget.VideoView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goSecondId.setOnClickListener {

            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("name", "Bonni")
            intent.putExtra("char", 'C')
            intent.putExtra("int", 23)

            startActivityForResult(intent, REQUEST_CODE)

        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                var result = data!!.extras.get("return").toString()

                Toast.makeText(this, result, Toast.LENGTH_LONG).show()

            }
        }



    }











}

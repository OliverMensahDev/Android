package recipefindermyapp.gohool.com.recipefinderapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_show_link.*

class ShowLinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_link)

        var extras = intent.extras

        if (extras != null) {
            var link = extras.get("link")

            webViewId.loadUrl(link.toString())


        }
    }
}

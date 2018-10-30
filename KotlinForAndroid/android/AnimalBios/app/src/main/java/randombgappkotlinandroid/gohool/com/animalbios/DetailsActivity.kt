package randombgappkotlinandroid.gohool.com.animalbios

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    val cheetahInfo = "Cheetahs are iconic cats, famous for their speed and strength. " +
            "They are also somewhat singular animals – they have several differences " +
            "to the majority of the feline species"
    val lionInfo = "Lions have strong, compact bodies and powerful forelegs, teeth, and jaws" +
            " for pulling down and killing prey. Their coats are yellow-gold. " +
            "Adult males have shaggy manes that range in color from blond to reddish-brown " +
            "to black and also vary in length."

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var animal = intent.extras
        if (animal != null) {
            if (animal.get("animal") == "cheetah") {
                //we display cheetah
                detailsImageView.setImageDrawable(application.getDrawable(R.drawable.cheetah))
                descriptionText.text = cheetahInfo
            }else {
                //we know it's lion
                //we display a lion
                detailsImageView.setImageDrawable(application.getDrawable(R.drawable.lion))
                descriptionText.text = lionInfo
            }

        }
    }
}

package ar.com.wolox.android.example.ui.imagefullscreen

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ar.com.wolox.android.R
import kotlinx.android.synthetic.main.activity_image_full_sceen.*

class ImageFullScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_full_sceen)

        intent.getStringExtra(IMAGE_URL)?.let { vImageFullScreenImageView.setImageURI(it) }
        vImageFullScreenlBackArrow?.setOnClickListener { finish() }
    }

    companion object {
        const val IMAGE_URL = "image_url"
    }
}

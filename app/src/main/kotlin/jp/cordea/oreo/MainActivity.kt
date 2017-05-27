package jp.cordea.oreo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.widget.Button
import butterknife.bindView

class MainActivity : AppCompatActivity() {

    private val toolbar: Toolbar by bindView(R.id.toolbar)

    private val notificationButton: Button by bindView(R.id.notification_button)

    private val pipButton: Button by bindView(R.id.pip_button)

    private val fontButton: Button by bindView(R.id.font_button)

    private val pinButton: Button by bindView(R.id.pin_button)

    private val animationButton: Button by bindView(R.id.animation_button)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        notificationButton.setOnClickListener {
            startActivity(Intent(this, NotificationActivity::class.java))
        }

        pipButton.setOnClickListener {
            startActivity(Intent(this, PipActivity::class.java))
        }

        fontButton.setOnClickListener {
            startActivity(Intent(this, FontActivity::class.java))
        }

        pinButton.setOnClickListener {
            startActivity(Intent(this, PinActivity::class.java))
        }

        animationButton.setOnClickListener {
            startActivity(Intent(this, AnimationActivity::class.java))
        }
    }
}

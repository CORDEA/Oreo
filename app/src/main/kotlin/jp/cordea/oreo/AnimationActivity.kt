package jp.cordea.oreo

import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.FlingAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import butterknife.bindView

class AnimationActivity : AppCompatActivity() {

    private val toolbar: Toolbar by bindView(R.id.toolbar)

    private val flingView: View by bindView(R.id.fling_view)

    private val springView: View by bindView(R.id.spring_view)

    private val fab: FloatingActionButton by bindView(R.id.fab)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener {
            startAnimation()
        }
    }

    private fun startAnimation() {
        FlingAnimation(flingView, DynamicAnimation.TRANSLATION_Y)
                .setStartVelocity(1500f)
                .setMinValue(0f)
                .setFriction(0.5f)
                .start()

        SpringAnimation(springView, DynamicAnimation.TRANSLATION_Y)
                .setStartVelocity(3000f)
                .setMinValue(0f)
                .setSpring(SpringForce(800f).apply {
                    dampingRatio = SpringForce.DAMPING_RATIO_HIGH_BOUNCY
                    stiffness = SpringForce.STIFFNESS_LOW
                })
                .start()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

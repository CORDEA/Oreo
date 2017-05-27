package jp.cordea.oreo

import android.app.PictureInPictureArgs
import android.content.res.Configuration
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import butterknife.bindView

class PipActivity : AppCompatActivity() {

    private val toolbar: Toolbar by bindView(R.id.toolbar)

    private val movieView: MovieView by bindView(R.id.movie_view)

    private val fab: FloatingActionButton by bindView(R.id.fab)

    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        fab.setOnClickListener {
            enterPictureInPictureMode(PictureInPictureArgs(movieView.aspectRatio, arrayListOf()))
        }
    }

    override fun onResume() {
        super.onResume()
        movieView.onResume()

        movieView.url = MovieUrl
        movieView.play()
    }

    override fun onStop() {
        super.onStop()
        movieView.onStop()
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean, newConfig: Configuration?) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig)
        if (isInPictureInPictureMode) {
            fab.visibility = View.GONE
            supportActionBar?.hide()
            return
        }
        fab.visibility = View.VISIBLE
        supportActionBar?.show()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val MovieUrl = "https://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8"
    }
}

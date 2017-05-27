package jp.cordea.oreo

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.support.annotation.AttrRes
import android.util.AttributeSet
import android.view.Surface
import android.view.TextureView
import android.widget.FrameLayout
import butterknife.bindView
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.Format
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.decoder.DecoderCounters
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelector
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.video.VideoRendererEventListener

/**
 * Created by Yoshihiro Tanaka on 2017/05/25.
 */
class MovieView : FrameLayout, VideoRendererEventListener {

    private val layout: AspectRatioFrameLayout by bindView(R.id.aspect_ratio_layout)

    private val textureView: TextureView by bindView(R.id.texture_view)

    var url: String? = null

    var aspectRatio: Float = 0f
        private set

    private val mainHandler = Handler()

    private val bandwidthMeter = DefaultBandwidthMeter()

    private var player: SimpleExoPlayer? = null

    private var factory: DataSource.Factory? = null

    private var trackSelector: TrackSelector? = null

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        inflate(context, R.layout.view_movie, this)
    }

    fun onResume() {
        initPlayer()
    }

    fun onStop() {
        release()
    }

    fun play() {
        prepare()
    }

    private fun prepare() {
        player?.let {
            factory = factory ?: DefaultHttpDataSourceFactory(UserAgent, bandwidthMeter)
            val source = HlsMediaSource(Uri.parse(url), factory, mainHandler, null)
            it.playWhenReady = true
            it.prepare(source, false, false)
        }
    }

    private fun release() {
        player?.let {
            it.stop()
            it.release()
            player = null
        }
    }

    private fun initPlayer() {
        val factory = AdaptiveTrackSelection.Factory(bandwidthMeter)
        trackSelector = DefaultTrackSelector(factory)
        trackSelector?.let {
            player = ExoPlayerFactory.newSimpleInstance(context, it)
            player?.let {
                it.setVideoDebugListener(this)
                it.setVideoTextureView(textureView)
            }
        }
    }

    override fun onDroppedFrames(p0: Int, p1: Long) {
    }

    override fun onVideoEnabled(p0: DecoderCounters?) {
    }

    override fun onVideoSizeChanged(p0: Int, p1: Int, p2: Int, p3: Float) {
        aspectRatio = p0.toFloat() / p1.toFloat()
        layout.setAspectRatio(aspectRatio)
    }

    override fun onVideoDisabled(p0: DecoderCounters?) {
    }

    override fun onVideoDecoderInitialized(p0: String?, p1: Long, p2: Long) {
    }

    override fun onVideoInputFormatChanged(p0: Format?) {
    }

    override fun onRenderedFirstFrame(p0: Surface?) {
    }

    companion object {
        private val UserAgent = "ExoPlayer"
    }

}
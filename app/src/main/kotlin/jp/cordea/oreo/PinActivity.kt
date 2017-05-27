package jp.cordea.oreo

import android.content.Intent
import android.content.pm.ShortcutInfo
import android.graphics.drawable.Icon
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import butterknife.bindView
import jp.cordea.oreo.services.IShortcutService
import javax.inject.Inject

class PinActivity : AppCompatActivity() {

    @Inject
    lateinit var shortcutService: IShortcutService

    private val toolbar: Toolbar by bindView(R.id.toolbar)

    private val labelTextView: TextView by bindView(R.id.label_text_view)

    private val nameTextView: TextView by bindView(R.id.name_text_view)

    private val nameEditText: EditText by bindView(R.id.name_edit_text)

    private val fab: FloatingActionButton by bindView(R.id.fab)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        App.component.inject(this)

        fab.setOnClickListener {
            addShortcut()
        }

        intent.getStringExtra(NameKey).let {
            if (!it.isNullOrBlank()) {
                nameTextView.text = getString(R.string.pin_name_format_text).format(it)
            }
        }
    }

    fun addShortcut() {
        if (labelTextView.text.isBlank() || nameEditText.text.isBlank()) {
            return
        }
        val info = ShortcutInfo
                .Builder(this, "shortcut")
                .setShortLabel(labelTextView.text.toString())
                .setIcon(Icon.createWithResource(this, R.drawable.ic_android_black_24dp))
                .setIntent(
                        Intent(Intent.ACTION_VIEW, Uri.parse(""),
                                this, this::class.java).apply {
                            putExtra(NameKey, nameEditText.text.toString())
                        }
                )
                .build()

        shortcutService.addShortcut(info)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private val NameKey = "nameEditText"
    }
}

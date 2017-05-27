package jp.cordea.oreo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import butterknife.bindView
import jp.cordea.oreo.services.INotificationService
import javax.inject.Inject

class NotificationActivity : AppCompatActivity() {

    @Inject
    lateinit var notificationService: INotificationService

    private val toolbar: Toolbar by bindView(R.id.toolbar)

    private val spinner: Spinner by bindView(R.id.spinner)

    private val sendButton: Button by bindView(R.id.send_button)

    private val titleEditText: EditText by bindView(R.id.title_edit_text)

    private val descriptionEditText: EditText by bindView(R.id.description_edit_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        App.component.inject(this)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
                arrayListOf(getString(R.string.channel_low_title), getString(R.string.channel_high_title)))
        spinner.adapter = adapter

        sendButton.setOnClickListener {
            if (titleEditText.text.isBlank() || descriptionEditText.text.isBlank()) {
                return@setOnClickListener
            }
            val id = Channel.valueOf((spinner.selectedItem as String).toUpperCase()).toId(this)
            notificationService.post(1, id,
                    titleEditText.text.toString(),
                    descriptionEditText.text.toString())
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

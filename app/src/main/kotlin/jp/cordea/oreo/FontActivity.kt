package jp.cordea.oreo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.text.*
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import butterknife.bindView

class FontActivity : AppCompatActivity() {

    private val toolbar: Toolbar by bindView(R.id.toolbar)

    private val abrilFatfaceTextView: TextView by bindView(R.id.abril_fatface_text_view)

    private val dancingScriptTextView: TextView by bindView(R.id.dancing_script_text_view)

    private val orbitronTextView: TextView by bindView(R.id.orbitron_text_view)

    private val poiretOneTextView: TextView by bindView(R.id.poiret_one_text_view)

    private val shadowsIntoLightTextView: TextView by bindView(R.id.shadows_into_light_text_view)

    private val editText: EditText by bindView(R.id.edit_text)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_font)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                abrilFatfaceTextView.text = p0
                dancingScriptTextView.text = p0
                orbitronTextView.text = p0
                poiretOneTextView.text = p0
                shadowsIntoLightTextView.text = p0
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

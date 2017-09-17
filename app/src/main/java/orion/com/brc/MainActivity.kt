package orion.com.brc

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.text.Editable
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    var dbHelper: DbHelper by Delegates.notNull()

    var db: SQLiteDatabase by Delegates.notNull()
    lateinit var result: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DbHelper(this)
        db = dbHelper.writableDatabase
        dbHelper.insert()

        Log.d("Main", "on created ")


        var inputText = findViewById<EditText>(R.id.input) as EditText;
        var searchBtn = findViewById<Button>(R.id.search);
        result = findViewById<TextView>(R.id.result)

        searchBtn.setOnClickListener {
            v ->
            Log.d("Main", "search clicked ")
            search(inputText.text);
        }

        result.setOnClickListener {
            v ->
            Log.d("Main", "result clickec")
            launchIntent()
        }
    }

    @SuppressLint("MissingPermission")
    private fun launchIntent() {
        var callIntent = Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:9177395828"));

        //TODO: check the permission?
        /*if (ActivityCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }*/
        startActivity(callIntent);
    }

    private fun search(text: Editable?) {
        var cursor = dbHelper.getCursor()
        cursor.moveToFirst()
        do {
            var plate = cursor.getString(cursor.getColumnIndexOrThrow("parking_number"))
            var name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
            var details = cursor.getString(cursor.getColumnIndexOrThrow("details"))
            var contact = cursor.getString(cursor.getColumnIndexOrThrow("contact"))
            Log.d("Main", "db values  " + plate + " name " + name + " details " + details + " contact " + contact)
            result.setText(plate + "     " + name + "   " + details + "  " + contact)
        } while (cursor.moveToNext())
    }
}

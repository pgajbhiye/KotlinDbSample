package orion.com.brc

import android.content.ContentValues
import android.content.Context


import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(context: Context) : SQLiteOpenHelper(context, "brc.db", null, 1) {

    private val TABLE = "VEHICLE";

    companion object {
        val PARKING_NUMBER: String = "parking_number"
        val NAME: String = "name"
        val DETAILS: String = "details"
        val CONTACT: String = "contact"
    }

    val DATABASE_CREATE = "CREATE TABLE if not exists " + TABLE + " (" + "${PARKING_NUMBER} text PRIMARY KEY," + "${NAME} text," + "${DETAILS} text," + "${CONTACT} text" + ")"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE);

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    fun insert() {
        val values = ContentValues()
        values.put(PARKING_NUMBER, "1234");
        values.put(NAME, "Naveen");
        values.put(DETAILS, "F-503");
        values.put(CONTACT, "7702559638");
        writableDatabase.insert(TABLE, null, values);


        val values1 = ContentValues()
        values1.put(PARKING_NUMBER, "12");
        values1.put(NAME, "Srikant");
        values1.put(DETAILS, "F-506");
        values1.put(CONTACT, "9177395828");
        writableDatabase.insert(TABLE, null, values1);
        Log.d("Main", "values inserted into db ")
    }

    fun getCursor(): Cursor {
        return readableDatabase
                .query(TABLE, arrayOf(PARKING_NUMBER, NAME, DETAILS, CONTACT), null, null, null, null, null); }
}
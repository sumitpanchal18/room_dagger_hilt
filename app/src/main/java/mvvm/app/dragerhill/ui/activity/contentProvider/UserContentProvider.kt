package mvvm.app.dragerhill.ui.activity.contentProvider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class UserContentProvider : ContentProvider() {

    companion object {
        const val PROVIDER_NAME = "mvvm.app.dragerhill"
        val CONTENT_URI: Uri = Uri.parse("content://$PROVIDER_NAME/users")
    }

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(): Boolean {
        dbHelper = DatabaseHelper(context!!)
        return true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        val db: SQLiteDatabase = dbHelper.readableDatabase
        return db.query("users", projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val id = db.insert("users", null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return Uri.parse("$CONTENT_URI/$id")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val rowsDeleted = db.delete("users", selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return rowsDeleted
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val rowsUpdated = db.update("users", values, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return rowsUpdated
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.$PROVIDER_NAME.users"
    }
}

package mvvm.app.dragerhill.ui.activity.contentProvider
import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.annotation.NonNull

class DataProvider : ContentProvider() {
    companion object {
        // Authority is a unique identifier for the content provider
        const val AUTHORITY = "mvvm.app.dragerhill.provider"

        // Define the content URI
        val CONTENT_URI: Uri = Uri.parse("content://$AUTHORITY/data")

        // Unique integer to identify the URI matcher patterns
        private const val DATA_COLLECTION = 1
        private const val DATA_ITEM = 2

        // URI matcher to route requests
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "data", DATA_COLLECTION)
            addURI(AUTHORITY, "data/#", DATA_ITEM)
        }
    }

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(): Boolean {
        // Initialize the database helper
        dbHelper = DatabaseHelper(context!!)
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // Get writable database
        val db = dbHelper.writableDatabase

        // Insert the new row
        val id = db.insert(DatabaseHelper.TABLE_NAME, null, values)

        // Notify observers of the change
        return if (id > 0) {
            val newUri = ContentUris.withAppendedId(CONTENT_URI, id)
            context?.contentResolver?.notifyChange(newUri, null)
            newUri
        } else {
            null
        }
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        // Get readable database
        val db = dbHelper.readableDatabase

        // Match the URI to determine the query type
        return when (uriMatcher.match(uri)) {
            DATA_COLLECTION -> {
                // Query all data
                db.query(
                    DatabaseHelper.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }

            DATA_ITEM -> {
                // Query a specific item
                val id = ContentUris.parseId(uri)
                val modifiedSelection = "${DatabaseHelper.COLUMN_ID} = ?"
                val modifiedSelectionArgs = arrayOf(id.toString())

                db.query(
                    DatabaseHelper.TABLE_NAME,
                    projection,
                    modifiedSelection,
                    modifiedSelectionArgs,
                    null,
                    null,
                    sortOrder
                )
            }

            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun delete(
        @NonNull uri: Uri,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val db = dbHelper.writableDatabase
        val deletedRows = db.delete(DatabaseHelper.TABLE_NAME, selection, selectionArgs)

        // Notify observers of the change
        if (deletedRows > 0) {
            context?.contentResolver?.notifyChange(uri, null)
        }

        return deletedRows
    }

    override fun update(
        @NonNull uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val db = dbHelper.writableDatabase
        val updatedRows = db.update(
            DatabaseHelper.TABLE_NAME,
            values,
            selection,
            selectionArgs
        )

        // Notify observers of the change
        if (updatedRows > 0) {
            context?.contentResolver?.notifyChange(uri, null)
        }

        return updatedRows
    }

    override fun getType(@NonNull uri: Uri): String {
        return when (uriMatcher.match(uri)) {
            DATA_COLLECTION -> "vnd.android.cursor.dir/vnd.$AUTHORITY.data"
            DATA_ITEM -> "vnd.android.cursor.item/vnd.$AUTHORITY.data"
            else -> throw IllegalArgumentException("Unsupported URI: $uri")
        }
    }
}

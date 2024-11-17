package mvvm.app.dragerhill.ui.activity.contentProvider

import android.content.ContentValues
import android.database.Cursor
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mvvm.app.dragerhill.R

class ContentProviderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)

        // Example of inserting a user
        insertUser("John Doe", "john.doe@example.com")

        // Example of querying all users
        queryUsers()

        // Example of updating a user's email
        updateUserEmail("John Doe", "new.email@example.com")

        // Example of deleting a user
        deleteUser("John Doe")
    }



    private fun insertUser(name: String, email: String) {
        val values = ContentValues().apply {
            put("name", name)
            put("email", email)
        }
        val uri = contentResolver.insert(UserContentProvider.CONTENT_URI, values)
        println("Inserted data URI: $uri")
    }

    private fun queryUsers() {
        val cursor: Cursor? = contentResolver.query(
            UserContentProvider.CONTENT_URI,
            null, // Select all columns
            null, // No selection criteria
            null, // No selection arguments
            null  // Default sort order
        )

        cursor?.let {
            while (it.moveToNext()) {
                val name = it.getString(it.getColumnIndex("name"))
                val email = it.getString(it.getColumnIndex("email"))
                println("User: $name, Email: $email")
            }
            it.close()
        }
    }

    private fun updateUserEmail(name: String, newEmail: String) {
        val values = ContentValues().apply {
            put("email", newEmail)
        }
        val rowsUpdated = contentResolver.update(
            UserContentProvider.CONTENT_URI,
            values,
            "name = ?",
            arrayOf(name)
        )
        println("Rows updated: $rowsUpdated")
    }

    private fun deleteUser(name: String) {
        val rowsDeleted = contentResolver.delete(
            UserContentProvider.CONTENT_URI,
            "name = ?",
            arrayOf(name)
        )
        println("Rows deleted: $rowsDeleted")
    }
}

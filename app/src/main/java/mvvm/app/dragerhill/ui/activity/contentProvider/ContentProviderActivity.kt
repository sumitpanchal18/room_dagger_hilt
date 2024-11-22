package mvvm.app.dragerhill.ui.activity.contentProvider

import android.content.ContentValues
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mvvm.app.dragerhill.R

class ContentProviderActivity : AppCompatActivity() {
    private val TAG = "ContentProviderActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_provider)
/*
        val insertButton: Button = findViewById(R.id.insertButton)
        insertButton.setOnClickListener {
            try {
                // Validate content values before insertion
                val values = ContentValues().apply {
                    put(DatabaseHelper.COLUMN_NAME, "Sample Kotlin Data")
                    put(DatabaseHelper.COLUMN_VALUE, "Hello from Kotlin Sender App")
                }

                // Double-check CONTENT_URI
                val contentUri = Uri.parse(DataProvider.CONTENT_URI.toString())
                Log.d(TAG, "Attempting to insert into URI: $contentUri")

                // Insert data into Content Provider
                val newUri = contentResolver.insert(
                    contentUri,
                    values
                )

                // Check if insertion was successful
                if (newUri != null) {
                    Log.d(TAG, "Data inserted successfully. New URI: $newUri")
                    Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Log.e(TAG, "Failed to insert data")
                    Toast.makeText(this, "Failed to insert data", Toast.LENGTH_SHORT).show()
                }
            } catch (e: IllegalArgumentException) {
                // Specific handling for URI-related errors
                Log.e(TAG, "URI Error: ${e.message}", e)
                Toast.makeText(this, "Invalid Content Provider URI", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                // Catch-all for any other unexpected errors
                Log.e(TAG, "Insertion failed: ${e.message}", e)
                Toast.makeText(this, "Error inserting data: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }*/
    }
}
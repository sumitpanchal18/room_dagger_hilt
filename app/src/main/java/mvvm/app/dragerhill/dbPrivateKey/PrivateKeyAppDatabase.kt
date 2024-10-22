package mvvm.app.dragerhill.dbPrivateKey

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PrivateKey::class], version = 2, exportSchema = true)
abstract class PrivateKeyAppDatabase : RoomDatabase() {

    abstract fun privateKeyDao(): PrivateKeyDao

}

/**
 * Exporting the schema helps you track changes to the database schema across different versions of the app
 * By exporting the schema, you can store historical snapshots of the database schema
 */

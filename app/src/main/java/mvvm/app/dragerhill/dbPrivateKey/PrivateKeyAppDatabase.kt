package mvvm.app.dragerhill.dbPrivateKey

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PrivateKey::class], version = 2, exportSchema = true)
abstract class PrivateKeyAppDatabase : RoomDatabase() {

    abstract fun privateKeyDao(): PrivateKeyDao

}
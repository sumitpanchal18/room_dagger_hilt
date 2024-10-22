package mvvm.app.dragerhill.dbMetadata

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MetaData::class], version = 1, exportSchema = true)
abstract class MetadataAppDatabase : RoomDatabase() {

    abstract fun metadataDao(): MetadataDao

}

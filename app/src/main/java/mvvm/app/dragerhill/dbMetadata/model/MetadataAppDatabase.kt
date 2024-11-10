package mvvm.app.dragerhill.dbMetadata.model

import androidx.room.Database
import androidx.room.RoomDatabase
import mvvm.app.dragerhill.dbMetadata.model.MetaData
import mvvm.app.dragerhill.dbMetadata.model.MetadataDao

@Database(entities = [MetaData::class], version = 1, exportSchema = true)
abstract class MetadataAppDatabase : RoomDatabase() {
    abstract fun metadataDao(): MetadataDao
}

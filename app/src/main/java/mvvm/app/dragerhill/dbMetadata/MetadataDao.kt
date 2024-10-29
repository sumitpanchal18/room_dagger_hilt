package mvvm.app.dragerhill.dbMetadata

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MetadataDao {
    @Query("SELECT * FROM metadata_table")
    fun getMetadata(): Flow<List<MetaData>>

    @Query("SELECT * FROM metadata_table WHERE id % 2 = 0")
    fun getEvenMetadata(): Flow<List<MetaData>>

    @Delete
    suspend fun delete(metadata: MetaData)

    @Insert
    suspend fun create(metadata: MetaData)

    @Update
    suspend fun update(metadata: MetaData)

    @Query("UPDATE metadata_table SET type='Invalid' WHERE id=33")
    suspend fun updateInvalid()

    @Query("DELETE FROM metadata_table")
    suspend fun deleteAllMetadata()

    @Query("DELETE FROM metadata_table WHERE type LIKE :invalid")
    suspend fun deleteInvalid(invalid: String)

}

package mvvm.app.dragerhill.dbPrivateKey

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PrivateKeyDao {

    @Query("SELECT * FROM privatekey")
    fun getPrivateKey(): LiveData<List<PrivateKey>>

    @Query("SELECT * FROM privatekey")
    suspend fun getListPrivateKey(): List<PrivateKey>

    @Insert
    suspend fun create(privateKey: PrivateKey)

    @Update
    suspend fun update(privateKey: PrivateKey)

    @Query("UPDATE privatekey SET relay='XYZ' WHERE id=32")
    suspend fun updateById()

    @Query("DELETE FROM privatekey")
    suspend fun deleteAllMetadata()

    @Query("DELETE FROM privatekey WHERE id=30")
    suspend fun deleteById()
}

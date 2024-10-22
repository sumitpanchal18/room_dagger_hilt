package mvvm.app.dragerhill.dbMetadata

import androidx.lifecycle.LiveData
import javax.inject.Inject

class MetadataRepo @Inject constructor(private val metadataDao: MetadataDao) {

    fun getAllMetadata(): LiveData<List<MetaData>> {
        return metadataDao.getMetadata()
    }

    fun getEvenMetadata() : LiveData<List<MetaData>>{
        return metadataDao.getEvenMetadata()
    }
    suspend fun createMetadata(metadata: MetaData) {
        metadataDao.create(metadata)
    }


    suspend fun updateMetadata(metadata: MetaData) {
        metadataDao.update(metadata)
    }

    suspend fun deleteMetadata(metadata: MetaData) {
        metadataDao.delete(metadata)
    }

    suspend fun deleteAllMetadata(){
        metadataDao.deleteAllMetadata()
    }
    suspend fun deleteInvalidRepo(){
        metadataDao.deleteInvalid("Invalid")
    }
    suspend fun updateType(){
        metadataDao.updateInvalid()
    }
}

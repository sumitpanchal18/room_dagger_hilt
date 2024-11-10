package mvvm.app.dragerhill.dbMetadata.model

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MetadataRepo @Inject constructor(private val metadataDao: MetadataDao) {

    fun getAllMetadata(): Flow<List<MetaData>> {
        return metadataDao.getMetadata()
    }

    fun getEvenMetadata() : Flow<List<MetaData>>{
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

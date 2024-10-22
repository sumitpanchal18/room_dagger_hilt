package mvvm.app.dragerhill.dbPrivateKey

import androidx.lifecycle.LiveData
import javax.inject.Inject

class PrivateKeyRepo @Inject constructor(private val privateKeyDao: PrivateKeyDao) {

    fun getAllPrivateKey(): LiveData<List<PrivateKey>> {
        return privateKeyDao.getPrivateKey()
    }

    suspend fun getListPrivateKey(): List<PrivateKey> {
        return privateKeyDao.getListPrivateKey()
    }

    suspend fun createPrivateKey(privateKey: PrivateKey) {
        privateKeyDao.create(privateKey)
    }

    suspend fun updatePrivateKey(privateKey: PrivateKey) {
        privateKeyDao.update(privateKey)
    }

    suspend fun updateReplayById() {
        privateKeyDao.updateById()
    }

    suspend fun deleteAllPk() {
        privateKeyDao.deleteAllMetadata()
    }

    suspend fun deleteById() {
        privateKeyDao.deleteById()
    }
}

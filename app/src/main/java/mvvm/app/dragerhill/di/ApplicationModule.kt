package mvvm.app.dragerhill.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mvvm.app.dragerhill.dbMetadata.MetadataAppDatabase
import mvvm.app.dragerhill.dbMetadata.MetadataDao
import mvvm.app.dragerhill.dbMetadata.MetadataRepo
import mvvm.app.dragerhill.dbPrivateKey.PrivateKeyAppDatabase
import mvvm.app.dragerhill.dbPrivateKey.PrivateKeyDao
import mvvm.app.dragerhill.dbPrivateKey.PrivateKeyRepo

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideMetadataAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        MetadataAppDatabase::class.java,
        "metadataDB"
    ).build()

    @Provides
    fun provideMetadataDao(metadataAppDatabase: MetadataAppDatabase): MetadataDao =
        metadataAppDatabase.metadataDao()

    @Provides
    fun provideMetadataRepo(metadataDao: MetadataDao): MetadataRepo =
        MetadataRepo(metadataDao)

    @Singleton
    @Provides
    fun providePrivateKeyAppDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        PrivateKeyAppDatabase::class.java,
        "pvDB"
    ).build()


    @Provides
    fun providePrivateKeyDao(privateKeyAppDatabase: PrivateKeyAppDatabase): PrivateKeyDao =
        privateKeyAppDatabase.privateKeyDao()

    @Provides
    fun providePrivateKeyRepo(privateKeyDao: PrivateKeyDao): PrivateKeyRepo =
        PrivateKeyRepo(privateKeyDao)
}

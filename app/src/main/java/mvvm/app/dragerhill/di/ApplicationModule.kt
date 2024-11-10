package mvvm.app.dragerhill.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mvvm.app.dragerhill.commentJsonHolderApi.model.CommentApi
import mvvm.app.dragerhill.dbMetadata.model.MetadataAppDatabase
import mvvm.app.dragerhill.dbMetadata.model.MetadataDao
import mvvm.app.dragerhill.dbMetadata.model.MetadataRepo
import mvvm.app.dragerhill.dbPrivateKey.PrivateKeyAppDatabase
import mvvm.app.dragerhill.dbPrivateKey.PrivateKeyDao
import mvvm.app.dragerhill.dbPrivateKey.PrivateKeyRepo
import mvvm.app.dragerhill.utils.Constant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer your_token")
                    .build()
                chain.proceed(request)
            }
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BaseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCommentApi(retrofit: Retrofit): CommentApi {
        return retrofit.create(CommentApi::class.java)
    }

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

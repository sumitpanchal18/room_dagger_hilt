package mvvm.app.dragerhill.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import mvvm.app.dragerhill.commentJsonHolderApi.CommentApi
import mvvm.app.dragerhill.dbMetadata.MetadataAppDatabase
import mvvm.app.dragerhill.dbMetadata.MetadataDao
import mvvm.app.dragerhill.dbMetadata.MetadataRepo
import mvvm.app.dragerhill.dbPrivateKey.PrivateKeyAppDatabase
import mvvm.app.dragerhill.dbPrivateKey.PrivateKeyDao
import mvvm.app.dragerhill.dbPrivateKey.PrivateKeyRepo
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    //    Comment retrofit api integration
    private val baseUrl = "https://jsonplaceholder.typicode.com/"

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
//        Sets the maximum time to establish a connection to a server.
            .readTimeout(30, TimeUnit.SECONDS)
//        Sets the maximum time to wait for a server response after establishing a connection
            .build()
    }

    /**
     *  Interceptors can intercept and modify requests and responses, providing a
     *  way to add functionality like logging, authentication, or error handling.
     *
     *  chain: Represents the current request/response chain.
     *
     *  Adds an Authorization header to the request. This is commonly used for API authentication,
     *  where your_token is a placeholder for the actual token you want to send.
     */

    @Singleton
    @Provides
    fun provideRetrofit(okHttp: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideCommentApi(retrofit: Retrofit): CommentApi {
        return retrofit.create(CommentApi::class.java)
    }

    // metadata room database

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

    // private key room database

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

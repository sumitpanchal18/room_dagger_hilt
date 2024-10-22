package mvvm.app.dragerhill.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mvvm.app.dragerhill.network.ApiInterface
import mvvm.app.dragerhill.utils.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AppModules {

    @Provides
    fun providesURL() = Constant.BaseUrl

    @Provides
    @Singleton
    fun providesApiService(url: String): ApiInterface =
        Retrofit.Builder().baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
}
package com.sol.canada.di

import android.annotation.SuppressLint
import android.content.Context
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.sol.canada.api.ApiInterface
import com.sol.canada.data.AppDatabase
import com.sol.canada.data.FactRepository
import com.sol.canada.ui.countryfactdetails.data.FactDetailsRepository
import com.sol.canada.ui.countryfactdetails.data.FactsLocalDataSource
import com.sol.canada.ui.countryfactdetails.data.FactsRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Module which provide dependency for FactRemoteDataSource, FactsLocalDataSource and FactDetailsRepository
 */
@SuppressLint("JvmStaticProvidesInObjectDetector")
@Module(includes = [ApplicationModuleBinds::class])
object AppModule {
    @JvmStatic
    @Provides
    @Singleton
    fun provideStethoIntercepor(): StethoInterceptor {
        return StethoInterceptor()
    }

    @JvmStatic
    @Provides
    @Singleton
    fun provideFactsRemoteDataSource(apiInterface: ApiInterface) =
        FactsRemoteDataSource(apiInterface)

    @JvmStatic
    @Singleton
    @Provides
    fun provideDb(context: Context) = AppDatabase.getInstance(context)

    @JvmStatic
    @Provides
    @Singleton
    fun provideFactsLocalDataSource(db:AppDatabase) = FactsLocalDataSource(db.factDao())
}
@Module
abstract class ApplicationModuleBinds {

    @Singleton
    @Binds
    abstract fun bindRepository(repo: FactDetailsRepository): FactRepository
}

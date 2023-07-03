package com.plcoding.translator_kmm.android.di

import android.app.Application
import com.plcoding.translator_kmm.core.language.translate.data.history.SqDeLightHistoryDataSource
import com.plcoding.translator_kmm.core.language.translate.data.history.Translate
import com.plcoding.translator_kmm.core.language.translate.data.local.DataBaseDriverFactory
import com.plcoding.translator_kmm.core.language.translate.domain.KtorTranslateClient
import com.plcoding.translator_kmm.core.language.translate.domain.history.HistoryDataSource
import com.plcoding.translator_kmm.core.language.translate.domain.translate.TranslateClient
import com.plcoding.translator_kmm.database.TranslateDatabase
import com.plcoding.translator_kmm.translate.remote.HttpClientFactory
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory().creator()
    }

    @Provides
    @Singleton
    fun provideTranslateClient(httpClient: HttpClient): TranslateClient {
        return KtorTranslateClient(httpClient)
    }

    @Provides
    @Singleton
    fun provideDatabaseDriver(app: Application): SqlDriver {
        return DataBaseDriverFactory(app).create()
    }

    @Provides
    @Singleton
    fun provideHistoryDataSource(driver: SqlDriver): HistoryDataSource {
        return SqDeLightHistoryDataSource(TranslateDatabase(driver))
    }

    @Provides
    @Singleton
    fun provideTranslateUseCase(
        client: TranslateClient,
        dataSource: HistoryDataSource
    ): Translate {
        return Translate(client, dataSource)
    }
}
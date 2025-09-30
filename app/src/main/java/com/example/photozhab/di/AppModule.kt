package com.example.photozhab.di

import android.content.Context
import com.example.photozhab.data.local.dao.CanvasDao
import com.example.photozhab.data.local.datasource.AppDatabase
import com.example.photozhab.data.repository.impl.CanvasRepositoryImpl
import com.example.photozhab.data.repository.impl.GalleryRepositoryImpl
import com.example.photozhab.domain.interfaces.repository.local.CanvasRepository
import com.example.photozhab.domain.interfaces.repository.local.GalleryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideCanvasDao(appDatabase: AppDatabase): CanvasDao {
        return appDatabase.canvasDao()
    }

    @Provides
    @Singleton
    fun provideCanvasRepository(dao: CanvasDao): CanvasRepository {
        return CanvasRepositoryImpl(dao = dao)
    }

    @Provides
    @Singleton
    fun provideGalleryRepository(@ApplicationContext context: Context): GalleryRepository {
        return GalleryRepositoryImpl(resolver = context.contentResolver)
    }
}
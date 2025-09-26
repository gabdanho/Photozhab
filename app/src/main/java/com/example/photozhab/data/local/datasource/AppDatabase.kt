package com.example.photozhab.data.local.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.photozhab.data.local.dao.CanvasDao
import com.example.photozhab.data.local.entity.Canvas

@Database(entities = [Canvas::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun canvasDao(): CanvasDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
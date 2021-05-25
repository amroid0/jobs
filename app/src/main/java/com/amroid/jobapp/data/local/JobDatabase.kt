package com.amroid.jobapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.amroid.jobapp.data.model.GithubJob


@Database(
    entities = [GithubJob::class],
    version = 2,
    exportSchema = false
)
abstract class JobDatabase : RoomDatabase() {

    abstract fun jobDao(): JobDao

    companion object {
        const val DATABASE_NAME = "jobs.db"

        @Volatile
        private var INSTANCE: JobDatabase? = null

        fun getInstance(context: Context): JobDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    JobDatabase::class.java,
                    DATABASE_NAME
                )
                    .build()

                INSTANCE = instance
                return instance
            }
        }

    }

}
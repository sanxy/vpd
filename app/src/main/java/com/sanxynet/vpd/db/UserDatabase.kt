package com.sanxynet.vpd.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [(UserEntity::class)], version = 1)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDAO

    companion object {
        private var INSTANCE: UserDatabase?= null

        val migration_1_2: Migration = object: Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE userinfo ADD COLUMN phone TEXT DEFAULT ''")
            }
        }

        fun getAppDatabase(context: Context): UserDatabase? {

            if(INSTANCE == null ) {

                INSTANCE = Room.databaseBuilder<UserDatabase>(
                    context.applicationContext, UserDatabase::class.java, "AppDBB"
                )
//                    .addMigrations(migration_1_2)
                    .allowMainThreadQueries()
                    .build()

            }
            return INSTANCE
        }
        fun destroyInstance() {
            INSTANCE = null
        }
    }

}
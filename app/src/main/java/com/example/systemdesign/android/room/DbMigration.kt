package com.example.systemdesign.android.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object DbMigration {

    const val startVersion = 1;
    const val updateVersion = 2;

    val migration  = object : Migration(startVersion,updateVersion){
        override fun migrate(db: SupportSQLiteDatabase) {
         db.execSQL("ALTER TABLE student ADD COLUMN fatherName TEXT")
        }
    }
}
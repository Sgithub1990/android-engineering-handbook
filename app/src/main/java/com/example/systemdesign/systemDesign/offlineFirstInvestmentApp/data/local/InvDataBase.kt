package com.example.systemdesign.systemDesign.offlineFirstInvestmentApp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PortfolioEntity::class, HoldingEntity::class], version = 101)
abstract class InvDataBase : RoomDatabase(){
    abstract fun invDao(): InvDao
}
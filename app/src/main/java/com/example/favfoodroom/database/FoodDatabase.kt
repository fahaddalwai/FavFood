package com.example.favfoodroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Food::class], version = 4, exportSchema = false)
abstract class FoodDatabase : RoomDatabase() {

    abstract val foodDatabaseDao: FoodDatabaseDao

    companion object {           /*companion object is used to  access the methods for creating
                                    or getting the database without instantiating the class*/

        @Volatile                  //value of a volatile variable will never be cached, and all writes and reads will be done to and from the main memory
        private var INSTANCE: FoodDatabase? =
            null                    //keep a reference to the database, when one has been created

        fun getInstance(context: Context): FoodDatabase {
            synchronized(this) {                   /*Only one thread of execution at a time can enter this block of code,
                                                        which makes sure the database only gets initialized once.*/
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FoodDatabase::class.java,
                        "food_history_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
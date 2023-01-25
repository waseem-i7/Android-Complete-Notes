package com.mwi7.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class),version = 1,exportSchema = false)
abstract class NoteDatabase : RoomDatabase(){

    abstract fun getNoteDoa() : NoteDao

    companion object{

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context : Context) : NoteDatabase{
            //if the INSTACE IS NOT null , then return it,
            //if it is , then create the database
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()

                INSTANCE = instance
                //return instance
                instance
            }
        }
    }
}
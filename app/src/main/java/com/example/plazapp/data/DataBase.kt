package com.example.plazapp.data

import android.content.Context
import androidx.room.*

@Database(entities = [Usuarios::class], version = 2, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class DataBase : RoomDatabase() {
    abstract fun usuariosDAO() : UsuariosDAO
    companion object{
        private const val DATABASE_NAME = "database.db"
        @Volatile
        private var INSTANCE : DataBase? = null

        fun getInstance(context: Context) : DataBase?{
            INSTANCE ?: synchronized(this){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DataBase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
            }
            return INSTANCE
        }
    }
}
@Dao
interface UsuariosDAO {
    @Query( "select * from Usuarios")
    fun selectAll(): List<Usuarios>

    @Insert
    fun insert(usuario: Usuarios)

    @Update
    fun update(usuario: Usuarios)

    @Delete
    fun delete(usuario: Usuarios)
}
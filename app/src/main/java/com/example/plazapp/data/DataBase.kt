package com.example.plazapp.data

import android.content.Context
import androidx.room.*

@Database(entities = [Pedidos::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class DataBase : RoomDatabase() {
    abstract fun pedidosDAO() : PedidosDAO
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
                ).build()
            }
            return INSTANCE
        }
    }
}
@Dao
interface PedidosDAO {
    @Insert
    fun insert(pedido: Pedidos)

    @Query( "select * from Pedidos")
    fun selectAll(): List<Pedidos>
}
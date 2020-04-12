package com.example.plazapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Pedidos {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    @ColumnInfo(name = "idPedido")
    var idPedido: Int = 0
    @ColumnInfo(name = "idTienda")
    var idTienda: Int = 0
    @ColumnInfo(name = "tienda")
    var tienda: String = ""
    @ColumnInfo(name = "objeto")
    var objeto: String = ""
    @ColumnInfo(name = "unidades")
    var unidades: String = ""
    @ColumnInfo(name = "cantidad")
    var cantidad: Double = 0.0
    @ColumnInfo(name = "creado")
    var creado: Date = Date()
    @ColumnInfo(name = "actualizado")
    var actualizado: Date = Date()
}
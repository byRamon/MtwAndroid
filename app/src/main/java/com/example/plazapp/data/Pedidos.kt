package com.example.plazapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
class Usuarios {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
    @ColumnInfo(name = "nombre")
    var nombre: String = ""
    @ColumnInfo(name = "telefono")
    var telefono: String = ""
    @ColumnInfo(name = "direccion")
    var direccion: String = ""
    @ColumnInfo(name = "creado")
    var creado: Date = Date()
    @ColumnInfo(name = "actualizado")
    var actualizado: Date = Date()
}
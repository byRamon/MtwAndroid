package com.example.plazapp.data

class Tienda (parNombre:String, parDescripcion:String, parTelefono:String, parUbicacion:String){
    var nombre:String = ""
    var descripcion:String = ""
    var telefono:String = ""
    var ubicacion:String = ""
    init {
        this.nombre = parNombre
        this.descripcion = parDescripcion
        this.telefono = parTelefono
        this.ubicacion = parUbicacion
    }
}
package com.example.plazapp.data

class Tienda (parId:String, parNombre:String, parDescripcion:String, parTelefono:String, parUbicacion:String, parImagen:Int){
    var id:String
    var nombre:String = ""
    var descripcion:String = ""
    var telefono:String = ""
    var ubicacion:String = ""
    var imagen:Int = 0
    init {
        this.id = parId
        this.nombre = parNombre
        this.descripcion = parDescripcion
        this.telefono = parTelefono
        this.ubicacion = parUbicacion
        this.imagen = parImagen
    }
}
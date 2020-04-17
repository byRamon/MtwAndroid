package com.example.plazapp.data

class Tienda (parId:String, parNombre:String, parDescripcion:String, parTelefono:String, parUbicacion:String, parImagen:String){
    var id:String= ""
    var nombre:String = ""
    var descripcion:String = ""
    var telefono:String = ""
    var ubicacion:String = ""
    var imagen:String = ""
    init {
        this.id = parId
        this.nombre = parNombre
        this.descripcion = parDescripcion
        this.telefono = parTelefono
        this.ubicacion = parUbicacion
        this.imagen = parImagen
    }
}
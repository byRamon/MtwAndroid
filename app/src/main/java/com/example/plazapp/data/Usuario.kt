package com.example.plazapp.data

class Usuario (parNombre:String, parDireccion:String, parTelefono:String){
    var nombre:String = ""
    var telefono:String = ""
    var direccion:String = ""
    init {
        this.nombre = parNombre
        this.direccion = parDireccion
        this.telefono = parTelefono
    }
}
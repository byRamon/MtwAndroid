package com.example.plazapp


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ContenidoFragment : Fragment() {

    var vista: View? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vista = inflater.inflate(R.layout.fragment_contenido, container, false)
        cargarDatos()
        return vista
    }

    fun nuevaInstancia(index: Int): ContenidoFragment{
        val f = ContenidoFragment()
        var args = Bundle()
        args.putInt("INDEX", index)
        f.arguments = args
        return f
    }
    fun obtenerIndex(): Int{
        val index = arguments?.getInt("INDEX", 0)!!
        return index
    }

    fun cargarDatos(){
        Log.i(MainActivity.LOG_TAG, "cargar datos")
        var titulo = vista!!.findViewById<TextView>(R.id.tv_titulo_tienda)
        titulo.text = lsttiendas.lstTiendas?.get(obtenerIndex())?.nombre
    }

}

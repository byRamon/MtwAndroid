package com.example.plazapp


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.plazapp.data.Adapter
import com.example.plazapp.data.Items
import kotlinx.android.synthetic.main.activity_items.*

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
        var icono = vista!!.findViewById<ImageView>(R.id.iv_logotienda)
        var titulo = vista!!.findViewById<TextView>(R.id.tv_titulo_tienda)
        var descripcion = vista!!.findViewById<TextView>(R.id.tv_descripcion)
        var telefono = vista!!.findViewById<TextView>(R.id.tv_telefono)
        var ubicacion = vista!!.findViewById<TextView>(R.id.tv_ubicacion)
        var btn = vista!!.findViewById<Button>(R.id.btn_entrartienda)
        icono.setImageResource(lsttiendas.lstTiendas?.get(obtenerIndex())?.imagen!!)
        titulo.text = lsttiendas.lstTiendas?.get(obtenerIndex())?.nombre
        descripcion.text = lsttiendas.lstTiendas?.get(obtenerIndex())?.descripcion
        telefono.text = lsttiendas.lstTiendas?.get(obtenerIndex())?.telefono
        ubicacion.text = lsttiendas.lstTiendas?.get(obtenerIndex())?.ubicacion

        btn.setOnClickListener{
            val intent = Intent(this.context, ItemsActivity::class.java)
            startActivity(intent)
        }
    }
}

package com.example.plazapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import com.example.plazapp.data.*

class lsttiendas : Fragment() {
    companion object{
        var lstTiendas:ArrayList<Tienda>? = null
    }
    var lstNombres:ArrayList<String>? = null
    var lista: ListView? = null
    var doblePanel = false
    var posicionActual = 0

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configurarListView()
        val frameDetalles=activity!!.findViewById<FrameLayout>(R.id.frgdetalletienda)
        doblePanel = frameDetalles != null && frameDetalles.visibility == View.VISIBLE
        Log.i(MainActivity.LOG_TAG, "doble panel: $doblePanel")
        if(savedInstanceState != null){
            posicionActual = savedInstanceState.getInt("INDEX", 0)
        }
        if(doblePanel){
            lista?.choiceMode = ListView.CHOICE_MODE_SINGLE
            mostrarDetalles(this.posicionActual)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vista = inflater.inflate(R.layout.fragment_lsttiendas, container, false)
        return vista
    }

    private fun configurarListView(){
        lstTiendas = ArrayList()
        lstTiendas?.add(Tienda("Las quince letrss", "Venta de abarrote", "4578784512", "Colonia Laureles, torreslanda #27", R.drawable.appicon))
        lstTiendas?.add(Tienda("Pollos hermanos", "Pollo estilo Sinaloa", "4578787988", "Colonia Laureles, torreslanda #46", R.drawable.appicon))
        lstTiendas?.add(Tienda("Carniceria Diaz", "Venta de carne fresca", "4577878469", "Colonia Laureles, torreslanda #120", R.drawable.appicon))

        lstNombres = obtenerNombres(lstTiendas!!)
        val adaptador = ArrayAdapter(activity!!, android.R.layout.simple_list_item_activated_1, lstNombres!!)
        lista = activity!!.findViewById(R.id.lst_tiendas)
        lista?.adapter = adaptador
        lista?.setOnItemClickListener { adapterView, view, i, l ->
            mostrarDetalles(i)
        }
    }

    fun mostrarDetalles(index:Int) {
        posicionActual = index
        if (doblePanel) {
            var frgDetalles =
                activity!!.supportFragmentManager.findFragmentById(R.id.frgdetalletienda) as? ContenidoFragment
            if (frgDetalles == null || frgDetalles.obtenerIndex() != index) {
                frgDetalles = ContenidoFragment().nuevaInstancia(index)
                val frgTransaction = activity!!.supportFragmentManager.beginTransaction()
                frgTransaction.replace(R.id.frgdetalletienda, frgDetalles)
                frgTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                frgTransaction.commit()
            }
        } else {
            val intent = Intent(activity, DetallesActivity::class.java)
            intent.putExtra("INDEX", index)
            startActivity(intent)
        }

    }
    fun obtenerNombres(list:ArrayList<Tienda>):ArrayList<String>{
        val nombres = ArrayList<String>()
        for(tienda in list){
            nombres.add(tienda.nombre)
        }
        return  nombres
    }
}

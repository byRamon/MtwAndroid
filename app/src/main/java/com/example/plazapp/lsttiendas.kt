package com.example.plazapp

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.example.plazapp.data.Tienda
import kotlinx.android.synthetic.*
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.plazapp.api.DescargaURL
import com.example.plazapp.api.Network
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
        jsonArrayRequest()
    }

    fun jsonArrayRequest() {
        //if (Network.hayRed(this.contex)) {
        //        DescargaURL(this.context).execute("http://www.google.com")
        //        if (Network.hayRed(this.context)) {
                // Instantiate the RequestQueue.
                val queue = Volley.newRequestQueue(this.context)
                val url = "http://192.168.0.7:8080/ApiProyecto/post.php"//?productos=true&idtienda=B4B2A879-7EBF-11EA-B2E7-94E979ECB4F6"
                // Request a JSONArray response from the provided URL.
                val jsonArrayRequest = JsonArrayRequest(url,
                    Response.Listener { array ->
                        //Log.i(MainActivity.LOG_TAG, "Response is: $array")
                        //Log.i(MainActivity.LOG_TAG, "Hay : ${array.length()}")
                        //var array = response.getJSONArray(1)
                        lstTiendas = ArrayList()
                        for (i in 0 until array.length()){
                            var obj = array.getJSONObject(i)
                            //Log.i(MainActivity.LOG_TAG, "nombre de la tienda : ${obj.getString("nombre")}")
                            lstTiendas?.add(Tienda(
                                obj.getString("id"),
                                obj.getString("nombre"),
                                obj.getString("descripcion"),
                                obj.getString("telefono"),
                                obj.getString("ubicacion"),
                                obj.getString("imagen")))
                        }
                        lstNombres = obtenerNombres(lstTiendas!!)
                        val adaptador = ArrayAdapter(activity!!, android.R.layout.simple_list_item_activated_1, lstNombres!!)
                        lista = activity!!.findViewById(R.id.lst_tiendas)
                        lista?.adapter = adaptador
                        lista?.setOnItemClickListener { adapterView, view, i, l ->
                            mostrarDetalles(i)
                        }
                    },
                    Response.ErrorListener { error ->
                        error.printStackTrace()
                        Log.e(MainActivity.LOG_TAG, "Api no accesible!")
                    }
                )
                // Add the request to the RequestQueue.
                queue.add(jsonArrayRequest)
          //  } else Toast.makeText(this.context, "No hay Red", Toast.LENGTH_LONG).show()
        //} else Toast.makeText(this.context, "No hay Red", Toast.LENGTH_LONG).show()
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

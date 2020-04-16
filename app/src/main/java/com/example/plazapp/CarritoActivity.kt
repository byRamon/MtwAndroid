package com.example.plazapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.JsonReader
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.plazapp.MainActivity.Companion.LOG_TAG
import com.example.plazapp.data.Items
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import kotlinx.android.synthetic.main.activity_carrito.*
import org.json.JSONArray

class CarritoActivity : AppCompatActivity() {

    var lstNombres:ArrayList<String>? = null
    var lista: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        lstNombres = obtenerNombres(ItemsActivity.lstitems)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, lstNombres!!)
        lst_carrito.adapter = adaptador

        val gson = Gson()
        var jsonUsuario: String = gson.toJson(MainActivity.usuario)
        val jsonProductos: String = gson.toJson(ItemsActivity.lstitems.orEmpty())
        val jsonPedido = jsonUsuario.replace("}", ",\"productos\":$jsonProductos}", true)

        btnPedido.setOnClickListener {
            jsonArrayRequestPost(jsonPedido)
            var intent = Intent(this, Fragmento::class.java)
            startActivity(intent)
        }
    }

    fun obtenerNombres(list:ArrayList<Items>?):ArrayList<String>{
        val nombres = ArrayList<String>()
        for(item in list.orEmpty()){
            nombres.add(item.nombre)
        }
        return  nombres
    }
    fun jsonArrayRequestPost(pedido:String) {
        Log.i(LOG_TAG, "jsonArrayRequestPost")
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.0.7:8080/Apiproyecto/post.php"
        //val gsonPretty = GsonBuilder().setPrettyPrinting().create()
        //val jsonTutsListPretty: String = gsonPretty.toJson(ItemsActivity.lstitems)
        //Log.i(LOG_TAG, jsonTutsListPretty)
        val jsonArray = JSONArray()
        //jsonArray.put(jsonObj)

        jsonArray.put(1)
        jsonArray.put(2)
        jsonArray.put(3)
        // Request a JSONArray response from the provided URL.
        //val jsonArrayRequest = JsonObjectRequest(
        //    Request.Method.POST, url, json,
        val jsonArrayRequest = JsonArrayRequest(
            Request.Method.POST, url, jsonArray,
            Response.Listener { response ->
                Log.i(LOG_TAG, "Response POST  is: $response")
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Log.e(LOG_TAG, "Response POST didn't work!")
            }
        )

        val postRequest = object : StringRequest(Request.Method.POST, url,
            Response.Listener { response ->
                // response
                Log.d(LOG_TAG, response)
            },
            Response.ErrorListener {
                it.printStackTrace()
                Log.e(LOG_TAG, "Response POST didn't work!")
            }
        ) {
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params["pedido"] = pedido
                return params
            }
        }
        // Add the request to the RequestQueue.
        queue.add(postRequest)
        //queue.add(jsonArrayRequest)
    }
}

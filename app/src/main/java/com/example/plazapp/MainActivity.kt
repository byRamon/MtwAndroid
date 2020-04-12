package com.example.plazapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.plazapp.api.*
import com.example.plazapp.data.*
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity(), CompletadoListener {
    companion object {
        val LOG_TAG = "@DEV"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        doAsync{
            var pedido = Pedidos()
            pedido.objeto = "pera"
            pedido.cantidad = 100.0
            pedido.unidades = "gr"
            DataBase.getInstance(this@MainActivity)?.pedidosDAO()?.insert(pedido)
            val lstPedidos = DataBase.getInstance(this@MainActivity)?.pedidosDAO()?.selectAll()
            runOnUiThread{
                lstPedidos?.get(lstPedidos?.size - 1)?.let { populateUI(it) }
            }
        }.execute()


            if (Network.hayRed(this)) {
                DescargaURL(this).execute("http://www.google.com")
                if (Network.hayRed(this)) {
                    jsonObjectRequest()
                    jsonArrayRequest()
                } else
                    Toast.makeText(this, "No hay Red", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(this, "No hay Red", Toast.LENGTH_LONG).show()
        /*
        var queue = Volley.newRequestQueue(this)
        var url = "http://localhost:8080/ApiCoronavirus/post.php"
        val request = JsonObjectRequest(url, null,
            Response.Listener{ response ->
                Log.i("@DEV", "Response is: $response")
            }, Response.ErrorListener { error ->
                error.printStackTrace()
            })
        queue.add(request)
         */
    }
    private fun solicitudHttpVolley(url:String){
        val queue = Volley.newRequestQueue(this)
        val solicitud = StringRequest(
            Request.Method.GET, url,
            Response.Listener <String> {
                    response ->
                try{
                    Log.i(LOG_TAG, response)
                }
                catch (e: Exception){
                    Log.e(LOG_TAG, "That didn't work log!")
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Log.e(LOG_TAG, "That didn't work!")
            }
        )
        queue.add(solicitud)
    }
    override fun descargaCompleta(resultado: String) {
        Log.i("@DEV", resultado)
    }
    fun populateUI(pedido: Pedidos)
    {
        tvTexto.text = pedido.id.toString() + " "  + pedido.objeto + " " + pedido.unidades
    }
    fun jsonObjectRequest() {
        Log.i(LOG_TAG, "jsonObjectRequest")

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        //val url = "https://jsonplaceholder.typicode.com/posts/1"
        val url = "http://192.168.0.7:8080/ApiCoronavirus/post.php?id=047f6d05-78e9-11ea-b737-94e979ecb4f6"

        // Request a JSONObject response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest( url, null,
            Response.Listener { response ->
                Log.i(LOG_TAG, "Response is: $response")
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Log.e(LOG_TAG, "That didn't work!")
            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    /**
     * Llamada POST que envia un JSONObject y devuelve un JSONobject
     */
    fun jsonObjectRequestPost() {
        Log.i(LOG_TAG, "jsonObjectRequestPost")
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://jsonplaceholder.typicode.com/posts/1"
        val jsonObject = JSONObject()
        jsonObject.put("id", 1)
        jsonObject.put("title", "Hello K")

        // Request a JSONObject response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(url, jsonObject,
            Response.Listener { response ->
                Log.i(LOG_TAG, "Response is: $response")
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Log.e(LOG_TAG, "That didn't work!")
            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    fun jsonArrayRequest() {
        Log.i(LOG_TAG, "jsonArrayRequest")
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.0.7:8080/ApiCoronavirus/post.php"//?paises=true"
        // Request a JSONArray response from the provided URL.
        val jsonArrayRequest = JsonArrayRequest(url,
            Response.Listener { array ->
                Log.i(LOG_TAG, "Response is: $array")
                Log.i(LOG_TAG, "Hay : ${array.length()}")
                //var array = response.getJSONArray(1)
                for (i in 0 until array.length()){
                    var pais = array.getJSONObject(i)
                    Log.i(LOG_TAG, "Pais is : ${pais.getString("pais")}")
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Log.e(LOG_TAG, "That didn't work!")
            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest)
    }

    fun jsonArrayRequestPost() {
        Log.i(LOG_TAG, "jsonArrayRequestPost")

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        val url = "https://jsonplaceholder.typicode.com/posts"

        val jsonArray = JSONArray()
        jsonArray.put(1)
        jsonArray.put(2)
        jsonArray.put(3)

        // Request a JSONArray response from the provided URL.
        val jsonArrayRequest = JsonArrayRequest(Request.Method.POST, url, jsonArray,
            Response.Listener { response ->
                Log.i(LOG_TAG, "Response is: $response")
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Log.e(LOG_TAG, "That didn't work!")
            }
        )

        // Add the request to the RequestQueue.
        queue.add(jsonArrayRequest)
    }
}

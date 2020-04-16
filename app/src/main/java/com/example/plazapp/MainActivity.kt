package com.example.plazapp

import android.content.Intent
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
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*


class MainActivity : AppCompatActivity(), CompletadoListener {
    companion object {
        val LOG_TAG = "@DEV"
        var usuario:Usuario? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val action:String? = intent.getStringExtra("action")
        var dbUsuario = Usuarios()
        //jsonArrayRequest()
        if( action != null){
            Log.i(LOG_TAG, action)
            val lstUsuarios = DataBase.getInstance(this@MainActivity)?.usuariosDAO()?.selectAll()
            lstUsuarios?.get(lstUsuarios?.size - 1)?.let {
                dbUsuario =it
            }
            if(action == "salir") {
                DataBase.getInstance(this@MainActivity)?.usuariosDAO()?.delete(dbUsuario)
            }
            else{
                populateUI(dbUsuario)
            }
        }else {
            Log.i(LOG_TAG, "no hay valor")
        }

        btn_acceso.setOnClickListener{
            usuario = Usuario(ev_nombre.text.toString(), ev_direccion.text.toString(), ev_telefono.text.toString())
            doAsync{
                val localDateTime = LocalDateTime.now()
                val date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant())
                dbUsuario.nombre = ev_nombre.text.toString()
                dbUsuario.telefono = ev_telefono.text.toString()
                dbUsuario.direccion = ev_direccion.text.toString()
                dbUsuario.actualizado = date
                if(dbUsuario.id < 1){
                    dbUsuario.creado = date
                    DataBase.getInstance(this@MainActivity)?.usuariosDAO()?.insert(dbUsuario)
                }
                else{
                    DataBase.getInstance(this@MainActivity)?.usuariosDAO()?.update(dbUsuario)
                }
                val lstUsuarios = DataBase.getInstance(this@MainActivity)?.usuariosDAO()?.selectAll()
                runOnUiThread{
                    lstUsuarios?.get(lstUsuarios?.size - 1)?.let { populateUI(it) }
                }
            }.execute()
            val intent = Intent(this, Fragmento::class.java)
            startActivity(intent)
        }
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
    fun populateUI(usuario: Usuarios)
    {
        ev_nombre.setText(usuario.nombre)
        ev_telefono.setText(usuario.telefono)
        ev_direccion.setText(usuario.direccion)
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

    fun stringRequest() {
        Log.i(LOG_TAG, "stringRequest")

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)

        val url = "https://kikopalomares.com"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(url,
            Response.Listener<String> { response ->
                // Display the first 100 characters of the response string.
                Log.i(LOG_TAG, "Response is: ${response.substring(0, 100)}")
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
                Log.e(LOG_TAG, "That didn't work!")
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
    fun jsonArrayRequest() {
        if (Network.hayRed(this)) {
            DescargaURL(this).execute("http://www.google.com")
            if (Network.hayRed(this)) {
                // Instantiate the RequestQueue.
                val queue = Volley.newRequestQueue(this)
                val url = "http://192.168.0.7:8080/Apiproyecto/post.php"//?productos=true&idtienda=B4B2A879-7EBF-11EA-B2E7-94E979ECB4F6"
                // Request a JSONArray response from the provided URL.
                val jsonArrayRequest = JsonArrayRequest(url,
                    Response.Listener { array ->
                        Log.i(LOG_TAG, "Response is: $array")
                        Log.i(LOG_TAG, "Hay : ${array.length()}")
                        //var array = response.getJSONArray(1)
                        for (i in 0 until array.length()){
                            var obj = array.getJSONObject(i)
                            Log.i(LOG_TAG, "nombre de la tienda : ${obj.getString("nombre")}")
                        }
                    },
                    Response.ErrorListener { error ->
                        error.printStackTrace()
                        Log.e(LOG_TAG, "Api no accesible!")
                    }
                )
                // Add the request to the RequestQueue.
                queue.add(jsonArrayRequest)
            } else
                Toast.makeText(this, "No hay Red", Toast.LENGTH_LONG).show()
        } else
            Toast.makeText(this, "No hay Red", Toast.LENGTH_LONG).show()
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

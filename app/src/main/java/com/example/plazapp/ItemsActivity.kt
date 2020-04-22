package com.example.plazapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.plazapp.data.*
import kotlinx.android.synthetic.main.activity_items.*

class ItemsActivity : AppCompatActivity() {
    companion object{
        var lstitems:ArrayList<Items>? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)
        lstitems = ArrayList()
        recycler.layoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        lstitems = ArrayList()
        val idTienda:String = intent.getStringExtra("idTienda")
        jsonArrayRequest(idTienda)
        btn_Carrito.setOnClickListener {
            var intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }
    }
    fun jsonArrayRequest(idTienda:String) {
        //if (Network.hayRed(this.contex)) {
        //        DescargaURL(this.context).execute("http://www.google.com")
        //        if (Network.hayRed(this.context)) {
        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this.baseContext)
        val url = "http://192.168.64.2/ApiProyecto/post.php?productos=true&idtienda=$idTienda"
        //Log.i(MainActivity.LOG_TAG, "Url is: $url")
        // Request a JSONArray response from the provided URL.
        val jsonArrayRequest = JsonArrayRequest(url,
            Response.Listener { array ->
                //Log.i(MainActivity.LOG_TAG, "Response is: $array")
                //Log.i(MainActivity.LOG_TAG, "Hay : ${array.length()}")
                val models = ArrayList<Items>()
                //var array = response.getJSONArray(1)
                for (i in 0 until array.length()){
                    var obj = array.getJSONObject(i)
                    models.add(Items(obj.getString("id"), obj.getString("nombre"), obj.getString("descripcion"), obj.getString("thumbnail")))
                    //lstitems.add(Items(0,"Chiles en nogada", "El chile en nogada es uno de los platillos típicos de la gastronomía del estado de Puebla", 0))
                }
                val adapter= Adapter(models)
                recycler.adapter = adapter
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

//Juan Menu  Lista de Productos

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
       menuInflater.inflate(R.menu.menu1,menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mnudatos-> datos()
            R.id.btnSalir-> Salir()
            R.id.mnuListaPlaza-> ListaPlaza()
            else -> super.onOptionsItemSelected(item)
        }

    }
    fun ListaPlaza():Boolean{
        val intent = Intent( this, Fragmento::class.java)
        startActivity(intent)
        return true
    }

    fun Salir():Boolean{
        val intent = Intent( this, MainActivity::class.java)
        intent.putExtra("action","salir")
        startActivity(intent)
        return true
    }
    fun datos():Boolean{
        val intent = Intent( this, MainActivity::class.java)
        intent.putExtra("action","modificar")
        startActivity(intent)
        return true
    }

}

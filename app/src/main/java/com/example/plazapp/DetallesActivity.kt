package com.example.plazapp

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.graphics.Typeface;
import kotlinx.android.synthetic.main.fragment_lsttiendas.*


class DetallesActivity : AppCompatActivity() {


@Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles)
        if(resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish()
            return
        }


        if(savedInstanceState == null){
            var frgDetalles = ContenidoFragment()
            frgDetalles.arguments = intent.extras
            supportFragmentManager.beginTransaction().add(R.id.detalles, frgDetalles).commit()
        }
    }

//Juan Menu Detalle de la tienda

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

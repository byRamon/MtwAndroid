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

//Juan Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu1,menu)
    menuInflater.inflate(R.menu.menu1,menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mnudetalle-> detalle()
            R.id.mnuAcerca-> acercaDe()
            R.id.btnSalir-> Item2()
            R.id.mnudatos->  datos()
            else -> super.onOptionsItemSelected(item)
        }

    }
    fun acercaDe():Boolean{
        val intent = Intent( this, Fragmento::class.java)
        startActivity(intent)
        return true
    }

    fun datos():Boolean{
        val intent = Intent( this, MainActivity::class.java)
        val intent = Intent( this, Fragmento::class.java)
        startActivity(intent)
        return true
    }

    fun Item2():Boolean{
        val intent = Intent( this, MainActivity::class.java)
        startActivity(intent)
        return true
    }
    fun detalle():Boolean{
        val intent = Intent( this, MainActivity::class.java)
        startActivity(intent)
        return true
    }
}

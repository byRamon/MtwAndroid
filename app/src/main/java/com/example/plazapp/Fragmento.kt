package com.example.plazapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class Fragmento : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmento)
    }

//Juan Menu Lista de plazas
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
    //menuInflater.inflate(R.menu.menu1,menu)

    menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mnudatos-> datos()
            R.id.mnuListaPlaza-> ListaPlaza()
            R.id.btnSalir-> salir()
            else -> super.onOptionsItemSelected(item)
        }

    }
    fun ListaPlaza():Boolean{
        val intent = Intent( this, Fragmento::class.java)
        startActivity(intent)
        return true
    }

    fun salir():Boolean{
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

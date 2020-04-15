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

//Juan Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {

    menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mnuAcerca-> acercaDe()
            R.id.btnSalir-> Item2()
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
        startActivity(intent)
        return true
    }

    fun Item2():Boolean{
        val intent = Intent( this, MainActivity::class.java)
        startActivity(intent)
        return true
    }
}

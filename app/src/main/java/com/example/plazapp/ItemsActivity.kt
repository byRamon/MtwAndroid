package com.example.plazapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
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
        val models =ArrayList<Items>()
        models.add(Items(0,"Chiles en nogada", "El chile en nogada es uno de los platillos típicos de la gastronomía del estado de Puebla", 0))
        models.add(Items(1,"Ensalada", "Un plato frío de varias verduras cortadas, mezcladas y aderezadas, fundamentalmente con sal, aceite vegetal y vinagre", 0))
        models.add(Items(2,"Pavo", "El pavo relleno es el principal protagonista de la cena de Acción de Gracias y, por ello, te enseñamos a cocinar la auténtica receta americana", 0))
        models.add(Items(3,"Pozole","El pozole es un plato tradicional mesoamericano, un caldo hecho a base de granos de maíz de un tipo conocido comúnmente como cacahuazintle, a la que se agrega, según la región, carne de pollo o de cerdo como ingrediente secundario", 0))//R.drawable.pozole))
        models.add(Items(4,"Tacos","Es considerado como uno de los platillos más representativos de la comida mexicana\u200B\u200B." , 0))
        val adapter= Adapter(models)
        recycler.adapter = adapter

        btn_Carrito.setOnClickListener {
            var intent = Intent(this, CarritoActivity::class.java)
            startActivity(intent)
        }
    }

//Juan Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu1,menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.mnudetalle-> detalle()
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

package com.example.plazapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.plazapp.data.Items
import kotlinx.android.synthetic.main.activity_carrito.*

class CarritoActivity : AppCompatActivity() {

    var lstNombres:ArrayList<String>? = null
    var lista: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        lstNombres = obtenerNombres(ItemsActivity.lstitems)
        val adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, lstNombres!!)
        lst_carrito.adapter = adaptador

        btnPedido.setOnClickListener {
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
}

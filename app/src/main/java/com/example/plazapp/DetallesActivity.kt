package com.example.plazapp

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetallesActivity : AppCompatActivity() {

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
}

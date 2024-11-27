package com.upiiz.exam_02_agoh

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonRecyclerView = findViewById<Button>(R.id.button1)
        val buttonCreditos = findViewById<Button>(R.id.button2)
        val buttonSalir = findViewById<Button>(R.id.button3)

        // Botón para navegar a RecycleViewList_Activity
        buttonRecyclerView.setOnClickListener {
            val intent = Intent(this, UsersActivity::class.java)
            startActivity(intent)
        }

        // Botón para navegar a Creditos_Activity
        buttonCreditos.setOnClickListener {
            val intent = Intent(this, Creditos_Activity::class.java)
            startActivity(intent)
        }

        // Botón para salir de la aplicación
        buttonSalir.setOnClickListener {
            finishAffinity() // Cierra todas las actividades y sale de la aplicación
        }
    }
}
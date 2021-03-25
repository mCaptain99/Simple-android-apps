package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val lastWin: String? = intent.getStringExtra("lastWin")
        if(lastWin != null && !lastWin.isEmpty()) {
            Toast.makeText(this, "last winner is: $lastWin", 1000).show()
        }
    }

    fun game3x3(view: View) {
        val intent = Intent(this, Game3x3Activity::class.java)
        startActivity(intent)
    }
    fun game5x5(view: View) {
        val intent = Intent(this, Game5x5Activity::class.java)
        startActivity(intent)
    }

}
package com.example.tictactoe

import android.os.Bundle
import com.example.tictactoe.databinding.Game3x3Binding

class Game3x3Activity : GameActivity() {

    lateinit var binding: Game3x3Binding
    override var gameLogic: GameLogic = Game3x3Logic()
    override var tags = listOf("00", "01", "02", "10", "11", "12", "20", "21", "22")
    override var lastWin = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Game3x3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

}
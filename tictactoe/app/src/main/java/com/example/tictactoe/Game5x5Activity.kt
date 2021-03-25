package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import com.example.tictactoe.databinding.Game5x5Binding

class Game5x5Activity : GameActivity() {

    lateinit var binding: Game5x5Binding
    override var gameLogic: GameLogic = Game5x5Logic()
    override var tags = listOf("00", "01", "02", "03", "04", "10", "11", "12", "13", "14",
        "20", "21", "22", "23", "24", "30", "31", "32", "33", "34", "40", "41", "42", "43", "44")
    override var lastWin = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Game5x5Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}
package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


abstract class GameActivity : AppCompatActivity() {

    open var lastWin: String = ""
    open var gameLogic: GameLogic = Game3x3Logic()
    open var tags = listOf("00", "01", "02", "10", "11", "12", "20", "21", "22")

    fun handleWin(tour: String){
        Toast.makeText(this, "Player " + tour + " won!", 1000).show()
        lastWin = tour;
        var pointsXVal = findViewById<TextView>(R.id.pointsXVal)
        var pointsOVal = findViewById<TextView>(R.id.pointsOVal)
        if(tour == "X") {
            pointsXVal.text = (Integer.parseInt(pointsXVal.text.toString()) + 1).toString()
        }
        else{
            pointsOVal.text = (Integer.parseInt(pointsOVal.text.toString()) + 1).toString()
        }
    }

    fun handleDraw() {
        lastWin = "draw"
        Toast.makeText(this, "Draw!", 1000).show()
    }

    fun play(field: String){
        var message = gameLogic.play(field)
        if(message.equals("X") || message.equals("O")){
            handleWin(message)
            updateView()
        }
        else if(message.equals("DRAW")){
            handleDraw()
            updateView()
        }
    }

    private fun updateView() {
        for(i in tags) {
            var button = findViewById<TableLayout>(R.id.board).findViewWithTag<Button>(i)
            button.text = gameLogic.fields[i]
        }
    }

    fun handleClick(view: View) {
        for(i in tags) {
            System.out.println(i)
            var btn = findViewById<TableLayout>(R.id.board).findViewWithTag<Button>(i)
            if(view.id == btn.id) {
                play(i)
                btn.text = gameLogic.fields[i]
                return
            }
        }
    }

    fun goToMenu(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("lastWin", lastWin)
        startActivity(intent)
    }
}
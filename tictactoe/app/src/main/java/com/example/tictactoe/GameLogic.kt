package com.example.tictactoe

import java.util.*


abstract class GameLogic {
    open var tour = "X"
    open var tours = listOf("X", "O")

    open var fields = mutableMapOf("00" to "")

    fun play(field: String) : String{
        var result = ""
        if(fields[field]?.isEmpty()!!){
            fields[field] = tour
        }
        if(checkWin()){
            cleanBoard()
            return tour
        }
        if(checkDraw()){
            cleanBoard();
            return "DRAW"
        }
        changeTour()
        return result
    }

    abstract fun checkWin(): Boolean

    fun cleanBoard(){
        var random = Random()
        var next: Int = random.nextInt(2)
        tour = tours[next]
        for(field in fields){
            fields[field.key] = ""
        }
    }

    fun changeTour(){
        if(tour == "X"){
            tour = "O"
        }
        else{
            tour = "X"
        }
    }

    fun checkDraw(): Boolean{
        for(field in fields){
            if(fields[field.key]?.isEmpty()!!) {
                return false
            }
        }
        return true
    }
}
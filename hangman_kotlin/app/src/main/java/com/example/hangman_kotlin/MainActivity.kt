package com.example.hangman_kotlin

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hangman_kotlin.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var password: String? = null
    private var attempts = 0

    private val images = listOf(R.drawable.hangman0, R.drawable.hangman1, R.drawable.hangman2,
        R.drawable.hangman3, R.drawable.hangman4, R.drawable.hangman5, R.drawable.hangman6,
    R.drawable.hangman7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        roll();
    }

    fun randomPassword(): String {
        val passwords: Array<String> = resources.getStringArray(R.array.words_array)
        val random = java.util.Random()
        val r = random.nextInt(passwords.size)
        return passwords[r]
    }

    private fun rollKeyboard(){
        for(i in listOf("A","B","C","D","E","F","G","H","I","J",
                "K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z")) {
            var button = binding.table.findViewWithTag<Button>(i)
            button.isClickable = true
            button.setBackgroundColor(Color.parseColor("#efefef"))
        }
    }

    private fun roll() {
        val passwordView = binding.textToGuess
        password = randomPassword()
        val hiddenPassword = StringBuilder()
        for (i in 0 until this.password!!.length) {
            if (this.password!!.get(i) != ' ') {
                hiddenPassword.append('*')
            } else {
                hiddenPassword.append(" ")
            }
        }
        passwordView.text = hiddenPassword.toString()
        attempts = 0
        binding.imageView.setImageResource(images[0])
        rollKeyboard()
    }

    fun onLetterSubmit(view: View) {
        var button = findViewById<Button>(view.getId())
        val letterString = button.text.toString().toLowerCase()
        button.isClickable = false
        button.setBackgroundColor(Color.parseColor("#9f9f9f"))
        val letter = letterString[0]
        val guessed = ArrayList<Int>()
        for (i in this.password?.indices!!) {
            if (letter == this.password?.get(i)) {
                guessed.add(i)
            }
        }
        if (guessed.isEmpty()) {
            incorrect()
        } else {
            letterCorrect(guessed)
        }
    }

    fun onPasswordSubmit(view: View?) {
        val passwordInput = binding.guessPasswordInput
        val password = passwordInput.text.toString()
        if (password == this.password) {
            passwordCorrect()
        } else {
            incorrect()
        }
    }

    fun passwordCorrect() {
        Toast.makeText(
            this, String.format(
                "Congratulation! You Won"
            ),
            Toast.LENGTH_SHORT
        ).show()
        roll()
    }

    fun incorrect() {
        attempts += 1
        if (attempts >= 8){
            Toast.makeText(this,"Sorry, you lost :(\nThe password was: ${password}", 2000).show()
            roll()
            return
        }
        changeImage();
    }

    private fun changeImage() {
        binding.imageView.setImageResource(images[attempts])
    }

    fun letterCorrect(guessed: ArrayList<Int>) {
        val passwordView = binding.textToGuess
        val oldPassword = passwordView.text.toString()
        val newPassword = StringBuilder()
        for (i in oldPassword.indices) {
            println(i)
            if (guessed.contains(i)) {
                this.password?.let { newPassword.append(it.get(i)) }
            } else {
                newPassword.append(oldPassword[i])
            }
        }
        passwordView.text = newPassword.toString()
        checkIfPasswordGuessed()
    }

    private fun checkIfPasswordGuessed() {
        if(!binding.textToGuess.text.contains('*')){
            passwordCorrect()
        }
    }

}
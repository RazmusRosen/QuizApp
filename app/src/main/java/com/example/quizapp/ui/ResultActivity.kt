package com.example.quizapp.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.MainActivity
import com.example.quizapp.R

class ResultActivity : AppCompatActivity() {
    private lateinit var finishButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val userName = intent.getStringExtra("Username")
        val amountOfQuestions = intent.getIntExtra("Amount of questions", 0)
        val correctAnswers = intent.getIntExtra("Correct answers", 0)

        val textViewResult = findViewById<TextView>(R.id.textViewResult)
        val textViewName = findViewById<TextView>(R.id.textViewName)
        val textViewScore = findViewById<TextView>(R.id.textViewScore)

        textViewResult.text = getString(R.string.result)
        textViewName.text = userName
        textViewScore.text =
            getString(R.string.your_score_is_out_of, correctAnswers, amountOfQuestions)

        finishButton = findViewById(R.id.button_finish)
        finishButton.setOnClickListener {
            Intent(this@ResultActivity, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }
    }
}
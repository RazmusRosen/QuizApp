package com.example.quizapp.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.quizapp.R
import com.example.quizapp.model.Question
import com.example.quizapp.utils.Constants

class QuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var progressBar: ProgressBar
    private lateinit var textViewProgress: TextView
    private lateinit var textViewQuestion: TextView
    private lateinit var imageViewFlag: ImageView

    private lateinit var textViewOptionOne: TextView
    private lateinit var textViewOptionTwo: TextView
    private lateinit var textViewOptionThree: TextView
    private lateinit var textViewOptionFour: TextView

    private lateinit var buttonCheck: Button

    private var questionsCounter: Int = 1
    private lateinit var questionsList: MutableList<Question>
    private var selectedOptionPosition = 0
    private lateinit var currentQuestion: Question
    private var answered = false

    private var correctAnswers: Int = 0
    private var userName: String = "Username"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_questions)

        userName = intent.getStringExtra("Username").toString()

        progressBar = findViewById(R.id.progress_bar)
        textViewProgress = findViewById(R.id.text_view_progress)
        textViewQuestion = findViewById(R.id.question_text_view)
        imageViewFlag = findViewById(R.id.image_flag)

        textViewOptionOne = findViewById(R.id.text_view_option_one)
        textViewOptionTwo = findViewById(R.id.text_view_option_two)
        textViewOptionThree = findViewById(R.id.text_view_option_three)
        textViewOptionFour = findViewById(R.id.text_view_option_four)

        textViewOptionOne.setOnClickListener(this)
        textViewOptionTwo.setOnClickListener(this)
        textViewOptionThree.setOnClickListener(this)
        textViewOptionFour.setOnClickListener(this)

        buttonCheck = findViewById(R.id.button_check)
        buttonCheck.setOnClickListener(this)

        questionsList = Constants.getQuestions()
        Log.d("QuestionSize", "${questionsList.size}")

        showNextQuestion()

    }

    private fun showNextQuestion() {
        currentQuestion = questionsList[questionsCounter - 1]

        imageViewFlag.setImageResource(currentQuestion.image)
        progressBar.progress = questionsCounter
        textViewProgress.text =
            getString(R.string.progress_format, questionsCounter, progressBar.max)
        textViewQuestion.text = currentQuestion.question
        textViewOptionOne.text = currentQuestion.optionOne
        textViewOptionTwo.text = currentQuestion.optionTwo
        textViewOptionThree.text = currentQuestion.optionThree
        textViewOptionFour.text = currentQuestion.optionFour

        if (questionsCounter == questionsList.size) {
            buttonCheck.text = getString(R.string.finish)
        } else {
            buttonCheck.text = getString(R.string.button_check)
        }
        resetOptions()
        answered = false
    }

    private fun resetOptions() {
        val options = listOf<TextView>(
            textViewOptionOne, textViewOptionTwo,
            textViewOptionThree, textViewOptionFour
        )

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this,
                R.drawable.default_option_border_bg
            )
        }
        textViewOptionOne.isClickable = true
        textViewOptionTwo.isClickable = true
        textViewOptionThree.isClickable = true
        textViewOptionFour.isClickable = true
    }

    private fun selectedOption(textView: TextView, selectedOptionNumber: Int) {
        resetOptions()

        selectedOptionPosition = selectedOptionNumber

        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(
            this,
            R.drawable.selected_option_border_bg
        )
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.text_view_option_one -> {
                selectedOption(textViewOptionOne, 1)
            }

            R.id.text_view_option_two -> {
                selectedOption(textViewOptionTwo, 2)
            }

            R.id.text_view_option_three -> {
                selectedOption(textViewOptionThree, 3)
            }

            R.id.text_view_option_four -> {
                selectedOption(textViewOptionFour, 4)
            }

            R.id.button_check -> {
                if (!answered) {
                    checkAnswer()
                } else if(questionsCounter > questionsList.size) {
                    Intent(this@QuestionsActivity, ResultActivity::class.java).also {
                        it.putExtra("Amount of questions", questionsList.size)
                        it.putExtra("Correct answers", correctAnswers)
                        it.putExtra("Username", userName)
                        startActivity(it)
                        finish()
                    }
                } else {
                    showNextQuestion()
                }
                selectedOptionPosition = 0
            }
        }
    }

    private fun checkAnswer() {
        answered = true
        val wrongHighlight = ContextCompat.getDrawable(
            this,
            R.drawable.wrong_option_border_bg
        )
        if (selectedOptionPosition == currentQuestion.correctAnswer) {
            correctAnswers++
            // showCorrectAnswer() before there was a switch statement but now no need
        } else {
            when (selectedOptionPosition) {
                1 -> textViewOptionOne.background = wrongHighlight
                2 -> textViewOptionTwo.background = wrongHighlight
                3 -> textViewOptionThree.background = wrongHighlight
                4 -> textViewOptionFour.background = wrongHighlight
            }
            textViewOptionOne.isClickable = false
            textViewOptionTwo.isClickable = false
            textViewOptionThree.isClickable = false
            textViewOptionFour.isClickable = false
            buttonCheck.text = getString(R.string.button_next)
        }
        questionsCounter++
        showCorrectAnswer()
    }

    private fun showCorrectAnswer() {
        val correctHighlight = ContextCompat.getDrawable(
            this,
            R.drawable.correct_option_border_bg
        )
        when (currentQuestion.correctAnswer) {
            1 -> textViewOptionOne.background = correctHighlight
            2 -> textViewOptionTwo.background = correctHighlight
            3 -> textViewOptionThree.background = correctHighlight
            4 -> textViewOptionFour.background = correctHighlight
        }
    }
}
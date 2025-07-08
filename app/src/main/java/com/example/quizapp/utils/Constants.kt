package com.example.quizapp.utils

import com.example.quizapp.R
import com.example.quizapp.model.Question

object Constants {
    fun getQuestions(): MutableList<Question> {
        val question = mutableListOf<Question>()

        val quest1 = Question(
            1, "What country does this flag belong to?",
            R.drawable.italy_flag,
            "Italy", "France", "Germany", "Bulgaria",
            1)
        question.add(quest1)

        val quest2 = Question(
            2, "What country does this flag belong to?",
            R.drawable.france_flag,
            "Italy", "France", "Germany", "Spain",
            2)
        question.add(quest2)

        val quest3 = Question(
            3, "What country does this flag belong to?",
            R.drawable.germany_flag,
            "Italy", "France", "Germany", "Spain",
            3)
        question.add(quest3)

        val quest4 = Question(
            4, "What country does this flag belong to?",
            R.drawable.spain_flag,
            "Italy", "France", "Denmark", "Spain",
            4)
        question.add(quest4)

        val quest5 = Question(
            5, "What country does this flag belong to?",
            R.drawable.india_flag,
            "Greece", "France", "Germany", "India",
            4)
        question.add(quest5)

        val quest6 = Question(
            6, "What country does this flag belong to?",
            R.drawable.finland_flag,
            "Italy", "France", "Finland", "Spain",
            3)
        question.add(quest6)

        val quest7 = Question(
            7, "What country does this flag belong to?",
            R.drawable.indonesia_flag,
            "Mongolia", "Indonesia", "Germany", "Spain",
            2)
        question.add(quest7)

        val quest8 = Question(
            8, "What country does this flag belong to?",
            R.drawable.lithuania_flag,
            "Lithuania", "France", "Iran", "Sweden",
            1)
        question.add(quest8)

        val quest9 = Question(
            9, "What country does this flag belong to?",
            R.drawable.argentina_flag,
            "Argentina", "France", "Germany", "Spain",
            1)
        question.add(quest9)

        val quest10 = Question(
            10, "What country does this flag belong to?",
            R.drawable.brazil_flag,
            "China", "Brazil", "Germany", "Spain",
            2)
        question.add(quest10)

        val quest11 = Question(
            11, "What country does this flag belong to?",
            R.drawable.poland_flag,
            "Canada", "France", "Germany", "Poland",
            4)
        question.add(quest11)
        return question
    }
}
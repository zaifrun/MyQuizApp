package com.example.quizapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionAnswer(var question: String = "", var answer:String = "") : Parcelable

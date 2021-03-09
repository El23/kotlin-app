package com.example.myapplicationtodo

import android.icu.text.CaseMap

data class Todo(
    val title: String,
    val isCheked: Boolean = false
)
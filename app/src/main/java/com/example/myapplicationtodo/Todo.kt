package com.example.myapplicationtodo

import android.icu.text.CaseMap

data class Todo(
    val title: String,
    var isChecked: Boolean = false
)
package com.moni.scoreapp.utils

object Validators {
    fun validateNameOrLastname(name: String): Boolean {
        val regex = "^[a-zA-Z\\s]{2,50}$".toRegex()
        return regex.matches(name)
    }

    fun validateEmail(email: String): Boolean {
        val regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$".toRegex()
        return regex.matches(email)
    }

    fun validateDni(dni: String): Boolean {
        val regex = "^[0-9]{8}$".toRegex()
        return regex.matches(dni)
    }
}
package com.moni.scoreapp.data.local.enums

enum class Genders {
    MALE, FEMALE, OTHER;

    companion object {
        fun displayGender(genderStr: String) = when (genderStr) {
            "Masculino" -> MALE
            "Femenino" -> FEMALE
            "Otro" -> OTHER
            else -> OTHER
        }
    }
}

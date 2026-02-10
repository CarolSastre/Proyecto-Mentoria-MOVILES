package com.example.mentoria.main.domain.model

data class Profile(
    val id: String,
    val name: String,
    val dni: String,
    val photoUrl: String? = null
)
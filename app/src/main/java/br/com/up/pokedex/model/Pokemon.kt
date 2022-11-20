package br.com.up.pokedex.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val url : String,
    val id: Int,
    val name : String,
    val height: Float,
    val weight: Float,
    val types: List<Type>,
)

data class Type(
    val type: Name
)

data class Name(
    val name: String
)
package br.com.up.pokedex.model

data class Pokemon(
    val url : String,
    val id: Int,
    val name : String,
    val height: Float,
    val weight: Float,
    val types: List<Type>,
    val stats: List<Stat>,
    val abilities: List<Ability>,
    val moves: List<Move>
)

data class Name(
    val name: String
)

data class Type(
    val type: Name
)

data class Stat(
    val stat: Name,
    val base_stat: Float
)

data class Ability(
    val ability: Name
)

data class Move(
    val move: Name
)
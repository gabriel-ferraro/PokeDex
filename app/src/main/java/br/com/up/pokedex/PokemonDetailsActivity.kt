package br.com.up.pokedex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import br.com.up.pokedex.network.PokeApi
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.squareup.picasso.Picasso

class PokemonDetailsActivity : AppCompatActivity() {

    private lateinit var pokemonTypes: ChipGroup
    private lateinit var pokemonStats: ChipGroup
    private lateinit var pokemonAbilities: ChipGroup
    private lateinit var pokemonMoves: ChipGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        val pokemon = intent.getStringExtra("pokemon")

        val pokemonImage: ImageView = findViewById(R.id.pokemon_image)
        val pokemonName: TextView = findViewById(R.id.pokemon_name)
        val pokemonHeight: TextView = findViewById(R.id.pokemon_height)
        val pokemonWeight: TextView = findViewById(R.id.pokemon_weight)

        pokemonTypes = findViewById(R.id.pokemon_types)
        pokemonStats = findViewById(R.id.pokemon_stats)
        pokemonAbilities = findViewById(R.id.pokemon_abilities)
        pokemonMoves = findViewById(R.id.pokemon_moves)

        PokeApi().getPokemonByName(pokemon!!){
                pokemon ->

            if(pokemon != null) {

                val id = pokemon.id
                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
                Picasso.get().load(url).into(pokemonImage)

                pokemonName.text = pokemon.name
                pokemonWeight.text = pokemon.weight.toString()
                pokemonHeight.text = pokemon.height.toString()

                pokemon.types.forEach{
                    addChip(it.type.name, pokemonTypes)
                }

                pokemon.stats.forEach{
                    addStatChip(it.stat.name, it.base_stat, pokemonStats)
                }

                pokemon.abilities.forEach{
                    addChip(it.ability.name, pokemonAbilities)
                }

                pokemon.moves.forEach{
                    addChip(it.move.name, pokemonMoves)
                }
            }
        }
    }

    private fun addChip(stat: String, chipGroup: ChipGroup) {
        val chip = Chip(this)
            chip.text = stat

        chipGroup.addView(chip)
    }

    private fun addStatChip(stat: String, value: Float, chipGroup: ChipGroup) {
        val chip = Chip(this)

        chip.text = stat + " " + value

        chipGroup.addView(chip)
    }
}

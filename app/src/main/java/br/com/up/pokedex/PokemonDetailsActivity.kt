package br.com.up.pokedex

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import br.com.up.pokedex.network.PokeApi
import com.squareup.picasso.Picasso

class PokemonDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        val pokemon = intent.getStringExtra("pokemon")

        val namePoke : TextView =  findViewById(R.id.pokeName)
        val pokeImage : ImageView = findViewById(R.id.pokeImg)
        val heightPoke : TextView =  findViewById(R.id.pokeHeight)
        val weightPoke : TextView =  findViewById(R.id.pokeWeight)
        val typesPoke : TextView = findViewById(R.id.pokeTypes)

        PokeApi().getPokemonByName(pokemon!!){
                pokemon ->

            if(pokemon != null) {

                val id = pokemon.id
                val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

                namePoke.text = pokemon.name
                weightPoke.text = pokemon.weight.toString()
                heightPoke.text = pokemon.height.toString()
                pokemon.types.forEach {
                    typesPoke.text = it.type.name + "  " + typesPoke.text
                }

                Picasso.get().load(url).into(pokeImage)
            }
        }

    }

}
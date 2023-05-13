package com.kzeledon.jetpackcomposepokedex.data.models

import androidx.compose.ui.graphics.Color
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeBug
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeDark
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeDragon
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeElectric
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeFairy
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeFighting
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeFire
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeFlying
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeGhost
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeGround
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeIce
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeNormal
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypePoison
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypePsychic
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeRock
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeSteel
import com.kzeledon.jetpackcomposepokedex.ui.theme.TypeWater
import com.kzeledon.jetpackcomposepokedex.ui.theme.lightTeal

data class PokedexListEntry(
    val pokemonName: String,
    val imageUrl: String,
    val number: Int,
    val types: List<PokemonTypes>,
    val color: Color = types.first().color
)

enum class PokemonTypes {
    GRASS,
    POISON,
    FIRE,
    FLYING,
    WATER,
    BUG,
    NORMAL,
    ELECTRIC,
    GROUND,
    FAIRY,
    FIGHTING,
    PSYCHIC,
    ROCK,
    STEEL,
    ICE,
    GHOST,
    DRAGON,
    DARK,
    MONSTER,
    UNKNOWN;

    val color : Color
        get() {
            return when(this) {
                GRASS -> lightTeal
                POISON -> TypePoison
                FIRE -> TypeFire
                FLYING -> TypeFlying
                WATER -> TypeWater
                BUG -> TypeBug
                NORMAL -> TypeNormal
                ELECTRIC -> TypeElectric
                GROUND -> TypeGround
                FAIRY -> TypeFairy
                FIGHTING -> TypeFighting
                PSYCHIC -> TypePsychic
                ROCK -> TypeRock
                STEEL -> TypeSteel
                ICE -> TypeIce
                GHOST -> TypeGhost
                DRAGON -> TypeDragon
                DARK -> TypeDark
                MONSTER -> TypeNormal
                UNKNOWN -> TypeNormal
            }
        }
}

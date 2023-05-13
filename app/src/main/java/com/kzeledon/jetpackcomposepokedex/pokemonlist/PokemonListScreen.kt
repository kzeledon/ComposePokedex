package com.kzeledon.jetpackcomposepokedex.pokemonlist

import android.content.res.Configuration
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dehaze
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.Coil
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kzeledon.jetpackcomposepokedex.R
import com.kzeledon.jetpackcomposepokedex.data.models.PokedexListEntry
import com.kzeledon.jetpackcomposepokedex.data.models.PokemonTypes
import com.kzeledon.jetpackcomposepokedex.ui.theme.blackOpacity
import com.kzeledon.jetpackcomposepokedex.ui.theme.whiteGrey

@Composable
fun pokemonListScreen(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokeball),
                contentDescription = null,
                colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(whiteGrey) else ColorFilter.tint(
                    blackOpacity
                ),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(300.dp)
                    .offset(x = (118).dp, y = (-90).dp)
            )
        }
    }
}

@Composable
fun background() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.pokeball),
            contentDescription = null,
            colorFilter = if (isSystemInDarkTheme()) ColorFilter.tint(whiteGrey) else ColorFilter.tint(blackOpacity),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(300.dp)
                .offset(x = (118).dp, y = (-90).dp)
        )

        Icon(
            imageVector = Icons.Default.Dehaze,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-20).dp, y = 48.dp)
        )

        Text(
            text = "Pokedex",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = if (isSystemInDarkTheme()) Color.White else Color.Black,
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(y = 100.dp)
        )


    }
}

@Composable
fun PokemonCard(
    entry : PokedexListEntry,
    navController: NavController?,
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = hiltViewModel()
) {
    val defaultDominantColor = MaterialTheme.colors.surface
    var dominantColor by remember {
        mutableStateOf(defaultDominantColor)
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(5.dp, RoundedCornerShape(15.dp))
            .clip(RoundedCornerShape(15.dp))
            .background(entry.color)
            .aspectRatio(1.4f)
            .clickable {
                navController?.navigate(
                    "pokemon_detail_screen/${entry.color}/${entry.pokemonName}"
                )
            }
    ) {

        Image(
            painter = painterResource(id = R.drawable.pokeball),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Color.White.copy(alpha = 0.14f)),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(140.dp)
                .offset(x = 6.dp, y = 26.dp)
        )

        AsyncImage(
//            model = entry.imageUrl,
            model =  ImageRequest.Builder(LocalContext.current)
                .data(entry.imageUrl)
                .crossfade(true)
                .target {
                        viewModel.calcDominatColor(it) { color ->
                            dominantColor = color
                        }
                }

                .build(),
            contentDescription = entry.pokemonName,
            placeholder = painterResource(id = R.drawable.bulbasaur),
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.BottomEnd)
                .padding(vertical = 10.dp)
        )

        Column (
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp, 24.dp, 16.dp, 16.dp)
        ){
            Text(
                text = entry.pokemonName,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(10.dp))

            entry.types.take(2).forEach {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White.copy(0.2f))
                        .padding(vertical = 4.dp, horizontal = 12.dp)
                ) {
                    Text(
                        text = it.name.lowercase().capitalize(),
                        color = Color.White,
                        fontSize = 8.sp
                    )
                }

                Spacer(modifier = Modifier.height(5.dp))
            }
        }
    }

}
@Preview(
    widthDp = 200
)
@Composable
fun pokemonCardPreview() {
    PokemonCard(
        entry = PokedexListEntry(
            pokemonName = "Bulbasaur",
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
            number = 1,
            types = listOf(PokemonTypes.GRASS, PokemonTypes.POISON)
        ),
        navController = null
    )
}

@Composable
fun searchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch:(String) -> Unit = {}
) {

    var text by remember {
        mutableStateOf("")
    }

    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }

    Box(modifier = modifier) {
        BasicTextField(
            value = text ,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(MaterialTheme.colors.background, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused
                }

        )
        if(isHintDisplayed) {
            Text(
                text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun showSearchBar() {
    searchBar(
        hint = "Search...",
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun showPreview() {
    background()
}


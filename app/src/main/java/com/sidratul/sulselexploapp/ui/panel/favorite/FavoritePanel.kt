package com.sidratul.sulselexploapp.ui.panel.favorite

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sidratul.sulselexploapp.data.WisataEntity
import com.sidratul.sulselexploapp.model.ViewModelFavorite
import com.sidratul.sulselexploapp.ui.component.AvailableView
import com.sidratul.sulselexploapp.ui.component.EmptyView
import com.sidratul.sulselexploapp.ui.component.ErrorView
import com.sidratul.sulselexploapp.ui.component.Loader
import com.sidratul.sulselexploapp.utils.ResultState

@Composable
fun FavoritePanel(navController: NavController, scaffoldState: ScaffoldState) {
    val viewModelFavorite = hiltViewModel<ViewModelFavorite>()

    viewModelFavorite.allFavoriteTour.collectAsState(ResultState.Loading).value.let { resultState ->
        when (resultState) {
            is ResultState.Loading -> Loader()
            is ResultState.Error -> ErrorView()
            is ResultState.Success -> {
                FavoriteContent(
                    listFavoriteTour = resultState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    onUpdateFavoriteTour = viewModelFavorite::updateFavoriteTour
                )
            }
        }
    }
}

@Composable
fun FavoriteContent(
    listFavoriteTour: List<WisataEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    onUpdateFavoriteTour: (id: Int, isFavorite: Boolean) -> Unit
) {
    when (listFavoriteTour.isEmpty()) {
        true -> EmptyView()
        false -> AvailableView(listFavoriteTour, navController, scaffoldState, onUpdateFavoriteTour)
    }
}
package com.sidratul.sulselexploapp.ui.panel.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sidratul.sulselexploapp.data.WisataEntity
import com.sidratul.sulselexploapp.model.ViewModelHome
import com.sidratul.sulselexploapp.ui.component.*
import com.sidratul.sulselexploapp.utils.ResultState

@Composable
fun HomePanel(navController: NavController, scaffoldState: ScaffoldState) {
    val viewModelHome = hiltViewModel<ViewModelHome>()
    val homeUiState by viewModelHome.homeUiState

    viewModelHome.allTour.collectAsState(ResultState.Loading).value.let { resultState ->
        when (resultState) {
            is ResultState.Loading -> Loader()
            is ResultState.Error -> ErrorView()
            is ResultState.Success -> {
                HomeContent(
                    listTour = resultState.data,
                    navController = navController,
                    scaffoldState = scaffoldState,
                    query = homeUiState.query,
                    onQueryChange = viewModelHome::onQueryChange,
                    onUpdateFavoriteTour = viewModelHome::updateFavoriteTour
                )
            }
        }
    }
}

@Composable
fun HomeContent(
    listTour: List<WisataEntity>,
    navController: NavController,
    scaffoldState: ScaffoldState,
    query: String,
    onQueryChange: (String) -> Unit,
    onUpdateFavoriteTour: (id: Int, isFavorite: Boolean) -> Unit
) {
    Column {
        SearchingBar(query = query, onQueryChange = onQueryChange)
        when (listTour.isEmpty()) {
            true -> EmptyView()
            false -> AvailableView(listTour, navController, scaffoldState, onUpdateFavoriteTour)
        }
    }
}





/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.plavelo.puppy.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import org.plavelo.puppy.domain.PuppyId
import org.plavelo.puppy.presentation.ui.detail.Detail
import org.plavelo.puppy.presentation.ui.home.Home

object MainDestinations {
    const val LIST_ROUTE = "list"
    const val DETAIL_ROUTE = "detail"
    const val DETAIL_ID_KEY = "id"
}

@Composable
fun NavGraph(startDestination: String = MainDestinations.LIST_ROUTE) {
    val navController = rememberNavController()

    val actions = remember(navController) { MainActions(navController) }
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MainDestinations.LIST_ROUTE) { backStackEntry ->
            val viewModel: PuppyViewModel =
                viewModel(factory = HiltViewModelFactory(LocalContext.current, backStackEntry))
            Home(viewModel, actions.select)
        }
        composable(
            "${MainDestinations.DETAIL_ROUTE}/{${MainDestinations.DETAIL_ID_KEY}}",
            arguments = listOf(
                navArgument(MainDestinations.DETAIL_ID_KEY) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val viewModel: PuppyViewModel =
                viewModel(factory = HiltViewModelFactory(LocalContext.current, backStackEntry))
            Detail(
                viewModel,
                PuppyId(
                    arguments.getString(MainDestinations.DETAIL_ID_KEY)
                        ?: throw IllegalArgumentException("An illegal ID provided.")
                ),
                actions.upPress,
            )
        }
    }
}

class MainActions(navController: NavHostController) {
    val select: (PuppyId) -> Unit = { id: PuppyId ->
        navController.navigate("${MainDestinations.DETAIL_ROUTE}/${id.id}")
    }
    val upPress: () -> Unit = {
        navController.navigateUp()
    }
}

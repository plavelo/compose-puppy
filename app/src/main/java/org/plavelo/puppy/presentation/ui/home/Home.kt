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
package org.plavelo.puppy.presentation.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.plavelo.puppy.R
import org.plavelo.puppy.domain.Puppy
import org.plavelo.puppy.domain.PuppyId
import org.plavelo.puppy.presentation.PuppyViewModel

@Composable
fun Home(viewModel: PuppyViewModel, onSelect: (PuppyId) -> Unit) {
    val puppies by viewModel.puppies().collectAsState(initial = emptyList())
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.addRandomPuppy()
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add a random puppy")
            }
        }
    ) { innerPadding ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.padding(innerPadding)
        ) {
            ListItems(puppies, onSelect)
        }
    }
}

@Composable
fun ListItems(puppies: List<Puppy>, onSelect: (PuppyId) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = puppies) { puppy ->
            ListItem(puppy, onSelect = onSelect)
            Divider(color = Color.Black)
        }
    }
}

@Composable
fun ListItem(puppy: Puppy, onSelect: (PuppyId) -> Unit) {
    Text(
        text = "Hello ${puppy.name}!",
        modifier = Modifier
            .clickable(
                onClick = {
                    onSelect(puppy.id)
                }
            )
            .padding(24.dp)
    )
}

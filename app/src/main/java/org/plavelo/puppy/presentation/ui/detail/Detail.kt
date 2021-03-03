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
package org.plavelo.puppy.presentation.ui.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.plavelo.puppy.R
import org.plavelo.puppy.domain.PuppyId
import org.plavelo.puppy.presentation.PuppyViewModel

@Composable
fun Detail(viewModel: PuppyViewModel, id: PuppyId, upPress: () -> Unit) {
    val puppy by viewModel.puppy(id).collectAsState(null)
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = puppy?.name ?: "",
                    )
                },
                navigationIcon = {
                    IconButton(onClick = upPress) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.label_back)
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.padding(innerPadding)
        ) {
            if (puppy != null) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = puppy?.image ?: "",
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                    Text(
                        text = "Name: ${puppy?.name}",
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp),
                        style = MaterialTheme.typography.h5,
                    )
                }
            }
        }
    }
}

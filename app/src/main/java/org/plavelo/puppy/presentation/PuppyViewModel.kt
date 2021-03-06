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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.plavelo.puppy.domain.Puppy
import org.plavelo.puppy.domain.PuppyId
import org.plavelo.puppy.usecase.PuppyUseCase
import javax.inject.Inject

@HiltViewModel
class PuppyViewModel @Inject constructor(
    private val puppyUseCase: PuppyUseCase,
) : ViewModel() {
    fun puppies(): Flow<List<Puppy>> = puppyUseCase.puppies()

    fun puppy(id: PuppyId): Flow<Puppy> = puppyUseCase.puppy(id)

    fun addRandomPuppy() = viewModelScope.launch {
        puppyUseCase.addRandomPuppy()
    }
}

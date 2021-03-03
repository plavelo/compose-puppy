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
package org.plavelo.puppy.usecase

import kotlinx.coroutines.flow.Flow
import org.plavelo.puppy.domain.Puppy
import org.plavelo.puppy.domain.PuppyId
import org.plavelo.puppy.usecase.repository.PuppyRepository
import javax.inject.Inject

class PuppyUseCase @Inject constructor(
    private val puppyRepository: PuppyRepository,
) {
    fun puppies(): Flow<List<Puppy>> = puppyRepository.getAll()

    fun puppy(id: PuppyId): Flow<Puppy> = puppyRepository.get(id)

    suspend fun addRandomPuppy() {
        val puppies = listOf(
            "Abby",
            "Benny",
            "Cinnamon",
            "Davy",
            "Elsa",
            "Frankie",
            "Ginger",
            "Harvey",
            "Ivy",
            "Jesse",
            "Kayla",
            "Linus",
        )
        puppyRepository.add(Puppy(PuppyId.generate(), puppies.random(), false))
    }
}

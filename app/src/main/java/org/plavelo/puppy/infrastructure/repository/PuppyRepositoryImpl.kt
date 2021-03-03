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
package org.plavelo.puppy.infrastructure.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.plavelo.puppy.domain.Puppy
import org.plavelo.puppy.domain.PuppyId
import org.plavelo.puppy.infrastructure.db.dao.PuppyDao
import org.plavelo.puppy.infrastructure.db.dto.PuppyDto
import org.plavelo.puppy.infrastructure.db.dto.toDto
import org.plavelo.puppy.infrastructure.db.dto.toModel
import org.plavelo.puppy.infrastructure.db.dto.toModels
import org.plavelo.puppy.usecase.repository.PuppyRepository
import javax.inject.Inject

class PuppyRepositoryImpl @Inject constructor(
    private val puppyDao: PuppyDao,
) : PuppyRepository {
    override fun getAll(): Flow<List<Puppy>> = puppyDao.getAll().map {
        it.toModels()
    }

    override fun get(id: PuppyId): Flow<Puppy> = puppyDao.get(id.id).map(PuppyDto::toModel)

    override suspend fun add(puppy: Puppy) = puppyDao.insert(puppy.toDto())
}

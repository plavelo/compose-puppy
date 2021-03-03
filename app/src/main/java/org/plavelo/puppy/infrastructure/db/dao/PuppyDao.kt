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
package org.plavelo.puppy.infrastructure.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.plavelo.puppy.infrastructure.db.dto.PuppyDto

@Dao
interface PuppyDao {
    @Query("SELECT * FROM puppies")
    fun getAll(): Flow<List<PuppyDto>>

    @Query("SELECT * FROM puppies WHERE id = :id")
    fun get(id: String): Flow<PuppyDto>

    @Insert
    suspend fun insert(puppy: PuppyDto)
}

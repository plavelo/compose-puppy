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
package org.plavelo.puppy.infrastructure.db.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.plavelo.puppy.domain.Puppy
import org.plavelo.puppy.domain.PuppyId

@Entity(tableName = "puppies")
data class PuppyDto(
    @PrimaryKey val id: String,
    val name: String,
    val favorite: Boolean,
)

fun List<PuppyDto>.toModels(): List<Puppy> = map(PuppyDto::toModel)

fun PuppyDto.toModel(): Puppy = Puppy(
    PuppyId(id),
    name,
    favorite,
)

fun Puppy.toDto(): PuppyDto = PuppyDto(
    id.id,
    name,
    favorite,
)

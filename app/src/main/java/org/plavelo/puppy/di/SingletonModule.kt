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
package org.plavelo.puppy.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.plavelo.puppy.infrastructure.db.AppDatabase
import org.plavelo.puppy.infrastructure.repository.PuppyRepositoryImpl
import org.plavelo.puppy.usecase.repository.PuppyRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SingletonModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.create(context)

    @Provides
    @Singleton
    fun providePuppyRepository(database: AppDatabase): PuppyRepository =
        PuppyRepositoryImpl(database.puppyDao())
}

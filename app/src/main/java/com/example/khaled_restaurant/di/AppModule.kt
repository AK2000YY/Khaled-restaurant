package com.example.khaled_restaurant.di

import android.content.Context
import androidx.room.Room
import com.example.khaled_restaurant.data.local.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database =
        Room.databaseBuilder(
            context,
            Database::class.java,
            "database"
        ).build()


    @Provides
    @Singleton
    fun provideStreetDao(db: Database) =
        db.streetDao

    @Provides
    @Singleton
    fun provideCustomerDao(db: Database) =
        db.customerDao

}

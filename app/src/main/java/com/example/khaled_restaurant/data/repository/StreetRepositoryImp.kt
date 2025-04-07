package com.example.khaled_restaurant.data.repository

import com.example.khaled_restaurant.data.local.street.StreetDao
import com.example.khaled_restaurant.data.local.street.toModel
import com.example.khaled_restaurant.domain.model.Street
import com.example.khaled_restaurant.domain.model.toEntity
import com.example.khaled_restaurant.domain.repository.StreetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StreetRepositoryImp @Inject constructor(
    private val dao: StreetDao
) : StreetRepository {

    override suspend fun getStreet(): Flow<List<Street>> =
        dao.getAllStreet().map { streetEntities ->
            streetEntities
                .map { streetEntity ->
                    streetEntity.toModel()
                }
        }

    override suspend fun insertOrUpdateStreet(street: Street) {
        dao.saveOrUpdateStreet(street.toEntity())
    }

    override suspend fun deleteStreet(street: Street) {
        dao.deleteStreet(street.toEntity())
    }

}
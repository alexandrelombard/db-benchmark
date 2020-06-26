package com.al.db.document.repositories

import com.al.db.relational.model.SingleEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface SingleEntityMongoDbRepository : MongoRepository<SingleEntity, Long> {
}
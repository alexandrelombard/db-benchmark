package com.al.db.relational.repositories

import com.al.db.relational.model.OwnedRelatedEntity
import com.al.db.relational.model.OwnerRelatedEntity
import com.al.db.relational.model.SingleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface SingleEntityRepository : JpaRepository<SingleEntity, Long>

interface OwnerRelatedEntityRepository : JpaRepository<OwnerRelatedEntity, Long>

interface OwnedRelatedEntityRepository : JpaRepository<OwnedRelatedEntity, Long>
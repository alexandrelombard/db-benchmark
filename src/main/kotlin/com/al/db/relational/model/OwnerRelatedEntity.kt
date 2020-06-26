package com.al.db.relational.model

import javax.persistence.*

@Entity
class OwnerRelatedEntity(
        val name: String,
        val value: Float,
        @OneToMany(mappedBy = "owner") val ownedEntities: List<OwnedRelatedEntity> = arrayListOf()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}
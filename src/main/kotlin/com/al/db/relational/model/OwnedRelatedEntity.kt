package com.al.db.relational.model

import javax.persistence.*

@Entity
class OwnedRelatedEntity(
        val name: String,
        val value: Float,
        @ManyToOne val owner: OwnerRelatedEntity
) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
}
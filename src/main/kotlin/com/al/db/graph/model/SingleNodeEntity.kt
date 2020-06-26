package com.al.db.graph.model

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import org.neo4j.ogm.annotation.NodeEntity

@NodeEntity
class SingleNodeEntity(val name: String, val value: Float) {
    @Id
    @GeneratedValue
    var id: Long = 0
}
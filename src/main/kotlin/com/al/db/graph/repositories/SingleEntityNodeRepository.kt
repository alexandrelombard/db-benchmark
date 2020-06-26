package com.al.db.graph.repositories

import com.al.db.graph.model.SingleNodeEntity
import org.springframework.data.neo4j.repository.Neo4jRepository

interface SingleEntityNodeRepository : Neo4jRepository<SingleNodeEntity, Long>
package com.al.db

import com.al.db.document.repositories.SingleEntityMongoDbRepository
import com.al.db.graph.model.SingleNodeEntity
import com.al.db.graph.repositories.SingleEntityNodeRepository
import com.al.db.relational.model.SingleEntity
import com.al.db.relational.repositories.OwnerRelatedEntityRepository
import com.al.db.relational.repositories.SingleEntityRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
class Benchmark

@Component
class RunBench {
    @Autowired
    lateinit var singleEntityRepository: SingleEntityRepository

    @Autowired
    lateinit var singleEntityMongoDbRepository: SingleEntityMongoDbRepository

    @Autowired
    lateinit var singleEntityNodeRepository: SingleEntityNodeRepository

    @Autowired
    lateinit var ownerEntityRepository: OwnerRelatedEntityRepository

    @PostConstruct
    fun run() {
        for(count in arrayOf(100, 500, 1000, 5000, 10000, 50000, 100000)) {
            // 1 by 1 PG
            mutableListOf<SingleEntity>().let {
                for(i in 0..count) {
                    it.add(SingleEntity(UUID.randomUUID().toString(), Random().nextFloat()))
//                    it[i].id = (i + 1).toLong() // Manual ID
                }
                val tic = System.nanoTime()
                for(i in 0..count) {
                    singleEntityRepository.save(it[i])
                }
                singleEntityRepository.flush()
                println("PG 1/1 $count : ${System.nanoTime() - tic}")
            }

            System.nanoTime().let {
                val l = this.singleEntityRepository.findAll()
                println("PG FindAll (1/1) $count ${System.nanoTime() - it}")
            }

            System.nanoTime().let {
                this.singleEntityRepository.deleteAll()
                println("PG DeleteAll (1/1) $count ${System.nanoTime() - it}")
            }

            // Batch PG
            mutableListOf<SingleEntity>().let {
                for(i in 0..count) {
                    it.add(SingleEntity(UUID.randomUUID().toString(), Random().nextFloat()))
//                    it[i].id = (i + 1).toLong() // Manual ID
                }
                val tic = System.nanoTime()
                singleEntityRepository.saveAll(it)
                singleEntityRepository.flush()
                println("PG Batch $count : ${System.nanoTime() - tic}")
            }

            System.nanoTime().let {
                val l = this.singleEntityRepository.findAll()
                println("PG FindAll (Batch) $count ${System.nanoTime() - it}")
            }

            System.nanoTime().let {
                this.singleEntityRepository.deleteAll()
                println("PG DeleteAll (Batch) $count ${System.nanoTime() - it}")
            }

            // 1 by 1 Mongo
            mutableListOf<SingleEntity>().let {
                for(i in 0..count) {
                    it.add(SingleEntity(UUID.randomUUID().toString(), Random().nextFloat()))
                    it[i].id = (i + 1).toLong()
                }
                val tic = System.nanoTime()
                for(i in 0..count) {
                    singleEntityMongoDbRepository.save(it[i])
                }
                println("Mongo 1/1 $count : ${System.nanoTime() - tic}")
            }

            System.nanoTime().let {
                val l = this.singleEntityMongoDbRepository.findAll()
                println("Mongo FindAll (1/1) $count ${System.nanoTime() - it}")
            }

            System.nanoTime().let {
                this.singleEntityMongoDbRepository.deleteAll()
                println("Mongo DeleteAll (1/1) $count ${System.nanoTime() - it}")
            }

            // Batch Mongo
            mutableListOf<SingleEntity>().let {
                for(i in 0..count) {
                    it.add(SingleEntity(UUID.randomUUID().toString(), Random().nextFloat()))
                    it[i].id = (i + 1).toLong()
                }
                val tic = System.nanoTime()
                singleEntityMongoDbRepository.saveAll(it)
                println("Mongo Batch $count : ${System.nanoTime() - tic}")
            }

            System.nanoTime().let {
                val l = this.singleEntityMongoDbRepository.findAll()
                println("Mongo FindAll (Batch) $count ${System.nanoTime() - it}")
            }

            System.nanoTime().let {
                this.singleEntityMongoDbRepository.deleteAll()
                println("Mongo DeleteAll (Batch) $count ${System.nanoTime() - it}")
            }

            // 1 by 1 Neo4j
            mutableListOf<SingleNodeEntity>().let {
                for(i in 0..count) {
                    it.add(SingleNodeEntity(UUID.randomUUID().toString(), Random().nextFloat()))
//                    it[i].id = (i + 1).toLong() // Manual ID
                }
                val tic = System.nanoTime()
                for(i in 0..count) {
                    singleEntityNodeRepository.save(it[i])
                }
                println("Neo4j 1/1 $count : ${System.nanoTime() - tic}")
            }

            System.nanoTime().let {
                val l = this.singleEntityNodeRepository.findAll()
                println("Neo4j FindAll (1/1) $count ${System.nanoTime() - it}")
            }

            System.nanoTime().let {
                this.singleEntityNodeRepository.deleteAll()
                println("Neo4j DeleteAll (1/1) $count ${System.nanoTime() - it}")
            }

            // Batch Neo4j
            mutableListOf<SingleNodeEntity>().let {
                for(i in 0..count) {
                    it.add(SingleNodeEntity(UUID.randomUUID().toString(), Random().nextFloat()))
//                    it[i].id = (i + 1).toLong() // Manual ID
                }
                val tic = System.nanoTime()
                singleEntityNodeRepository.saveAll(it)
                println("Neo4j Batch $count : ${System.nanoTime() - tic}")
            }

            System.nanoTime().let {
                val l = this.singleEntityNodeRepository.findAll()
                println("Neo4j FindAll (Batch) $count ${System.nanoTime() - it}")
            }

            System.nanoTime().let {
                this.singleEntityNodeRepository.deleteAll()
                println("Neo4j DeleteAll (Batch) $count ${System.nanoTime() - it}")
            }
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(Benchmark::class.java, *args)
}
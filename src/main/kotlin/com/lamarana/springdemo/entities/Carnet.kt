package com.lamarana.springdemo.entities

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*

@Entity
data class Carnet(
        @Id
        @Column(length = 16)
        val id: UUID = UUID.randomUUID(),
        val nom: String,
        val contact: String? = null,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(nullable = false)
        val patient: Patient,

        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val dateNaissance: Date
)
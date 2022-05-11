package com.lamarana.springdemo.entities

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*

@Entity
data class Patient(
        @Id
        @Column(length = 16)
        val id: UUID = UUID.randomUUID(),
        val nom: String,
        @Temporal(TemporalType.DATE)
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        val dateNaissance: Date
)
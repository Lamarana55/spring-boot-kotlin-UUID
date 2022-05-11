package com.lamarana.springdemo.repositories

import com.lamarana.springdemo.entities.Carnet
import com.lamarana.springdemo.entities.Patient
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CarnetRepository : JpaRepository<Carnet, UUID>{
    fun findByNom(nom: String): List<Carnet>
    fun findByPatientId(id: UUID): List<Carnet>
}
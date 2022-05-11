package com.lamarana.springdemo.repositories

import com.lamarana.springdemo.entities.Patient
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PatientRepository : JpaRepository<Patient, UUID>{
    fun findByNom(nom: String): List<Patient>
}
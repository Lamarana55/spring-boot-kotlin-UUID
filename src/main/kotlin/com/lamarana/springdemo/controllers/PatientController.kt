package com.lamarana.springdemo.controllers

import com.lamarana.springdemo.entities.Patient
import com.lamarana.springdemo.repositories.PatientRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.Instant
import java.util.*
import javax.validation.Valid

@RestController
@CrossOrigin("*")
@RequestMapping("/api/patients")
class PatientController {

    private val logger = LoggerFactory.getLogger(PatientController::class.java)

    @Autowired
    private lateinit var patientRepository: PatientRepository



    @GetMapping
    fun index() = patientRepository.findAll()

    @GetMapping("{id}")
    fun show(@PathVariable id: UUID) = patientRepository.findById(id).orElse(null)


    @PostMapping
    fun save(@Valid @RequestBody patient: Patient): ResponseEntity<Any> =
            ResponseEntity(patientRepository.save(patient), HttpStatus.CREATED)


    @PutMapping("{id}")
    fun update(@PathVariable id: UUID, @Valid @RequestBody patient: Patient): ResponseEntity<Any> {

        val response = patientRepository.findById(id).map { existType ->
            val newPatient = existType.copy(
//                    id = existType.id,
                    nom = patient.nom,
                    dateNaissance = patient.dateNaissance
            )
            ResponseEntity.ok().body(patientRepository.save(newPatient))
        }.orElse(ResponseEntity.notFound().build())
        return ResponseEntity(response.body, response.statusCode)

    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: UUID) = patientRepository.deleteById(id)

}